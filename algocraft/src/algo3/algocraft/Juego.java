package algo3.algocraft;

import java.util.ArrayList;
import java.util.HashMap;

import algo3.algocraft.controlador.Id;
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
	
	public int gasJugadorActual(){
		return turnos.turnoActual().GasVespeno();
	}
	
	public int mineralJugadorActual(){
		return turnos.turnoActual().Minerales();
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
	
	public Color ColorActual() {
		return turnos.turnoActual().color();
	}

	public boolean hayGanador(){
		//return (mapa.seresDeJugador(turnos.turnoActual().color()).isEmpty());
		for(Jugador juga :  jugadores){
			if(juga.color() != turnos.turnoActual().color()){
				if(!mapa.seresDeJugador(juga.color()).isEmpty()) return false;
			}
		}
		return true;
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
	public void subirAlTransporte(int filaTransportable , int columnaTransportable, int filaTransportador,int columnaTransportador) throws NoEsPosibleSubirException{
		Posicion posicionTransportable=new Posicion(filaTransportable, columnaTransportable);
		Posicion posicionTransportador=new Posicion(filaTransportador, columnaTransportador);
		Transportable transportable = (Transportable) mapa.ContenidoPosicion(posicionTransportable).serEnLaCeldaTerrestre();
		UnidadDeTransporte transporte = (UnidadDeTransporte) mapa.ContenidoPosicion(posicionTransportador).serEnLaCeldaTerrestre();
		verificarPropiedadUnidad((Unidad)transporte);
		verificarPropiedadUnidad((Unidad)transportable);
		mapa.subirAUnidadDeTransporte(posicionTransportable, posicionTransportador);
		turnos.agregarMovido((Unidad)transportable);
	}
	public void bajarDelTransporte(int filaTransportador,int columnaTransportador) throws NoHayUnidadesEnElTransporte{
		Posicion posicionTransportador=new Posicion(filaTransportador, columnaTransportador);
		UnidadDeTransporte transporte = (UnidadDeTransporte) mapa.ContenidoPosicion(posicionTransportador).serEnLaCeldaTerrestre();
		verificarPropiedadUnidad((Unidad)transporte);
		mapa.bajarDeUnidadDeTransporte(posicionTransportador);
	}
	public void elevar(int fila,int columna){
		Posicion posicionFinal=new Posicion(fila, columna);
		Unidad unidadAMover = (Unidad) mapa.ContenidoPosicion(posicionFinal).serEnLaCeldaTerrestre();
		verificarPropiedadUnidad(unidadAMover);
		mapa.elevar(new Posicion(fila,columna));
	}
	public void descender(int fila,int columna){
		Posicion posicionFinal=new Posicion(fila, columna);
		Unidad unidadAMover = (Unidad) mapa.ContenidoPosicion(posicionFinal).serEnLaCeldaAerea();
		verificarPropiedadUnidad(unidadAMover);
		mapa.descender(new Posicion(fila,columna));
	}
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
	private void verificaElEdificioPuedeCrearEstaUnidad(int id,int idEdificio) {
		if(id!=idEdificio){
			throw new ElEdificioNoPuedeCrearEstaUnidad();
		}		
	}
	
	public void crearCreadorAereos(int fil,int col){
		verificarSiCeldaEstaOcupado(fil,col);
		fabricas.get(turnos.turnoActual()).fabricarCreadorAereos(new Posicion(fil,col)); 
		ponerEdificioEnConstruccion(fil,col);
	}
	public void crearCreadorTerrestres(int fil,int col){
		verificarSiCeldaEstaOcupado(fil,col);
		fabricas.get(turnos.turnoActual()).fabricarCreadorTerrestres(new Posicion(fil,col)); 
		ponerEdificioEnConstruccion(fil,col);
	}
	public void crearCreadorSoldados(int fil,int col){
		verificarSiCeldaEstaOcupado(fil,col);
		fabricas.get(turnos.turnoActual()).fabricarCreadorSoldados(new Posicion(fil,col)); 
		ponerEdificioEnConstruccion(fil,col);
	}
	public void crearSumaPoblacion(int fil,int col){
		verificarSiCeldaEstaOcupado(fil,col);
		fabricas.get(turnos.turnoActual()).fabricarSumaPoblacion(new Posicion(fil,col)); 		
		ponerEdificioEnConstruccion(fil,col);
	}
	public void crearRecolectableGas(int fil,int col) {
		verificarSiCeldaEstaOcupado(fil,col);
		VolcanGasVespeno volcan=(VolcanGasVespeno) mapa.ContenidoPosicion(new Posicion(fil,col)).fuenteRecurso();
		fabricas.get(turnos.turnoActual()).fabricarRecolectableGas(volcan,new Posicion(fil,col)); 		
		ponerEdificioEnConstruccion(fil,col);
	}
	public void crearRecolectableMinerales(int fil,int col) {
		verificarSiCeldaEstaOcupado(fil,col);
		Mineral mineral=(Mineral) mapa.ContenidoPosicion(new Posicion(fil,col)).fuenteRecurso();
		fabricas.get(turnos.turnoActual()).fabricarRecolectableMinerales(mineral,new Posicion(fil,col)); 
		ponerEdificioEnConstruccion(fil,col);
	}
	public void ponerEdificioEnConstruccion(int fil, int col){
		mapa.ponerTerrestre(new Posicion(fil,col), new EdificioEnConstruccion(turnos.turnoActual().color()));
	}

	public Boolean crearAltoTemplario(int fil, int col){
		Ser ed=(Ser) mapa.ContenidoPosicion(new Posicion(fil,col)).serEnLaCeldaTerrestre();
		verificaElEdificioPuedeCrearEstaUnidad(Id.ArchivoTemplario.numero(),ed.devolverID());
		return ((ArchivosTemplarios) ed).crearAltoTemplario(turnos.turnoActual());
	}
	public Boolean crearScout(int fil, int col){
		Ser ed=(Ser) mapa.ContenidoPosicion(new Posicion(fil,col)).serEnLaCeldaTerrestre();
		verificaElEdificioPuedeCrearEstaUnidad(Id.PuertoEstelarProtoss.numero(),ed.devolverID());
		return ((PuertoEstelarProtoss) mapa.ContenidoPosicion(new Posicion(fil,col)).serEnLaCeldaTerrestre()).crearScout(turnos.turnoActual());
	}
	public Boolean crearMarine(int fil, int col){
		Ser ed=(Ser) mapa.ContenidoPosicion(new Posicion(fil,col)).serEnLaCeldaTerrestre();
		verificaElEdificioPuedeCrearEstaUnidad(Id.Barraca.numero(),ed.devolverID());
		return ((Barraca) mapa.ContenidoPosicion(new Posicion(fil,col)).serEnLaCeldaTerrestre()).crearMarine(turnos.turnoActual());
	}
	public Boolean crearDragon(int fil,int col){
		Ser ed=(Ser) mapa.ContenidoPosicion(new Posicion(fil,col)).serEnLaCeldaTerrestre();
		verificaElEdificioPuedeCrearEstaUnidad(Id.Acceso.numero(),ed.devolverID());
		return ((Acceso) mapa.ContenidoPosicion(new Posicion(fil,col)).serEnLaCeldaTerrestre()).crearDragon(turnos.turnoActual());
	}
	public Boolean crearNaveCiencia(int fil,int col){
		Ser ed=(Ser) mapa.ContenidoPosicion(new Posicion(fil,col)).serEnLaCeldaTerrestre();
		verificaElEdificioPuedeCrearEstaUnidad(Id.PuertoEstelarTerran.numero(),ed.devolverID());
		return ((PuertoEstelarTerran)  mapa.ContenidoPosicion(new Posicion(fil,col)).serEnLaCeldaTerrestre()).crearNaveCiencia(turnos.turnoActual());
	}
	public Boolean crearNaveTransporteProtoss(int fil,int col){
		Ser ed=(Ser) mapa.ContenidoPosicion(new Posicion(fil,col)).serEnLaCeldaTerrestre();
		verificaElEdificioPuedeCrearEstaUnidad(Id.PuertoEstelarProtoss.numero(),ed.devolverID());
		return ((PuertoEstelarProtoss) mapa.ContenidoPosicion(new Posicion(fil,col)).serEnLaCeldaTerrestre()).crearNaveTransporteProtoss(turnos.turnoActual());
	}
	public Boolean crearNaveTransporteTerran(int fil,int col){
		Ser ed=(Ser) mapa.ContenidoPosicion(new Posicion(fil,col)).serEnLaCeldaTerrestre();
		verificaElEdificioPuedeCrearEstaUnidad(Id.PuertoEstelarTerran.numero(),ed.devolverID());
		return ((PuertoEstelarTerran) mapa.ContenidoPosicion(new Posicion(fil,col)).serEnLaCeldaTerrestre()).crearNaveTransporteTerran(turnos.turnoActual());
	}
	public Boolean crearEspectro(int fil,int col){
		Ser ed=(Ser) mapa.ContenidoPosicion(new Posicion(fil,col)).serEnLaCeldaTerrestre();
		verificaElEdificioPuedeCrearEstaUnidad(Id.PuertoEstelarTerran.numero(),ed.devolverID());
		return ((PuertoEstelarTerran) mapa.ContenidoPosicion(new Posicion(fil,col)).serEnLaCeldaTerrestre()).crearEspectro(turnos.turnoActual());
	}
	public Boolean crearGolliat(int fil,int col){
		Ser ed=(Ser) mapa.ContenidoPosicion(new Posicion(fil,col)).serEnLaCeldaTerrestre();
		verificaElEdificioPuedeCrearEstaUnidad(Id.Fabrica.numero(),ed.devolverID());
		return ((Fabrica) mapa.ContenidoPosicion(new Posicion(fil,col)).serEnLaCeldaTerrestre()).crearGolliat(turnos.turnoActual());
	}
	public Boolean crearZealot(int fil ,int col){
		Ser ed=(Ser) mapa.ContenidoPosicion(new Posicion(fil,col)).serEnLaCeldaTerrestre();
		verificaElEdificioPuedeCrearEstaUnidad(Id.Acceso.numero(),ed.devolverID());
		return ((Acceso) mapa.ContenidoPosicion(new Posicion(fil,col)).serEnLaCeldaTerrestre()).crearZealot(turnos.turnoActual());		
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
		if (unidadQAtaca.rangoAtaqueTerrestre()<posicionInicial.distancia(posicionFinal))
			throw new NoEsPosibleAtacarException();
	}
	
	private void verificarPropiedadAtaque(Unidad unidad) {
		if (!turnos.turnoActual().esColor(unidad.color) || turnos.yaAtaco(unidad))
			throw new NoEsPosibleAtacarException();
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
	
	public void ataqueRadiacion(int filIni, int colIni, int filFinal,
			int colFinal) {
		Posicion posini = new Posicion(filIni, colIni);
		Posicion posfin = new Posicion(filFinal, colFinal);
		NaveCiencia unidadQAtaca = (NaveCiencia) mapa.ContenidoPosicion(posini)
				.serEnLaCeldaTerrestre();
		Unidad unidadAtacada = (Unidad) mapa.ContenidoPosicion(posfin)
				.serEnLaCeldaTerrestre();
		unidadQAtaca.radiacion(unidadAtacada);
	}

	public void ataqueAlucinacion(int filIni, int colIni) {
		Posicion posini = new Posicion(filIni, colIni);
		AltoTemplario unidadQAtaca = (AltoTemplario) mapa.ContenidoPosicion(
				posini).serEnLaCeldaTerrestre();
		ArrayList<AltoTemplarioInteface> copias = unidadQAtaca.alucinacion();
		for (int i = 0; i < copias.size(); i++) {
			mapa.ponerUnidadEnLaCeldaLibreMasCercana((Ser) unidadQAtaca,
					(Unidad) copias.get(i));
		}

	}

	/* sirve para EMP y para Tormenta */
	public ArrayList<Unidad> ataqueMagicoEnRadio(int filIni, int colIni, int filFinal,
			int colFinal) {
		Posicion posini = new Posicion(filIni, colIni);
		Posicion posfin = new Posicion(filFinal, colFinal);
		UnidadMagica unidadQAtaca = (UnidadMagica) mapa.ContenidoPosicion(
				posini).serEnLaCeldaTerrestre();
		ArrayList<Unidad> atacados = mapa.calcularRadio(posfin);
		unidadQAtaca.ataqueRadio(atacados);
		return atacados;
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
	
	/* Devuelve un hasmap - clave posicion - valor un array int [color , ser]*/ 
	public HashMap<Posicion,int[]> grillaColorUnidadTerrestre(){
		HashMap<Posicion,int[]> posicionYDibujable =  new HashMap<Posicion,int[]>();
		int ancho = mapa.ancho();
		int alto = mapa.alto();
		ArrayList<Posicion>visionJugadorActual =this.visionJugadorActual();
		for ( int i = 0; i< ancho+1; i++){
			for ( int j = 0 ; j< alto+1 ; j++){
				Posicion pos= new Posicion(i,j);
				int[] colorUnidad = new int[2];
				if(visionJugadorActual.contains(pos)){
					Celda celda = mapa.ContenidoPosicion(new Posicion(i,j));
					if (celda.hayFuenteRecurso() && !celda.ocupadoTerrestre()){ /* si hay un recurso, no se fija si hay seres parados ahi. no puede dibujar 2 cosas en 1 mismo lugar*/
						FuenteRecurso rec = celda.fuenteRecurso();
						colorUnidad[0]= Color.RECURSO.numero();
						colorUnidad[1]= rec.devolverID();
						posicionYDibujable.put(new Posicion (i,j), colorUnidad);
					}
					else if( celda.ocupadoTerrestre()){
						Ser ser = celda.serEnLaCeldaTerrestre();
						colorUnidad[0]=  ser.numeroColor();
						colorUnidad[1] = ser.devolverID();
						posicionYDibujable.put(new Posicion(i,j),colorUnidad);
						
					}
					else {
						colorUnidad[0]=Color.RECURSO.numero();;
						colorUnidad[1]= Id.Pasto2.numero();
						posicionYDibujable.put(new Posicion(i,j),colorUnidad);
					}
				}else{
					colorUnidad[0]=Color.RECURSO.numero();;
					colorUnidad[1]= Id.Negro.numero();
					posicionYDibujable.put(new Posicion(i,j),colorUnidad);
				}
			}
		}
		return posicionYDibujable;
	
	}
	
	/* Devuelve un hasmap - clave posicion - valor un array int [color , ser]*/ 
	public HashMap<Posicion,int[]> grillaColorUnidadAerea(){
		HashMap<Posicion,int[]> posicionYDibujable =  new HashMap<Posicion,int[]>();
		int ancho = mapa.ancho();
		int alto = mapa.alto();
		ArrayList<Posicion>visionJugadorActual =this.visionJugadorActual();
		for ( int i = 0; i< ancho+1 ; i++){
			for ( int j = 0 ; j< alto+1 ; j++){
				Posicion pos= new Posicion(i,j);
				int[] colorUnidad = new int[2];
				if(visionJugadorActual.contains(pos)){
					Celda celda = mapa.ContenidoPosicion(new Posicion(i,j));
						 if( celda.ocupadoAerea()){
							Ser ser = celda.serEnLaCeldaAerea();
							colorUnidad[0]=  ser.numeroColor();
							colorUnidad[1] = ser.devolverID();
							posicionYDibujable.put(new Posicion(i,j),colorUnidad);
						}
						else {
							colorUnidad[0]=Color.RECURSO.numero();;
							colorUnidad[1]= Id.Pasto1.numero();
							posicionYDibujable.put(new Posicion(i,j),colorUnidad);
						}
				}else{
					colorUnidad[0]=Color.RECURSO.numero();;
					colorUnidad[1]= Id.Negro.numero();
					posicionYDibujable.put(new Posicion(i,j),colorUnidad);
				}
			}	
		}
		return posicionYDibujable;

	}
	private ArrayList<Posicion>visionJugadorActual(){
		return mapa.visible(turnos.turnoActual().color());
	}
	
	public String[] queEdificioPuedeConstruirJugadorActual(){
		return turnos.turnoActual().edificiosPuedeConstruir();
	}
	
	public String[] queUnidadesPuedeConstruirJugadorActual(){
		return turnos.turnoActual().unidadesPuedeConstruir();
	}
	
	public Ser queHayEnCeldaTerrestre(int fila, int columna){
		return mapa.ContenidoPosicion(new Posicion(fila,columna)).serEnLaCeldaTerrestre();
	}

	public String razaActual() {
		return turnos.turnoActual().tipoRaza().toString();
	}
	public Ser queHayEnCeldaAerea(int fila, int columna){
		return mapa.ContenidoPosicion(new Posicion(fila,columna)).serEnLaCeldaAerea();
	}

	
	public AtaqueMagico[] ataquesMagicosQueTieneAire(int fil, int col){
	UnidadMagica unidad = (UnidadMagica) this.ContenidoFilaColumna(fil, col).serEnLaCeldaAerea();
	return unidad.devolverAtaques();
	}
	

	public AtaqueMagico[] ataquesMagicosQueTieneTierra(int fil, int col){
	Ser ser =  this.ContenidoFilaColumna(fil, col).serEnLaCeldaTerrestre();
	return ser.devolverAtaques();
	}
		
}
