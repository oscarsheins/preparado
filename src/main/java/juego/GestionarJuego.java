package juego;

import java.util.concurrent.Semaphore;

public class GestionarJuego {

	public static void main(String[] args) throws InterruptedException {
        int maxVictorias = 10; 

        Semaphore semaforo = new Semaphore(2);
        PartidaCompartida partida = new PartidaCompartida(maxVictorias);

        Jugador jugador1 = new Jugador("Iván", semaforo, partida);
        Jugador jugador2 = new Jugador("Alexa", semaforo, partida);

        jugador1.start();
        jugador2.start();

        jugador1.join();
        jugador2.join();

        partida.mostrarResultadosFinales();
    }
	
}
