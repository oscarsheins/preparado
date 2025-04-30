package semaforos;

import java.util.concurrent.Semaphore;

public class LanzarRestaurante {

	public static void main(String[] args) {
		
//		long inicio = System.currentTimeMillis(); // Marcar inicio 	 
//
//		Semaphore hayCliente = new Semaphore(1);
//		Semaphore hayComida = new Semaphore(1);
//
//		try {
//			hayCliente.acquire();
//			hayComida.acquire();
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		CocineroHilo cocinero = new CocineroHilo(1, hayCliente, hayComida);
//
//		cocinero.start();
//
//		Comensales[] comensales = new Comensales[5];
//
//		for (int i = 1; i < comensales.length; i++) {
//
//			comensales[i] = new Comensales(i, hayCliente, hayComida);
//
//		}
//
//		for (int i = 1; i < comensales.length; i++) {
//
//			comensales[i].start();
//
//		}
//		
//		long fin = System.currentTimeMillis(); // Marcar fin
//	    long duracion = fin - inicio;
//
//	    System.out.println("Tiempo total de ejecución: " + duracion + " ms");
		
		Semaphore menuPreparado = new Semaphore(1);
		Semaphore clientesEsperando = new Semaphore(1);
		//Al principio no hay ni menú preparado ni clientes esperando.
		try {
			menuPreparado.acquire();
			clientesEsperando.acquire();
			CocineroHilo cocinero = new CocineroHilo(1,menuPreparado, clientesEsperando);
			cocinero.start();
			
			Comensales hiloComensal1 = new Comensales(1,menuPreparado, clientesEsperando);
			Comensales hiloComensal2 = new Comensales(2,menuPreparado,clientesEsperando);
			Comensales hiloComensal3 = new Comensales(3,menuPreparado,clientesEsperando);
			hiloComensal1.start();
			hiloComensal2.start();
			hiloComensal3.start();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}

}
