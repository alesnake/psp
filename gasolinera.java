import java.security.SecureRandom;

import java.util.concurrent.Semaphore;
import java.util.Scanner;

public class gasolinera {

	class clientes extends Thread {
		int numOrden;
		String identificador;
		
		public clientes(int numOrden) {
			super();
			this.numOrden = numOrden;
			this.identificador = "MORENO"+numOrden;
		}

		public String getIdentificador() {
			return identificador;
		}
		
		public void run() {
			accesoSurtidor(this);
		}
	}
	
	Semaphore surtidor;
	
	public gasolinera (int limite) {
		surtidor=new Semaphore(limite,true);
	}
	
	public void accesoSurtidor(clientes c) {
		try {
			Thread.sleep(new SecureRandom().nextInt(600));//tiempo de acceso al surtidor aleatorio, max 600 miliseg
			
			surtidor.acquire();//entrada al surtidor
			
			System.out.println("Atendiendo al coche: "+c.getIdentificador()+" hora inicio: "+System.currentTimeMillis());
			Thread.sleep(10000);//llenando surtidor
			System.out.println("Coche atendido: "+c.getIdentificador()+" hora final: "+System.currentTimeMillis());//registro
			
			surtidor.release();//salida del surtidor
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void trabajoGasolinera() {
		try {
			
			int cantidad=0;//numero de coches que habra
			
			int numOleadas;
			int contOl;
			int contador=0;
			Scanner sc=new Scanner(System.in);
			
			System.out.println("Introduce el numero de oladas:");
			numOleadas=sc.nextInt();
			
			while(contador<numOleadas) {
				
				while(cantidad==0) {//control para que una oleada nunca sea de 0 coches
					cantidad=new SecureRandom().nextInt(5);//cantidad aleatoria, maximo 5 coches
				}
				
				contOl=contador+1;
				
				System.out.println("Oleada: "+contOl+" numero de coches: "+cantidad);
				
				clientes[]colaSurtidor=new clientes[cantidad];
				
				for(int j=0;j<cantidad;j++) {
					colaSurtidor[j]=new clientes(j+1);
					
					colaSurtidor[j].start();
				}
				
				contador++;
				
				if(contador<numOleadas) {
					Thread.sleep(60000);//oleada cada minuto
				}
			}
			
			sc.close();
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[]args) {
		gasolinera g=new gasolinera(2);//dos surtidores, dos recursos
		g.trabajoGasolinera();
	}
}
