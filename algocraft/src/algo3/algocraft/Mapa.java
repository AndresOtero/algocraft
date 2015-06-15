package algo3.algocraft;



import java.util.ArrayList;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.HashMap;
import java.util.Map;

import algo3.algocraft.edificios.Asimilador;
import algo3.algocraft.edificios.EdificioCreador;
import algo3.algocraft.edificios.EdificioDeRecurso;
import algo3.algocraft.edificios.Refineria;
import algo3.algocraft.edificios.SumaPoblacion;
import algo3.algocraft.exceptions.*;
import algo3.algocraft.unidades.Unidad;

public class Mapa {
	private static Mapa instancia = null;
	private Map<Posicion, Celda> mapa = null;
	private int ancho;
	private int alto;
	private Map<Color, ArrayList<Ser>> seres;
	private Map<Color, ArrayList<EdificioDeRecurso>> edificiosDeGas;
	private Map<Color, ArrayList<EdificioDeRecurso>> edificiosDeMineral;
	private Map<Color, ArrayList<EdificioCreador>> edificiosCreadores;
	private Map<Color, ArrayList<SumaPoblacion>> edificiosSumaPoblacion;
	ArrayList<Jugador> jugadores;
	
	/******************************
	 * 
	 * 
	 * Comienzo Inicializacion mapa
	 * 
	 * 
	 ******************************/
	private Mapa(int ancho, int largo, ArrayList<Jugador> jugadores) {
		this.mapa = new HashMap<Posicion, Celda>();
		this.seres = new HashMap<Color, ArrayList<Ser>>();
		this.edificiosDeGas = new HashMap<Color, ArrayList<EdificioDeRecurso>>();
		this.edificiosDeMineral = new HashMap<Color, ArrayList<EdificioDeRecurso>>();
		this.edificiosCreadores = new HashMap<Color, ArrayList<EdificioCreador>>();
		this.edificiosSumaPoblacion = new HashMap<Color, ArrayList<SumaPoblacion>>();
		this.jugadores = jugadores;
		this.ancho = ancho;
		this.alto = largo;
		for (int i = 0; i <= this.ancho; i++) {
			for (int j = 0; j <= this.alto; j++) {
				Posicion p = new Posicion(i, j);
				Celda c = new Celda();
				mapa.put(p, c);
			}
		}
		inicializarMapa();
	}
	private VolcanGasVespeno inicializarVolcanEnMapa(Posicion pos){
		VolcanGasVespeno volcan = new VolcanGasVespeno();
		Celda celda = mapa.get(pos);
		celda.agregarFuenteRecurso(volcan);
		return volcan;
	}
	/* Metodos de Inicializacion */
	private void inicializarMapa() {

		Posicion pos1 = new Posicion(1, 1);
		VolcanGasVespeno volcan1 = inicializarVolcanEnMapa(pos1);
		Posicion pos2 = new Posicion(this.ancho, this.alto);
		VolcanGasVespeno volcan2 = inicializarVolcanEnMapa(pos2);
		Posicion pos3 = new Posicion(1, this.alto);
		inicializarVolcanEnMapa(pos3);
		Posicion pos4 = new Posicion(this.ancho, 1);
		inicializarVolcanEnMapa(pos4);
		
		
		
		/*DESDE ACA HASTA ALLA HAY QUE REFACTORIZAR BIEN PIOLA*/
		Jugador jugador1 = jugadores.get(0);
		TipoRaza tipo = jugador1.tipoRaza();
		Ser edificioDeVolcan;
		if (tipo == TipoRaza.PROTOSS){
			edificioDeVolcan = new Asimilador(volcan1, jugador1.color(),pos1);
		}
		else {
			edificioDeVolcan = new Refineria(volcan1, jugador1.color(),pos1);
		}
		ponerTerrestre(pos1,edificioDeVolcan);
		
		Jugador jugador2 = jugadores.get(1);
		TipoRaza tipo2 = jugador2.tipoRaza();
		Ser edificioDeVolcan2;
		if (tipo2 == TipoRaza.PROTOSS){
			edificioDeVolcan2 = new Asimilador(volcan2, jugador2.color(),pos2);
		}
		else {
			edificioDeVolcan2 = new Refineria(volcan2, jugador2.color(),pos2);
		}
		ponerTerrestre(pos2,edificioDeVolcan2);
		/*
		 * 
		 *HASTA ACA HAY QUE REFACTORIZAR BIEN PIOLA
		 * 
		 * 
		 */
		
		inicializarEsquina(0,5,0,5);
		inicializarEsquina(this.ancho-5,this.ancho,0,5);
		inicializarEsquina(0,5,this.alto-5,this.alto);
		inicializarEsquina(this.ancho-5,this.ancho,this.alto-5,this.alto);
		
	}
	private void inicializarEsquina(int inicioX,int finX,int inicioY,int finY){
		for(int i=0;i<5;i++){
			int x = aleatorio(inicioX,finX);
			int y = aleatorio(inicioY,finY);
			Posicion pos = new Posicion(x,y);
			if (estaVaciaTerrestre(pos) == false) i--;
			else{
				Celda celda = mapa.get(pos);
				Mineral mineral = new Mineral();
				celda.agregarFuenteRecurso(mineral);
			}
		}
		
	}
	
