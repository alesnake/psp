package primerProceso;

import java.util.Arrays;
import java.io.IOException;

public class primerProceso {

	public static void main(String[] args) {
		
		String programa="calc";
		Process [] procesos=new Process[4];
		Process proceso;
		
		/*
		 * if(args.length==0) {
				System.err.println("Se necesita un programa a ejecutar");
				System.exit(-1);
			}
		 */
		
		ProcessBuilder constructorProceso = new ProcessBuilder(programa);
		
		try {
			for(int i=0;i<procesos.length;i++) {
				proceso=constructorProceso.start();
				procesos[i]=proceso;
			}
			
			for(int i=0;i<procesos.length;i++) {
				proceso=procesos[i];
				Thread.sleep(4000);
				proceso.destroy();
			}
			
			//int retorno=proceso.waitFor();
			//System.out.println("La ejecucion de "+Arrays.toString(args)+" devuelve "+retorno);
			
		}catch(IOException excepcion) {
			System.err.println("Excepcion de Entrada / Salida ");
		}catch(InterruptedException excepcion) {
			System.err.println("El proceso hijo finalizo de forma incorrecta");
			System.exit(-1);
		}

	}

}
