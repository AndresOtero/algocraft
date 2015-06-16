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
	}

	public void iniciarJuego() {
		turnos = new Turnos(jugadores);
		mapa = Mapa.getInstance(15,15,jugadores); 
		inicializarRecursos();
		for (Jugador jugador: jugadores){
			if (jugador.tipoRaza() == TipoRaza.TERRAN) {
				fabricas.put(jugador, new FactoryEdificiosTerran(jugador,mapa));
			} else
			{
				fabricas.put(jugador, new FactoryEdificiosProtoss(jugador,mapa));
			}
		}
	}

	private void inicializarRecursos() {
		for (Jugador juga : jugadores) {
			juga.agregarGasVespeno(800);
			juga.agregarMineral(800);
		}
	}
	
	//Metodos de logica de juego
	public String JugadorActual() {
		return turnos.turnoActual().nombre();
	}

	public boolean hayGanador(){
		return (mapa.seresDeJugador(turnos.turnoActual().color()).isEmpty());
	}

	private void administrarRecursos() {
		Jugador jugadorActual = turnos.turnoActual();
		ArrayList<EdificioDeRecurso> edificiosDeRecursos = mapa.edificiosDeRecursos(jugadorActual.color());
		
		for (EdificioDeRecurso edificio : edificiosDeRecursos){
			edificio.recolectar(jugadorActual);
		}
	}
	
	private void administrarCreacionEdificios() {
		AbstractFactoryEdificios factory=fabricas.get(turnos.turnoActual());
		factory.pasarTurno();
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
		ArrayList<TuplaDeSeres> tuplasCreadas= new ArrayList<TuplaDeSeres>();
		for(TuplaDeSeres tupla:unidadesEnEspera ){
			Unidad unidad=(Unidad) tupla.serB();
			EdificioCreador ed=(EdificioCreador) tupla.serA();
			if(jugadorActual.agregarPoblacion(unidad.suministro())){
				mapa.ponerUnidadEnLaCeldaLibreMasCercana(ed, unidad);
				tuplasCreadas.add(tupla);
			}else{
				break;
			}
		}
		for (TuplaDeSeres tupla:tuplasCreadas){
			if (unidadesEnEspera.contains(tupla)) unidadesEnEspera.remove(tupla);
			
		}
	}
	
	public void pasarTurno() {
		turnos.avanzarTurno();
		administrarRecursos();
		administrarCreacionEdificios();
		administrarCreacionUnidades();
	}
	

	public Celda ContenidoFilaColumna(int fila, int columna) {
		return mapa.ContenidoPosicion(new Posicion(fila, columna)); 
	}
	
	//Metodos de movimiento
	public boolean moverPosicionTerrestre(int filaInicio,
			int columnaInicio, int filaDestino, int columnaDestino) {
		try {
			Posicion posicionInicial=new Posicion(filaInicio, columnaInicio);
			Posicion posicionFinal=new Posicion(filaDestino, columnaDestino);
			Unidad unidadAMover = (Unidad) mapa.ContenidoPosicion(posicionInicial).serEnLaCeldaTerrestre();
			verificarPropiedadUnidad(unidadAMover);
			mapa.moverTerrestre(posicionInicial,posicionFinal );
			turnos.agregarMovido(unidadAMover);
		} catch (NoEsPosibleMoverException e) {
			return false;
		}
		return true;
	}

	public boolean moverPosicionAereo(int filaInicio,int columnaInicio, int filaDestino, int columnaDestino) {
		try {
			Posicion posicionInicial=new Posicion(filaInicio, columnaInicio);
			Posicion posicionFinal=new Posicion(filaDestino, columnaDestino);
			Unidad unidadAMover = (Unidad) mapa.ContenidoPosicion(posicionInicial).serEnLaCeldaAerea();
			verificarPropiedadUnidad(unidadAMover);

			mapa.moverAerea(posicionInicial,posicionFinal);
			turnos.agregarMovido(unidadAMover);
		} catch (NoEsPosibleMoverException e) {
			return false;
		}
		return true;
	}
	
	//Verificaciones
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
	private void verificarSiCeldaEstaOcupado(int fil, int col) {
		if(mapa.ContenidoPosicion(new Posicion(fil,col)).ocupadoTerrestre()){
			throw new LaCeldaTerrestreEstaOcupada();
		}		
	}
	
	public void crearCreadorAereos(int fil,int col){
		verificarSiCeldaEstaOcupado(fil,col);
		fabricas.get(turnos.turnoActual()).fabricarCreadorAereos(new Posicion(fil,col)); 
		mapa.ponerTerrestre(new Posicion(fil,col), new EdificioEnConstruccion(turnos.turnoActual().color()));
	}
	public void crearCreadorTerrestres(int fil,int col){
		verificarSiCeldaEstaOcupado(fil,col);
		fabricas.get(turnos.turnoActual()).fabricarCreadorTerrestres(new Posicion(fil,col)); 
		mapa.ponerTerrestre(new Posicion(fil,col), new EdificioEnConstruccion(turnos.turnoActual().color()));
	}
	public void crearCreadorSoldados(int fil,int col){
		verificarSiCeldaEstaOcupado(fil,col);
		fabricas.get(turnos.turnoActual()).fabricarCreadorSoldados(new Posicion(fil,col)); 
		mapa.ponerTerrestre(new Posicion(fil,col), new EdificioEnConstruccion(turnos.turnoActual().color()));
	}
	public void crearSumaPoblacion(int fil,int col){
		verificarSiCeldaEstaOcupado(fil,col);
		fabricas.get(turnos.turnoActual()).fabricarSumaPoblacion(new Posicion(fil,col)); 		
		mapa.ponerTerrestre(new Posicion(fil,col), new EdificioEnConstruccion(turnos.turnoActual().color()));
	}
	public void crearRecolectableGas(int fil,int col) {
		verificarSiCeldaEstaOcupado(fil,col);
		VolcanGasVespeno volcan=(VolcanGasVespeno) mapa.ContenidoPosicion(new Posicion(fil,col)).fuenteRecurso();
		fabricas.get(turnos.turnoActual()).fabricarRecolectableGas(volcan,new Posicion(fil,col)); 		
		mapa.ponerTerrestre(new Posicion(fil,col), new EdificioEnConstruccion(turnos.turnoActual().color()));
	}
	public void crearRecolectableMinerales(int fil,int col) {
		verificarSiCeldaEstaOcupado(fil,col);
		Mineral mineral=(Mineral) mapa.ContenidoPosicion(new Posicion(fil,col)).fuenteRecurso();
		fabricas.get(turnos.turnoActual()).fabricarRecolectableMinerales(mineral,new Posicion(fil,col)); 		
		mapa.ponerTerrestre(new Posicion(fil,col), new EdificioEnConstruccion(turnos.turnoActual().color()));
	}
	

	public boolean crearUnidad(int fil, int col, Unidades unidad){
		Posicion pos = new Posicion(fil,col);
		
		EdificioCreador ed = (EdificioCreador) mapa.ContenidoPosicion(pos).serEnLaCeldaTerrestre();
		/*Horrible, refactorizar  - excepcion EDIFICIO NO CREA A X UNIDAD*/
		if (ed == null) return false;
		switch(unidad){
			case ALTOTEMPLARIO:
				((ArchivosTemplarios) ed).crearAltoTemplario(turnos.turnoActual());
				break;
			case SCOUT:
				((PuertoEstelarProtoss) ed).crearScout(turnos.turnoActual());
				break;
			case MARINE:
				((Barraca) ed).crearMarine(turnos.turnoActual());
				break;
			case DRAGON:
				((Acceso) ed).crearDragon(turnos.turnoActual());
				break;
			case NAVECIENCIA:
				((PuertoEstelarTerran) ed).crearNaveCiencia(turnos.turnoActual());
				break;
			case NAVETRANSPORTEPROTOSS:
				((PuertoEstelarProtoss) ed).crearNaveTransporteProtoss(turnos.turnoActual());
				break;
			case NAVETRANSPORTETERRAN:
				((PuertoEstelarTerran) ed).crearNaveTransporteTerran(turnos.turnoActual());
				break;
			case ESPECTRO:
				((PuertoEstelarTerran) ed).crearEspectro(turnos.turnoActual());
				break;
			case GOLLIAT:
				((Fabrica) ed).crearGolliat(turnos.turnoActual());
				break;
			case ZEALOT:
				((Acceso) ed).crearZealot(turnos.turnoActual());
				break;
			default:
				throw new NoHayEspacioException();
		}
		return true;
		 	
	}
	public void crearAltoTemplario(int fil, int col){
		((ArchivosTemplarios) mapa.ContenidoPosicion(new Posicion(fil,col)).serEnLaCeldaTerrestre()).crearAltoTemplario(turnos.turnoActual());
	}
	public void crearScout(int fil, int col){
		((PuertoEstelarProtoss) mapa.ContenidoPosicion(new Posicion(fil,col)).serEnLaCeldaTerrestre()).crearScout(turnos.turnoActual());
	}
	public void crearMarine(int fil, int col){
		((Barraca) mapa.ContenidoPosicion(new Posicion(fil,col)).serEnLaCeldaTerrestre()).crearMarine(turnos.turnoActual());
	}
	public void crearDragon(int fil,int col){
		((Acceso) mapa.ContenidoPosicion(new Posicion(fil,col)).serEnLaCeldaTerrestre()).crearDragon(turnos.turnoActual());
	}
	public void crearNaveCiencia(int fil,int col){
		((PuertoEstelarTerran)  mapa.ContenidoPosicion(new Posicion(fil,col)).serEnLaCeldaTerrestre()).crearNaveCiencia(turnos.turnoActual());
	}
	public void crearNaveTransporteProtoss(int fil,int col){
		((PuertoEstelarProtoss) mapa.ContenidoPosicion(new Posicion(fil,col)).serEnLaCeldaTerrestre()).crearNaveTransporteProtoss(turnos.turnoActual());
	}
	public void crearNaveTransporteTerran(int fil,int col){
		((PuertoEstelarTerran) mapa.ContenidoPosicion(new Posicion(fil,col)).serEnLaCeldaTerrestre()).crearNaveTransporteTerran(turnos.turnoActual());
	}
	public void crearEspectro(int fil,int col){
		((PuertoEstelarTerran) mapa.ContenidoPosicion(new Posicion(fil,col)).serEnLaCeldaTerrestre()).crearEspectro(turnos.turnoActual());
	}
	public void crearGolliat(int fil,int col){
		((Fabrica) mapa.ContenidoPosicion(new Posicion(fil,col)).serEnLaCeldaTerrestre()).crearGolliat(turnos.turnoActual());
	}
	public void crearZealot(int fil ,int col){
		((Acceso) mapa.ContenidoPosicion(new Posicion(fil,col)).serEnLaCeldaTerrestre()).crearZealot(turnos.turnoActual());		
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
			break;
		case EMP:
			ataqueMagicoEnRadio(unidadQAtaca,posfin);
			break;
			
		case TORMENTA:
			ataqueMagicoEnRadio(unidadQAtaca,posfin);
			break;
			
		case ALUCINACION:
			// validar que pueda usarlo
			ArrayList<AltoTemplarioInteface> copias =((AltoTemplario)unidadQAtaca).alucinacion();
			for (int i = 0; i< copias.size(); i++){
				mapa.ponerUnidadEnLaCeldaLibreMasCercana((Ser)unidadQAtaca,(Unidad)copias.get(i));
			}
			break;
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
		else reiniciarJuego();
		return instancia;
	}

	private static void reiniciarJuego() {
		instancia = new Juego();		
	}


}