	private int aleatorio(int min,int max){
		/*Devuelve un numero aleatorio entre dos recibidos por parametro*/
		return (int)(Math.random()*(max-min))+min;		
	}
	
	
	private static void reiniciarMapa(int fil,int col, ArrayList<Jugador> jugadores2){
		instancia = new Mapa(fil, col, jugadores2);					
	}
	/******************************
	 * 
	 * 
	 * Comienzo Terrestre/Aereo
	 * 
	 * 
	 ******************************/
	
	/* Metodos de ubicacion de unidades y edificios */
	public void ponerTerrestre(Posicion pos, Ser ser) {
		Celda celda = mapa.get(pos);
		if (celda.ocupadoTerrestre()) {
			System.out.println("La celda esta ocupada");
			throw new LaCeldaTerrestreEstaOcupada();
		} else
			celda.agregarSerTerrestre(ser);
		Color color = ser.color();
		agregarSerDeColor(ser, color);
	}

	public void ponerAereo(Posicion pos, Ser ser) {
		Celda celda = mapa.get(pos);
		if (celda.ocupadoAerea()) {
			System.out.println("La celda esta ocupada");
			throw new LaCeldaAereaEstaOcupada();
		} else
			celda.agregarSerAereo(ser);
		Color color = ser.color();
		
		agregarSerDeColor(ser, color);
	}
	
	/*Agrega el ser a la lista de seres*/
	private void agregarSerDeColor(Ser ser, Color color) {
		if (seres.get(color) != null) {
			(seres.get(color)).add(ser);
		} else {
			ArrayList<Ser> seresDeColor = new ArrayList<Ser>();
			seresDeColor.add(ser);
			seres.put(color, seresDeColor);
		}
	}
	
	/* Metodos de movimiento de unidades*/
	public void moverTerrestre(Posicion posicionInicial, Posicion posicionFinal) {
		Celda celdaFinal = mapa.get(posicionFinal);
		if (celdaFinal.ocupadoTerrestre()) {
			System.out.println("ESTA OCUPADO Terrestre");
			throw new NoEsPosibleMoverException();
		}
		Celda celdaInicial = mapa.get(posicionInicial);
		Unidad unidadAMover = (Unidad) celdaInicial.serEnLaCeldaTerrestre();
		ArrayList<Posicion> camino = this.encontrarMinimoCamino(
				posicionInicial, posicionFinal, unidadAMover.movimiento());
		if (camino.isEmpty() || unidadAMover.movimientoPosible(posicionInicial, posicionFinal)) {
			System.out.println("No se pudo mover ");
			throw new NoEsPosibleMoverException();
		}
		
		ponerTerrestre(posicionFinal, unidadAMover);
		unidadAMover.cambiarPosicion(posicionFinal);
		celdaInicial.desocuparTerrestre();
	}

	public void moverAerea(Posicion posicionInicial, Posicion posicionFinal) {
		// aca deberia mover una unidad a la fila y columna que le pasan
		// si no se puede (ocupado) deberia lanzar NoEsPosibleMoverException.
		Celda celdaFinal = mapa.get(posicionFinal);
		if (celdaFinal.ocupadoAerea()) {
			System.out.println("ESTA OCUPADO Aerea");
			throw new NoEsPosibleMoverException();
		}
		
		Celda celdaInicial = mapa.get(posicionInicial);
		Unidad unidadAMover = (Unidad) celdaInicial.serEnLaCeldaAerea();
		ArrayList<Posicion> camino = this.encontrarMinimoCamino(
				posicionInicial, posicionFinal, unidadAMover.movimiento());
		
		if (camino.isEmpty() || unidadAMover.movimientoPosible(posicionInicial, posicionFinal)) {
			System.out.println("No se pudo mover ");
			throw new NoEsPosibleMoverException();
		}
		ponerAereo(posicionFinal, unidadAMover);
		unidadAMover.cambiarPosicion(posicionFinal);
		celdaInicial.desocuparAerea();
	}
	
