package juego;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class Jugador extends Thread {
    private String nombre;
    private Semaphore sePuedeJugar;
    private PartidaCompartida partida;

    public Jugador(String nombre, Semaphore sePuedeJugar, PartidaCompartida partida) {
        this.nombre = nombre;
        this.sePuedeJugar = sePuedeJugar;
        this.partida = partida;
    }

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public Semaphore getSePuedeJugar() {
		return sePuedeJugar;
	}

	public void setSePuedeJugar(Semaphore sePuedeJugar) {
		this.sePuedeJugar = sePuedeJugar;
	}



	public String opcionesSalida() {
		
		Random randon = new Random();
		
		String[] opciones = {"Piedra", "Papel", "Tijera"};
		
		return opciones[randon.nextInt(3)];
		
	}
	
	
	
	
	public void run() {
        while (!partida.estaTerminado()) {
            try {
            	sePuedeJugar.acquire();
                if (!partida.estaTerminado()) {
                    String jugada = opcionesSalida();
                    partida.registrarJugada(nombre, jugada);
                }
                sePuedeJugar.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
