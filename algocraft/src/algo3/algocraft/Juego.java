package algo3.algocraft;

import java.util.ArrayList;


public class Juego {

	
	
	private static Juego instancia=null;
	private Mapa mapa = Mapa.getInstance();

	private ArrayList<Jugador> jugadores = new ArrayList<Jugador>();

	public   void crearJugador(String nombre, Color color, TipoRaza raza) throws NombreIncorrectoException {

		this.chequearNombreYColorNoRepetidos(nombre, color);
		Jugador jugador = new Jugador(nombre, color, raza);
		jugadores.add(jugador);
	}
	
	public void iniciarJuego(){
		
	}
	
	public void pasarTurno(){
		
	}
	
	public Celda ContenidoFilaColumna(int fila, int columna){
		return mapa.ContenidoFilaColumna(fila,columna);
	}

	private void chequearNombreYColorNoRepetidos(String nombre, Color color) {
		for (Jugador jugador : jugadores) {
			if (jugador.esNombre(nombre) || jugador.esColor(color)) {
				//Error 
			}
		}
	}

	// Singleton
	private Juego() {
		mapa = Mapa.getInstance();
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