	 /*************************
	 * 
	 * 
	 * Comienzo Edificio Recurso
	 * 
	 * 
	 *****************************/
	
	private void ponerEdificioDeRecurso(Posicion pos, EdificioDeRecurso edificio) {
		Celda celda = mapa.get(pos);
		if (celda.fuenteRecurso() == null) {
			System.out.println("NO HAY RECURSO AHI");
			throw new NoHayRecursoEnEsaPosicionException();
		} else {
			ponerTerrestre(pos, edificio);
		}
	}
	
	public void ponerEdificioGas(Posicion pos, EdificioDeRecurso edificio) {
		ponerEdificioDeRecurso(pos, edificio);
		agregarEdificioDeRecursosDeColor(edificiosDeGas, edificio,
				edificio.color());
	}

	public void ponerEdificioMineral(Posicion pos, EdificioDeRecurso edificio) {
		ponerEdificioDeRecurso(pos, edificio);
		agregarEdificioDeRecursosDeColor(edificiosDeMineral, edificio,
				edificio.color());
	}
	
	/*agrega edificio de recurso a las listasssss*/
	private void agregarEdificioDeRecursosDeColor(
			Map<Color, ArrayList<EdificioDeRecurso>> edificios,
			EdificioDeRecurso edificio, Color color) {
		
		if (edificios.get(color) != null) {
			(edificios.get(color)).add(edificio);
		} else {
			ArrayList<EdificioDeRecurso> edificiosDeRecursoDeColor = new ArrayList<EdificioDeRecurso>();
			edificiosDeRecursoDeColor.add(edificio);
			edificios.put(color, edificiosDeRecursoDeColor);
		}
	}
	 /********************************
	 * 
	 * 
	 * Comienzo Edificio SumaPoblacion
	 * 
	 * 
	 ********************************/
	public void ponerEdificioSumaPoblacion(Posicion pos, Edificio edificio){
		Celda celda = mapa.get(pos);
		if (!celda.ocupadoTerrestre()){
			ponerTerrestre(pos,edificio);
			agregarSumaPoblacion(edificiosSumaPoblacion,edificio,edificio.color());
		}
		
		else throw new LaCeldaTerrestreEstaOcupada();
		
		for (Jugador jugador : jugadores){
			if (jugador.color() == edificio.color()) 
				jugador.agregarEspacionParaPoblacion();
		}
		ponerTerrestre(pos,edificio);
	}
	
	
	private void agregarSumaPoblacion(
			Map<Color, ArrayList<SumaPoblacion>> edificios,
			Edificio edificio, Color color) {
		if (edificios.get(color) != null) (edificios.get(color)).add((SumaPoblacion) edificio);
		else {
			ArrayList<SumaPoblacion> edificiosSumaPoblacion = new ArrayList<SumaPoblacion>();
			edificiosSumaPoblacion.add((SumaPoblacion) edificio);
			edificios.put(color, edificiosSumaPoblacion);
		}
		
		
	}
	/******************************
	 * 
	 * 
	 * Comienzo EdificioCreador
	 * 
	 * 
	 ******************************/
	
	public void ponerEdificioCreador(Posicion pos, EdificioCreador edificio){
		Celda celda = mapa.get(pos);
		if (!celda.ocupadoTerrestre()){
			ponerTerrestre(pos,edificio);
			agregarEdificioCreador(edificiosCreadores,edificio,edificio.color());
		}
		
		else throw new LaCeldaTerrestreEstaOcupada();
	}
	
	/*Agrega edificiocreador a las listas*/
	private void agregarEdificioCreador(Map<Color, ArrayList<EdificioCreador>> edificios,
			EdificioCreador edificio, Color color){
		
		if (edificios.get(color) != null) (edificios.get(color)).add(edificio);
		else {
			ArrayList<EdificioCreador> edificiosCreadores = new ArrayList<EdificioCreador>();
			edificiosCreadores.add(edificio);
			edificios.put(color, edificiosCreadores);
		}	
	}

	

	/*************************
	 * 
	 *  
	 *  Metodos de Busqueda
	 *  
	 *************************/
	public Celda ContenidoPosicion(Posicion pos) {
		return mapa.get(pos);
	}

