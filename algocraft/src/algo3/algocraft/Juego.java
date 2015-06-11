package algo3.algocraft;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

import algo3.algocraft.edificios.*;
import algo3.algocraft.exceptions.*;
import algo3.algocraft.unidades.*;

public class Juego {

	private static Juego instancia = null;
	private Mapa mapa;
	private Turnos turnos;
	private HashMap<Jugador, AbstractFactoryEdificios> fabricas = new HashMap<Jugador, AbstractFactoryEdificios>();
	private ArrayList<Jugador> jugadores = new ArrayList<Jugador>();
	private ArrayList<TuplaDeSeres> unidadesEnEspera= new ArrayList<TuplaDeSeres>();

	//Metodos de Inicializacion
	public void crearJugador(String nombre, Color color, TipoRaza raza) {
		
		if(nombre.length() < 4){
			throw new NombreIncorrectoException();
		}
		 this.chequearNombreYColorNoRepetidos(nombre, color);
		
		Jugador jugador = new Jugador(nombre, color, raza);
		jugadores.add(jugador);
		if (raza == TipoRaza.TERRAN) {
			fabricas.put(jugador, new FactoryEdificiosTerran(jugador));
		} else

		{
			fabricas.put(jugador, new FactoryEdificiosProtoss(jugador));
		}
	}

