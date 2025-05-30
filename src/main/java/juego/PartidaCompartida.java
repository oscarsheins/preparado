package juego;

public class PartidaCompartida {
	
    private String[] jugadas = new String[2];
    private String[] nombres = new String[2];
    private int turno = 0;
    private int[] victorias = new int[2];
    private boolean terminado = false;
    private int maxVictorias;

    public PartidaCompartida(int maxVictorias) {
        this.maxVictorias = maxVictorias;
    }

    public synchronized boolean estaTerminado() {
        return terminado;
    }

    public synchronized void registrarJugada(String nombre, String jugada) {
        while (turno == 2 && !terminado) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        int id = turno;
        jugadas[id] = jugada;
        nombres[id] = nombre;
        turno++;

        if (turno == 2) {
            // Mostrar jugadas
            System.out.println("=>" + nombres[0] + " saca " + jugadas[0].toUpperCase());
            System.out.println("=>" + nombres[1] + " saca " + jugadas[1].toUpperCase());

            int ganador = comparar(jugadas[0], jugadas[1]);

            if (ganador == -1) {
                System.out.println("EMPATE\n");
                terminado = true;
            } else {
                System.out.println(jugadas[0].toUpperCase() + "-" + jugadas[1].toUpperCase()
                        + " => GANADOR: " + nombres[ganador] + "\n");
                victorias[ganador]++;
                if (victorias[ganador] >= maxVictorias) {
                    terminado = true;
                }
            }

            turno = 0; // reiniciar para la siguiente ronda
            notifyAll(); // Despierta al otro jugador
        }
    }

    private int comparar(String j1, String j2) {
        if (j1.equals(j2)) return -1;
        if ((j1.equals("Piedra") && j2.equals("Tijera")) ||
            (j1.equals("Papel") && j2.equals("Piedra")) ||
            (j1.equals("Tijera") && j2.equals("Papel"))) {
            return 0;
        } else {
            return 1;
        }
    }

    public void mostrarResultadosFinales() {
        System.out.println("Jugador1: " + nombres[0] + " => Gana: " + victorias[0] + " veces.");
        System.out.println("Jugador2: " + nombres[1] + " => Gana: " + victorias[1] + " veces.");
    }
}
