package semaforos;

import java.util.concurrent.Semaphore;

public class Comensales extends Thread{
	
	private int id;
	private Semaphore hayCliente;
	private Semaphore hayComida;
	
	
	public Comensales(int id, Semaphore hayCliente, Semaphore hayComida) {
		super();
		this.id = id;
		this.hayCliente = hayCliente;
		this.hayComida = hayComida;
	}
	
	public void comer() {
		
		
		try {
			System.out.println(id + " esperando");
			hayCliente.release();
			hayComida.acquire();
			System.out.println("Comensal" + id + "comiendo");
			sleep(2000);
			System.out.println("Comensal" + id + "termina de comer");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void run() {
		
		comer();
		
	}

}

