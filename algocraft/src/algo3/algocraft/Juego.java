package algo3.algocraft;

import java.util.ArrayList;

import algo3.algocraft.edificios.EdificioDeRecurso;
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
		inicializarRecursos();
	}

	private void inicializarRecursos() {
		for(Jugador juga: jugadores){
			juga.agregarGasVespeno(200);
			juga.agregarMineral(200);
		}
	}
	
	private void administrarRecursos(){
		/*Jugador jugadorActual = turnos.turnoActual();
		ArrayList<EdificioDeRecurso> edificiosDeRecursos = mapa.edificioDeRecursos(jugadorActual.color());
		for(EdificioDeRecurso edificio : edificiosDeRecursos ){
			jugadorActual.agregarRecurso(edificio.),);
		}*/
	}
	
	public void pasarTurno(){
		turnos.avanzarTurno();
		administrarRecursos();
	}
	
	public boolean moverPosicionTerrestre(Ser unidadAMover,int filaInicio, int columnaInicio ,int filaDestino, int columnaDestino){
		try{
			verificarPropiedadUnidad(unidadAMover);
			mapa.moverTerrestre(unidadAMover,filaInicio,columnaInicio,filaDestino,columnaDestino);
		}
		catch(NoEsPosibleMoverException e){
			return false;
		}
		return true;
	}
	
	public boolean moverPosicionAereo(Ser unidadAMover,int filaInicio, int columnaInicio ,int filaDestino, int columnaDestino){
		try{
			verificarPropiedadUnidad(unidadAMover);
			mapa.moverAerea(unidadAMover,filaInicio,columnaInicio,filaDestino,columnaDestino);
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

	public void agregarEdificio(String string, int i, int j) throws NoHayRecursosException {
		// TODO Auto-generated method stub
		throw new NoHayRecursosException();
	}

	public void crearUnidad(String string) throws NoHayRecursosException, NoHayEspacioException {
		// TODO Auto-generated method stub
		//throw new NoHayRecursosException();
		throw new NoHayEspacioException();
	}
}