	public Boolean estaVaciaTerrestre(Posicion pos) {
		Celda celda = this.ContenidoPosicion(pos);
		Boolean estaVacia = !(celda.ocupadoTerrestre());
		return estaVacia;
	}

	public Boolean estaVaciaAereo(Posicion pos) {
		Celda celda = this.ContenidoPosicion(pos);
		Boolean estaVacia = !(celda.ocupadoAerea());
		return estaVacia;
	}
	
	public void ponerUnidadEnLaCeldaLibreMasCercana(Ser ed,Unidad unidad){
		Posicion pos=this.buscarPosicionDeSer(ed);
		ArrayList<Posicion> adyacentes = adyacentes(pos);
		Posicion posicionVacia= this.buscarLibreMasCercanoRecursivo(pos, adyacentes);
		this.ponerTerrestre(posicionVacia, unidad);
	}
	
	private Posicion buscarLibreMasCercanoRecursivo(Posicion pos,ArrayList<Posicion> adyacentes ){
		for(Posicion ady:adyacentes){
			if(this.estaVaciaTerrestre(ady)){
				return ady;
			}
		}
		for(Posicion ady:adyacentes){
			adyacentes.addAll(this.adyacentes(ady));
		}
		return this.buscarLibreMasCercanoRecursivo(pos, adyacentes);
	}

	private ArrayList<Posicion> adyacentes(Posicion pos) {
		ArrayList<Posicion> adyacentes = new ArrayList<Posicion>();
		for (int i = -1; i < 2; i = i + 2) {
			if (this.ancho >= i + pos.abscisa()) {
				adyacentes.add(new Posicion(pos.abscisa() + i, pos.ordenada()));
			}
			if (this.alto >= i + pos.ordenada()) {
				adyacentes.add(new Posicion(pos.abscisa(), pos.ordenada() + i));
			}
		}
		return adyacentes;
	}
	
	public ArrayList<Unidad> calcularRadio(Posicion pos) {
		ArrayList<Unidad> unidadesAlcanzadas = new ArrayList<Unidad>();
		for (int i = -1; i <= 1; i++) {
			for (int j = -1; j <= 1; j++) {
				Celda celda = this.ContenidoPosicion(new Posicion(pos.abscisa()+i, pos.ordenada()+ j));
				if (celda.ocupadoTerrestre()){
					unidadesAlcanzadas.add((Unidad) celda.serEnLaCeldaTerrestre());
				}
				if (celda.ocupadoAerea()){
					unidadesAlcanzadas.add((Unidad) celda.serEnLaCeldaAerea());
				}
			}
		}
		return unidadesAlcanzadas;
	}

	/******************************
	 * 
	 * 
	 * DEVOLUCIONES
	 * 
	 * 
	 ******************************/

	public ArrayList<Ser> seresDeJugador(Color color) {
		if(seres.get(color)==null){
			ArrayList<Ser> seresDeColor = new ArrayList<Ser>();
			seres.put(color, seresDeColor);
		}
		return (seres.get(color));
	}

	public ArrayList<EdificioDeRecurso> edificioDeGas(Color color) {
		if(edificiosDeGas.get(color)==null){
			ArrayList<EdificioDeRecurso> edificios = new ArrayList<EdificioDeRecurso>();
			edificiosDeGas.put(color, edificios);
		}
		return (edificiosDeGas.get(color));
	}

	public ArrayList<EdificioDeRecurso> edificioDeMineral(Color color) {
		if(edificiosDeMineral.get(color)==null){
			ArrayList<EdificioDeRecurso> edificios = new ArrayList<EdificioDeRecurso>();
			edificiosDeMineral.put(color, edificios);
		}
		return (edificiosDeMineral.get(color));
	}
	
	public ArrayList<EdificioCreador> edificioCreador(Color color) {
		if(edificiosCreadores.get(color)==null){
			ArrayList<EdificioCreador> edificios = new ArrayList<EdificioCreador>();
			edificiosCreadores.put(color, edificios);
		}
		return (edificiosCreadores.get(color));
	}
	
	
	private ArrayList<Posicion> encontrarMinimoCamino(Posicion posicionInicial,
			Posicion posicionFinal, Movimiento movimiento) {
		/*
		 * Este metodo encuentra el minimo camino si es posible encontrarlo sino
		 * devuele una lista vacia. El movimiento es por donde se puede mover el
		 * ser por tierra o por aire
		 */
		ArrayList<Posicion> camino = new ArrayList<Posicion>();
		if (movimiento == null) {
			return camino;
		}
		ArrayList<Posicion> visitados = new ArrayList<Posicion>();

		this.encontrarMinimoCaminoRecursivo(posicionInicial, posicionFinal,
				movimiento, camino, visitados);
		return camino;
	}

