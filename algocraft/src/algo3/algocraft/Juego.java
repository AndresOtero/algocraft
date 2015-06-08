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
		
		if(nombre.length() < 4){
			throw new NombreIncorrectoException();
		}
		 this.chequearNombreYColorNoRepetidos(nombre, color);
		
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
	public boolean moverPosicionTerrestre(int filaInicio,
			int columnaInicio, int filaDestino, int columnaDestino) {
		try {
			Posicion posicionInicial=new Posicion(filaInicio, columnaInicio);
			Posicion posicionFinal=new Posicion(filaDestino, columnaDestino);
			Unidad unidadAMover = (Unidad) mapa.ContenidoPosicion(posicionInicial).serEnLaCeldaTerrestre();
			verificarPropiedadUnidad(unidadAMover);
			verificarMovimientoUnidad(unidadAMover,posicionInicial,posicionFinal);
			mapa.moverTerrestre(posicionInicial,posicionFinal );
			turnos.agregarMovido(unidadAMover);
		} catch (NoEsPosibleMoverException e) {
			return false;
		}
		return true;
	}

	public boolean moverPosicionAereo(int filaInicio,
			int columnaInicio, int filaDestino, int columnaDestino) {
		try {
			Posicion posicionInicial=new Posicion(filaInicio, columnaInicio);
			Posicion posicionFinal=new Posicion(filaDestino, columnaDestino);
			Unidad unidadAMover = (Unidad) mapa.ContenidoPosicion(posicionInicial).serEnLaCeldaAerea();
			verificarPropiedadUnidad(unidadAMover);
			verificarMovimientoUnidad(unidadAMover,posicionInicial,posicionFinal);
			mapa.moverAerea(posicionInicial,posicionFinal);
			turnos.agregarMovido(unidadAMover);
		} catch (NoEsPosibleMoverException e) {
			return false;
		}
		return true;
	}
	
	//Verificaciones
	private void verificarMovimientoUnidad(Unidad unidadAMover,
			Posicion posicionInicial, Posicion posicionFinal)  {
		if (unidadAMover.vision()>posicionInicial.distancia(posicionFinal))
			throw new NoEsPosibleMoverException();
	}

	private void verificarPropiedadUnidad(Unidad unidad){
		if (!turnos.turnoActual().esColor(unidad.color) || turnos.yaSeMovio(unidad))
			throw new NoEsPosibleMoverException();
	}

	

	private void chequearNombreYColorNoRepetidos(String nombre, Color color)  {
		for (Jugador jugador : jugadores) {
			if (jugador.esNombre(nombre) || jugador.esColor(color)) {
				throw new ColorRepetidoException();
			}
		}
	}
	//Metodos de Creacion
	public void agregarEdificio(String string, int i, int j) {
		// TODO Auto-generated method stub
		// throw new NoHayRecursosException();
		turnos.turnoActual();
		// throw new NoEstanLosRequisitosException();
	}

	public void crearUnidad(String string){
		// TODO Auto-generated method stub
		// throw new NoHayRecursosException();
		throw new NoHayEspacioException();
	}
	//Metodos De Ataque

	public boolean atacarAire(int filIni, int colIni , int filFinal , int colFinal) {
		try{
			Posicion posini = new Posicion(filIni,colIni);
			Posicion posfin = new Posicion(filFinal,colFinal);
			UnidadDeAtaque unidadQAtaca = (UnidadDeAtaque) mapa.ContenidoPosicion(posini).serEnLaCeldaAerea();
			Ser serAtacado1 =  mapa.ContenidoPosicion(posfin).serEnLaCeldaAerea();
			Ser serAtacado2 =  mapa.ContenidoPosicion(posfin).serEnLaCeldaTerrestre();
			verificarSiPuedeAtacarEnRango(unidadQAtaca,posini,posfin);
			verificarPropiedadAtaque(unidadQAtaca);
			if ( serAtacado1 != null ) unidadQAtaca.atacarAire(serAtacado1);
			if ( serAtacado2 != null) unidadQAtaca.atacarTierra(serAtacado2);
			if ( serAtacado1.estaMuerto()) mapa.borrarSerAereo(serAtacado1);
			if ( serAtacado2.estaMuerto()) mapa.borrarSerTerrestre(serAtacado2);
			turnos.agregarQueAtaco(unidadQAtaca);
		}
		catch (NoEsPosibleAtacarException e){
			return false;
		}
		return true;
	}
	
	private void verificarSiPuedeAtacarEnRango(Unidad unidadAMover,
			Posicion posicionInicial, Posicion posicionFinal) {
		if (unidadAMover.vision()>posicionInicial.distancia(posicionFinal))
			throw new NoEsPosibleAtacarException();
	}
	
	private void verificarPropiedadAtaque(Unidad unidad) {
		if (!turnos.turnoActual().esColor(unidad.color) || turnos.yaAtaco(unidad))
			throw new NoEsPosibleMoverException();
	}


	public boolean atacarTierra(int filIni,int colIni,int filFinal, int colFinal) {
		try{
			Posicion posini = new Posicion(filIni,colIni);
			Posicion posfin = new Posicion(filFinal,colFinal);
			UnidadDeAtaque unidadQAtaca = (UnidadDeAtaque) mapa.ContenidoPosicion(posini).serEnLaCeldaTerrestre();
			Ser serAtacado1 =  mapa.ContenidoPosicion(posfin).serEnLaCeldaAerea();
			Ser serAtacado2 =  mapa.ContenidoPosicion(posfin).serEnLaCeldaTerrestre();
			verificarSiPuedeAtacarEnRango(unidadQAtaca,posini,posfin);
			verificarPropiedadAtaque(unidadQAtaca);
			if ( serAtacado1 != null ) unidadQAtaca.atacarAire(serAtacado1);
			if ( serAtacado2 != null) unidadQAtaca.atacarTierra(serAtacado2);
			if ( serAtacado1.estaMuerto()) mapa.borrarSerAereo(serAtacado1);
			if ( serAtacado2.estaMuerto()) mapa.borrarSerTerrestre(serAtacado2);
			turnos.agregarQueAtaco(unidadQAtaca);
		}
		catch (NoEsPosibleAtacarException e){
			return false;
		}
		return true;
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
