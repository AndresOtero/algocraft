package algo3.algocraft;

import java.util.ArrayList;

/*El codigo no se sube roto, ni sin formato(ctrl+shift+f). ->hay tabla*/
public class Juego {
	private static Juego instancia = null;
	private Mapa mapa = new Mapa();
	private ArrayList<Jugador> jugadores = new ArrayList<Jugador>();

	public void crearJugador(String nombre, Color color, TipoRaza raza) {
		this.chequearNombreYColorNoRepetidos(nombre,color);
		Jugador jugador = new Jugador(nombre,color,raza);
		jugadores.add(jugador);
	}

	private void chequearNombreYColorNoRepetidos(String nombre, Color color) {
		for (Jugador jugador : jugadores) {
			if (jugador.esNombre(nombre) || jugador.esColor(color)) {
				/* Error */
			}
		}
	}


	// Singleton
	private Juego() {
	}

	private synchronized static void createInstance() {
		if (instancia == null) {
			instancia = new Juego();
		}
	}

	public static Juego getInstance() {
		if (instancia == null)
			createInstance();
		return instancia;
	}
}
