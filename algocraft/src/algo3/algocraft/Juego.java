package algo3.algocraft;

import java.util.ArrayList;
import algo3.algocraft.exceptions.*;

public class Juego {

	
	
	private static Juego instancia=null;
	private Mapa mapa;
	private Turnos turnos;

	private ArrayList<Jugador> jugadores = new ArrayList<Jugador>();

	public   void crearJugador(String nombre, Color color, TipoRaza raza) throws NombreIncorrectoException, ColorRepetidoException {

		if(nombre.length() < 4) throw new NombreIncorrectoException();
		this.chequearNombreYColorNoRepetidos(nombre, color);
		Jugador jugador = new Jugador(nombre, color, raza);
		jugadores.add(jugador);
	}
	
	public int agregarJugador(String nombre, Color color, TipoRaza raza, String[] errores){
		// me pasan un vector de string donde voy a devolver los errores para mostrarlos
		// al usuario
		int actual = 0;
		try{
			crearJugador(nombre,color,raza);
		}
		catch(NombreIncorrectoException e){
			errores[actual] = "Nombre Incorrecto";
			actual++;
		} 
		catch(ColorRepetidoException e){
			errores[actual] = "ColorRepetido";
			actual++;
		}
		return actual;
		
	}
	
	public String JugadorActual(){
		return turnos.turnoActual().nombre();
	}
	
	public void iniciarJuego(){
		turnos = new Turnos(jugadores);
		mapa.getInstance(jugadores);
	}
	
	public void pasarTurno(){
		turnos.avanzarTurno();
	}
	
	public boolean moverPosicion(Ser unidadAMover, int fila, int columna){
		try{
			verificarPropiedadUnidad(unidadAMover);
			mapa.mover(unidadAMover, fila, columna);
		}
		catch(NoEsPosibleMoverException e){
			return false;
		}
		return true;
	}
	
	private void verificarPropiedadUnidad(Ser unidad) throws NoEsPosibleMoverException{
		if(!turnos.turnoActual().esColor(unidad.color))
			throw new NoEsPosibleMoverException();
	}
	
	public Celda ContenidoFilaColumna(int fila, int columna){
		return mapa.ContenidoFilaColumna(fila,columna);
	}

	private void chequearNombreYColorNoRepetidos(String nombre, Color color) throws ColorRepetidoException {
		for (Jugador jugador : jugadores) {
			if (jugador.esNombre(nombre) || jugador.esColor(color)) {
				throw new ColorRepetidoException();
			}
		}
	}

	// Singleton
	private Juego() {
		mapa = Mapa.getInstance(jugadores);
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
