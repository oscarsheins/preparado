package semaforos;

import java.util.concurrent.Semaphore;

public class Mecanico extends Thread {

	private int id;
	private Semaphore hayCoche;
	private Semaphore hayMecanico;
	private int tiempoArreglar;

	public Mecanico(int id, Semaphore hayCoche, Semaphore hayMecanico, int tiempArreglar) {
		super();
		this.id = id;
		this.hayCoche = hayCoche;
		this.hayMecanico = hayMecanico;
		this.tiempoArreglar = tiempArreglar;
	}

	public void repararCohce() {

		try {
			while (true) {
				hayCoche.acquire();
				System.out.println("arreglando coche");
				sleep(tiempoArreglar);
				System.out.println("coche arreglado");
				hayMecanico.release();
			}

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public void run() {
		
		repararCohce();
		
	}

}
