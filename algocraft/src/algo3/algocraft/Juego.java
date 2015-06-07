package algo3.algocraft;

import java.util.ArrayList;
import java.util.HashMap;

import algo3.algocraft.edificios.AbstractFactoryEdificios;
import algo3.algocraft.edificios.EdificioDeRecurso;
import algo3.algocraft.edificios.FactoryEdificiosProtoss;
import algo3.algocraft.edificios.FactoryEdificiosTerran;
import algo3.algocraft.exceptions.*;
import algo3.algocraft.unidades.Unidad;
import algo3.algocraft.unidades.UnidadDeAtaque;

public class Juego {

	private static Juego instancia = null;
	private Mapa mapa;
	private Turnos turnos;
	private HashMap<Jugador, AbstractFactoryEdificios> fabricas = new HashMap<Jugador, AbstractFactoryEdificios>();
	private ArrayList<Jugador> jugadores = new ArrayList<Jugador>();

	//Metodos de Inicializacion
	public void crearJugador(String nombre, Color color, TipoRaza raza) {

		/*
		 * if(nombre.length() < 4) throw new NombreIncorrectoException();
		 * this.chequearNombreYColorNoRepetidos(nombre, color);
		 */
		Jugador jugador = new Jugador(nombre, color, raza);
		jugadores.add(jugador);
		if (raza == TipoRaza.TERRAN) {
			fabricas.put(jugador, new FactoryEdificiosTerran(color));
		} else

		{
			fabricas.put(jugador, new FactoryEdificiosProtoss(color));
		}
	}

	/*
	 * public int agregarJugador(String nombre, Color color, TipoRaza raza,
	 * String[] errores){ // me pasan un vector de string donde voy a devolver
	 * los errores para mostrarlos // al usuario int actual = 0; try{
	 * crearJugador(nombre,color,raza); } catch(NombreIncorrectoException e){
	 * errores[actual] = "Nombre Incorrecto"; actual++; }
	 * catch(ColorRepetidoException e){ errores[actual] = "ColorRepetido";
	 * actual++; } return actual;
	 * 
	 * }
	 */

	
	public void iniciarJuego() {
		turnos = new Turnos(jugadores);
		Mapa.getInstance(5,5,jugadores); // puse 5x5 modificar si se quiere
		inicializarRecursos();
	}

	private void inicializarRecursos() {
		for (Jugador juga : jugadores) {
			juga.agregarGasVespeno(200);
			juga.agregarMineral(200);
		}
	}
	
	//Metodos de logica de juego
	public String JugadorActual() {
		return turnos.turnoActual().nombre();
	}


	private void administrarRecursos() {
		Jugador jugadorActual = turnos.turnoActual();
		ArrayList<EdificioDeRecurso> edificiosDeRecursos = mapa
				.edificioDeGas(jugadorActual.color());
		for (EdificioDeRecurso edificio : edificiosDeRecursos) {
			jugadorActual.agregarGasVespeno(edificio.recolectar());
		}
		edificiosDeRecursos = mapa.edificioDeMineral(jugadorActual.color());
		for (EdificioDeRecurso edificio : edificiosDeRecursos) {
			jugadorActual.agregarMineral(edificio.recolectar());
		}
	}

	public void pasarTurno() {
		turnos.avanzarTurno();
		administrarRecursos();
	}
	public Celda ContenidoFilaColumna(int fila, int columna) {
		return mapa.ContenidoPosicion(new Posicion(fila, columna)); // Aereo o
																	// Terrestre
																	// ?
	}
	//Metodos de movimiento
	public boolean moverPosicionTerrestre(Unidad unidadAMover, int filaInicio,
			int columnaInicio, int filaDestino, int columnaDestino) {
		try {
			Posicion posicionInicial=new Posicion(filaInicio, columnaInicio);
			Posicion posicionFinal=new Posicion(filaDestino, columnaDestino);
			verificarPropiedadUnidad(unidadAMover);
			verificarMovimientoUnidad(unidadAMover,posicionInicial,posicionFinal);
			mapa.moverTerrestre(posicionInicial,posicionFinal );
		} catch (NoEsPosibleMoverException e) {
			return false;
		}
		return true;
	}

	public boolean moverPosicionAereo(Unidad unidadAMover, int filaInicio,
			int columnaInicio, int filaDestino, int columnaDestino) {
		try {
			Posicion posicionInicial=new Posicion(filaInicio, columnaInicio);
			Posicion posicionFinal=new Posicion(filaDestino, columnaDestino);
			verificarPropiedadUnidad(unidadAMover);
			verificarMovimientoUnidad(unidadAMover,posicionInicial,posicionFinal);
			mapa.moverAerea(posicionInicial,posicionFinal);
		} catch (NoEsPosibleMoverException e) {
			return false;
		}
		return true;
	}
	
	//Verificaciones
	private void verificarMovimientoUnidad(Unidad unidadAMover,
			Posicion posicionInicial, Posicion posicionFinal) 	throws NoEsPosibleMoverException {
		if (unidadAMover.vision()>posicionInicial.distancia(posicionFinal))
			throw new NoEsPosibleMoverException();
	}

	private void verificarPropiedadUnidad(Ser unidad)
			throws NoEsPosibleMoverException {
		if (!turnos.turnoActual().esColor(unidad.color))
			throw new NoEsPosibleMoverException();
	}

	

	private void chequearNombreYColorNoRepetidos(String nombre, Color color)
			throws ColorRepetidoException {
		for (Jugador jugador : jugadores) {
			if (jugador.esNombre(nombre) || jugador.esColor(color)) {
				throw new ColorRepetidoException();
			}
		}
	}
	//Metodos de Creacion
	public void agregarEdificio(String string, int i, int j)
			throws NoHayRecursosException, NoEstanLosRequisitosException {
		// TODO Auto-generated method stub
		// throw new NoHayRecursosException();
		turnos.turnoActual();
		// throw new NoEstanLosRequisitosException();
	}

	public void crearUnidad(String string) throws NoHayRecursosException,
			NoHayEspacioException {
		// TODO Auto-generated method stub
		// throw new NoHayRecursosException();
		throw new NoHayEspacioException();
	}
	//Metodos De Ataque
	public void atacarAire(UnidadDeAtaque atacante, Ser atacado) {
		atacante.atacarAire(atacado);
		if (atacado.estaMuerto())
			mapa.borrarSerAereo(atacado);
	}

	public void atacarTierra(UnidadDeAtaque atacante, Ser atacado) {
		atacante.atacarTierra(atacado);
		if (atacado.estaMuerto())
			mapa.borrarSerTerrestre(atacado);
	}
	// Singleton
	private Juego() {
		mapa = Mapa.getInstance(5,5,jugadores);
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