	private Boolean encontrarMinimoCaminoRecursivo(Posicion posicionInicial,Posicion posicionFinal, Movimiento movimiento,
			ArrayList<Posicion> camino, ArrayList<Posicion> visitados) {
		/*Backtracking*/
		if (posicionFinal.equals(posicionInicial)) {
			return true;
		}
		int distanciaMinima = -1;
		Posicion posicionDistanciaMinima = null;
		ArrayList<Posicion> adyacentes = this.adyacentes(posicionInicial);
		Iterator<Posicion> iter = adyacentes.iterator();
		while (iter.hasNext()) {
			Posicion adyacente = iter.next();
			if ((!this.sePuedeMover(adyacente, movimiento))|| (visitados.contains(adyacente))) {// Si no se puede mover o fue visitado											// o fue visitado
				continue;
			}
			int distanciaAlFinal = adyacente.distancia(posicionFinal);
			if ((distanciaMinima == -1 || distanciaMinima >= distanciaAlFinal)) {
				posicionDistanciaMinima = adyacente;
				distanciaMinima = distanciaAlFinal;

			}
		}
		if (posicionDistanciaMinima == null) {// Ya visito todos los caminos
			camino.remove(posicionInicial);
			return false;
		}
		visitados.add(posicionDistanciaMinima);
		camino.add(posicionDistanciaMinima);
		if (this.encontrarMinimoCaminoRecursivo(posicionDistanciaMinima,posicionFinal, movimiento, camino, visitados)) {
			return true;
		}
		return this.encontrarMinimoCaminoRecursivo(posicionInicial,posicionFinal, movimiento, camino, visitados);
	}

	private boolean sePuedeMover(Posicion pos, Movimiento movimiento) {
		if (movimiento == Movimiento.Terrestre) {
			return this.estaVaciaTerrestre(pos);
		}
		return (this.estaVaciaAereo(pos) || this.estaVaciaTerrestre(pos));
	}

	/* ********************
	 * 
	 * 
	 * BORRADO
	 * 
	 * 
	 * *******************/
	private Posicion borrarSer(Ser ser) {
		if (seres.get(ser.color()).contains(ser)){
			seres.get(ser.color()).remove(ser);
		}
		return buscarPosicionDeSer(ser);
	}

	public void borrarSerAereo(Ser ser) {
		Posicion pos = borrarSer(ser);
		Celda celda = mapa.get(pos);
		celda.desocuparAerea();

	}
	
	public void borrarSerTerrestre(Ser ser) {
		Posicion pos = borrarSer(ser);
		Celda celda = mapa.get(pos);
		celda.desocuparTerrestre();
		
		if (edificiosCreadores.get(ser.color()).contains(ser))
			edificiosCreadores.get(ser.color()).remove(ser);
		
		if (edificiosDeGas.get(ser.color()).contains(ser))
			edificiosDeGas.get(ser.color()).remove(ser);
		
		if (edificiosDeMineral.get(ser.color()).contains(ser))
			edificiosDeMineral.get(ser.color()).remove(ser);
		
	}

	private Posicion buscarPosicionDeSer(Ser ser) {
		for (int i = 0; i <= this.ancho; i++) {
			for (int j = 0; j <= this.alto; j++) {
				Posicion p = new Posicion(i, j);
				Celda celda = mapa.get(p);
				if (ser == celda.serEnLaCeldaAerea())
					return p;
				if (ser == celda.serEnLaCeldaTerrestre())
					return p;
			}
		}

		return null;
	}

	/* *****************
	 * 
	 * 
	 * Singleton 
	 * 
	 * 
	 * ******************/
	private synchronized static void createInstance(int fil, int col,
			ArrayList<Jugador> jugadores) {
		if (instancia == null) {
			instancia = new Mapa(fil, col, jugadores);
		}
	}

	public static Mapa getInstance(int fil, int col,
			ArrayList<Jugador> jugadores) {
		if (instancia == null)
			createInstance(fil, col, jugadores);
		else reiniciarMapa(fil,col,jugadores);
		return instancia;
	}
	

}