	public void iniciarJuego() {
		turnos = new Turnos(jugadores);
		mapa = Mapa.getInstance(15,15,jugadores); // puse 5x5 modificar si se quiere
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

	public boolean hayGanador(Color color){
		boolean hayGanador = false;
		if (mapa.seresDeJugador(color).isEmpty()) hayGanador = true;
		else return false;
		if (mapa.edificioDeGas(color).isEmpty()) hayGanador = true;
		else return false;
		if (mapa.edificioDeMineral(color).isEmpty()) hayGanador = true;
		else return false;
		return hayGanador;
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
	private void administrarCreacionEdificios() {
		Jugador jugadorActual = turnos.turnoActual();
		AbstractFactoryEdificios factory=fabricas.get(jugadorActual);
		HashMap<Edificio, Posicion> edificiosCreados = factory.pasarTurno();
		Iterator it = edificiosCreados.keySet().iterator();
		while(it.hasNext()){
			Edificio ed=(Edificio) it.next();
			Posicion pos= edificiosCreados.get(ed);
			if(!mapa.estaVaciaTerrestre(pos)){
				mapa.borrarSerTerrestre(mapa.ContenidoPosicion(pos).serEnLaCeldaTerrestre());	
				if(ed instanceof EdificioCreador){
					mapa.ponerEdificioCreador(pos,(EdificioCreador) ed);
				}
				if(ed instanceof RecolectableGas){
					mapa.ponerEdificioGas(pos,(EdificioDeRecurso) ed);
				}
				if(ed instanceof RecolectableMinerales){
					mapa.ponerEdificioMineral(pos,(EdificioDeRecurso) ed);
				}
				jugadorActual.agregarEspacionParaPoblacion();
				mapa.ponerTerrestre(pos, ed);
			}
		}
	}
	private void administrarCreacionUnidades() {
		Jugador jugadorActual = turnos.turnoActual();
		ArrayList<EdificioCreador> edificiosCreadores=mapa.edificioCreador(jugadorActual.color());
		for(EdificioCreador ed:edificiosCreadores ){
			ed.pasarTurno();
			for(Unidad unidad: ed.unidadesCreadas()){
				TuplaDeSeres tupla=new TuplaDeSeres(ed,unidad);
				unidadesEnEspera.add(tupla);
			}
		}
		for(TuplaDeSeres tupla:unidadesEnEspera ){
			Unidad unidad=(Unidad) tupla.serB();
			EdificioCreador ed=(EdificioCreador) tupla.serA();
			if(jugadorActual.agregarPoblacion(unidad.suministro())){
				mapa.ponerUnidadEnLaCeldaLibreMasCercana(ed, unidad);
				unidadesEnEspera.remove(tupla);
			}else{
				break;
			}
		}			
	}
	public void pasarTurno() {
		turnos.avanzarTurno();
		administrarRecursos();
		administrarCreacionEdificios();
		administrarCreacionUnidades();
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
	public void crearEdificio(TipoEdificio tipoEdifico, int fila, int columna) {
		AbstractFactoryEdificios factory= fabricas.get(turnos.turnoActual()); 
		Posicion pos=new Posicion(fila,columna);
		Celda celda= mapa.ContenidoPosicion(pos);
		Boolean seCreo=false;
		if(celda.ocupadoTerrestre()){
			throw new LaCeldaTerrestreEstaOcupada();
		}
		switch(tipoEdifico){
			case CreadorAereos:
				seCreo=factory.fabricarCreadorAereos(pos);
			case CreadorTerrestres:
				seCreo=factory.fabricarCreadorTerrestres(pos);
			case CreadorSoldados:
				seCreo=factory.fabricarCreadorSoldados(pos);
			case SumaPoblacion:
				seCreo=factory.fabricarSumaPoblacion(pos);
			case RecolectableGas:
				seCreo=factory.fabricarRecolectableGas((VolcanGasVespeno)celda.fuenteRecurso(),pos);
			case RecolectableMinerales:
				seCreo=factory.fabricarRecolectableMinerales((Mineral)celda.fuenteRecurso(), pos);
		}
		if(seCreo==false){
			// throw new NoHayRecursosException();
			// throw new NoEstanLosRequisitosException();
			return;
		}
		mapa.ponerTerrestre(pos, new EdificioEnConstruccion(turnos.turnoActual().color()));

		
	}

	public boolean crearUnidad(int fil, int col, Unidades unidad){
		Posicion pos = new Posicion(fil,col);
		// verificar que haya edificio
		EdificioCreador ed = (EdificioCreador) mapa.ContenidoPosicion(pos).serEnLaCeldaTerrestre();
		/*Horrible, refactorizar  - excepcion EDIFICIO NO CREA A X UNIDAD*/
		switch(unidad){
			case ALTOTEMPLARIO:
				((ArchivosTemplarios) ed).crearAltoTemplario(turnos.turnoActual());
			case SCOUT:
				((PuertoEstelarProtoss) ed).crearScout(turnos.turnoActual());
			case MARINE:
				((Barraca) ed).crearMarine(turnos.turnoActual());
			case DRAGON:
				((Acceso) ed).crearDragon(turnos.turnoActual());
			case NAVECIENCIA:
				((PuertoEstelarTerran) ed).crearNaveCiencia(turnos.turnoActual());
			case NAVETRANSPORTEPROTOSS:
				((PuertoEstelarProtoss) ed).crearNaveTransporteProtoss(turnos.turnoActual());
			case NAVETRANSPORTETERRAN:
				((PuertoEstelarTerran) ed).crearNaveTransporteTerran(turnos.turnoActual());
			case ESPECTRO:
				((PuertoEstelarTerran) ed).crearEspectro(turnos.turnoActual());
			case GOLLIAT:
				((Fabrica) ed).crearGolliat(turnos.turnoActual());
			case ZEALOT:
				((Acceso) ed).crearZealot(turnos.turnoActual());
		}
		 	
		throw new NoHayEspacioException();
	}
	
	//Metodos De Ataque

	public boolean atacarAire(int filIni, int colIni , int filFinal , int colFinal) {
		try{
			Posicion posini = new Posicion(filIni,colIni);
			Posicion posfin = new Posicion(filFinal,colFinal);
			UnidadDeAtaque unidadQAtaca = (UnidadDeAtaque) mapa.ContenidoPosicion(posini).serEnLaCeldaAerea();
			Ser serAtacado1 =  mapa.ContenidoPosicion(posfin).serEnLaCeldaAerea();
			
			verificarSiPuedeAtacarEnRangoAereo(unidadQAtaca,posini,posfin);
			verificarPropiedadAtaque(unidadQAtaca);
			if ( serAtacado1 != null ) unidadQAtaca.atacarAire(serAtacado1);
			
			if ( serAtacado1.estaMuerto()) mapa.borrarSerAereo(serAtacado1);
			
			turnos.agregarQueAtaco(unidadQAtaca);
		}
		catch (NoEsPosibleAtacarException e){
			return false;
		}
		return true;
	}
	
	
	private void verificarSiPuedeAtacarEnRangoAereo(UnidadDeAtaque unidadQAtaca, Posicion posicionInicial , Posicion posicionFinal){
		if (unidadQAtaca.rangoAtaqueAereo()>posicionInicial.distancia(posicionFinal))
			throw new NoEsPosibleAtacarException();
	}
	
	

	private void verificarSiPuedeAtacarEnRangoTerrestre(UnidadDeAtaque unidadQAtaca,
			Posicion posicionInicial, Posicion posicionFinal) {
		if (unidadQAtaca.rangoAtaqueTerrestre()>posicionInicial.distancia(posicionFinal))
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
			Ser serAtacado =  mapa.ContenidoPosicion(posfin).serEnLaCeldaTerrestre();
			verificarSiPuedeAtacarEnRangoTerrestre(unidadQAtaca,posini,posfin);
			verificarPropiedadAtaque(unidadQAtaca);
			if ( serAtacado != null) {
				unidadQAtaca.atacarTierra(serAtacado);	
				if ( serAtacado.estaMuerto()){ 
					mapa.borrarSerTerrestre(serAtacado);
					for(Jugador jugador: jugadores){
						if(jugador.esColor(serAtacado.color())){
							jugador.quitarPoblacion(serAtacado.suministro());
						}
					}
				}
			}	
			turnos.agregarQueAtaco(unidadQAtaca);
		}
		catch (NoEsPosibleAtacarException e){
			return false;
		}
		return true;
	}	
	
	public void ataqueMagico(AtaqueMagico ataque , int filIni ,int colIni, int filFinal,int colFinal ){
		Posicion posini = new Posicion(filIni,colIni);
		Posicion posfin = new Posicion(filFinal,colFinal);
		UnidadMagica unidadQAtaca = (UnidadMagica) mapa.ContenidoPosicion(posini).serEnLaCeldaTerrestre();
		// si no puede aplicar magia -> lanzar error
		// si no es su turno , -> error				
		switch(ataque){
		case RADIACION:
			Unidad unidadAtacada = (Unidad) mapa.ContenidoPosicion(posfin).serEnLaCeldaTerrestre();
			// validar que sea un ser
			((NaveCiencia) unidadQAtaca).radiacion(unidadAtacada);
		case EMP:
			ataqueMagicoEnRadio(unidadQAtaca,posfin);
			
		case TORMENTA:
			ataqueMagicoEnRadio(unidadQAtaca,posfin);
			
		case ALUCINACION:
			// validar que pueda usarlo
			ArrayList<AltoTemplarioInteface> copias =((AltoTemplario)unidadQAtaca).alucinacion();
			for (int i = 0; i< copias.size(); i++){
				mapa.ponerUnidadEnLaCeldaLibreMasCercana(unidadQAtaca,(Unidad)copias.get(i));
			}
		}
		
	}
	
	
	private void ataqueMagicoEnRadio(UnidadMagica unidad, Posicion pos){
		ArrayList <Unidad> atacados = mapa.calcularRadio(pos);
		unidad.ataqueRadio(atacados);
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
