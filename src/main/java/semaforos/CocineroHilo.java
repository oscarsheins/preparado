package semaforos;

import java.util.concurrent.Semaphore;

public class CocineroHilo extends Thread {

	private int id;
	private Semaphore hayCliente;
	private Semaphore hayComida;

	public CocineroHilo(int id, Semaphore hayCliente, Semaphore hayComida) {
		super();
		this.id = id;
		this.hayCliente = hayCliente;
		this.hayComida = hayComida;
	}

	public void cocinar() {

		try {
			hayCliente.acquire();
			System.out.println("Cocinero" + id + "empieza a cocinar");
			sleep(5000);
			System.out.println("plato LISTO");
			hayComida.release(2);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void run() {

		while (true) {

			cocinar();

		}

	}

}
