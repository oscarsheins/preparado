package semaforos;

import java.util.concurrent.Semaphore;

public class Coche extends Thread{

	private int id;
	private Semaphore hayCoche;
	private Semaphore hayMecanico;
	
	public Coche(int id, Semaphore hayCoche, Semaphore hayMecanico) {
		super();
		this.id = id;
		this.hayCoche = hayCoche;
		this.hayMecanico = hayMecanico;
	}
	
	public void entrarGaraje() {
		
		
		try {
			System.out.println(id + "esperando a entrar");
			hayCoche.release();
			hayMecanico.acquire();
			sleep(2000);
			System.out.println(id + "saliendo del garaje");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println();
		
		
	}
	
}
