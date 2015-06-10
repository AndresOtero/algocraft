package algo3.algocraft;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.HashMap;
import java.util.Map;

import algo3.algocraft.edificios.CentroDeMineral;
import algo3.algocraft.edificios.EdificioCreador;
import algo3.algocraft.edificios.EdificioDeRecurso;
import algo3.algocraft.edificios.Refineria;
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
	ArrayList<Jugador> jugadores;

	private Mapa(int ancho, int largo, ArrayList<Jugador> jugadores) {
		this.mapa = new HashMap<Posicion, Celda>();
		this.seres = new HashMap<Color, ArrayList<Ser>>();
		this.edificiosDeGas = new HashMap<Color, ArrayList<EdificioDeRecurso>>();
		this.edificiosDeMineral = new HashMap<Color, ArrayList<EdificioDeRecurso>>();
		this.edificiosCreadores = new HashMap<Color, ArrayList<EdificioCreador>>();
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

	/* Metodos de Inicializacion */
	private void inicializarMapa() {
		VolcanGasVespeno volcan1 = new VolcanGasVespeno();
		VolcanGasVespeno volcan2 = new VolcanGasVespeno();
		VolcanGasVespeno volcan3 = new VolcanGasVespeno();
		VolcanGasVespeno volcan4 = new VolcanGasVespeno();
		Posicion pos1 = new Posicion(1, 1);
		Posicion pos2 = new Posicion(this.ancho, this.alto);
		Posicion pos3 = new Posicion(1, this.alto);
		Posicion pos4 = new Posicion(this.ancho, 1);
		Celda celda1 = mapa.get(pos1);
		Celda celda2 = mapa.get(pos2);
		Celda celda3 = mapa.get(pos3);
		Celda celda4 = mapa.get(pos4);
		celda1.agregarFuenteRecurso(volcan1);
		celda2.agregarFuenteRecurso(volcan2);
		
		EdificioDeRecurso edificio1= new Refineria(null,jugadores.get(0).color());
		EdificioDeRecurso edificio2= new Refineria(null,jugadores.get(1).color());
		
		ponerEdificioDeRecurso(pos1,edificio1);
		ponerEdificioDeRecurso(pos2,edificio2);
		celda3.agregarFuenteRecurso(volcan3);
		celda4.agregarFuenteRecurso(volcan4);			
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

	private void ponerEdificioDeRecurso(Posicion pos, EdificioDeRecurso edificio) {
		Celda celda = mapa.get(pos);
		if (celda.fuenteRecurso() == null) {
			System.out.println("NO HAY RECURSO AHI");
			throw new NoHayRecursoEnEsaPosicionException();
		} else {
			ponerTerrestre(pos, edificio);
		}
	}
	
	public void ponerEdificioCreador(Posicion pos, EdificioCreador edificio){
		Celda celda = mapa.get(pos);
		if (!celda.ocupadoTerrestre()){
			ponerTerrestre(pos,edificio);
			agregarEdificioCreador(edificiosCreadores,edificio,edificio.color());
		}
		else throw new LaCeldaTerrestreEstaOcupada();
	}
	
	private void agregarEdificioCreador(Map<Color, ArrayList<EdificioCreador>> edificios,
			EdificioCreador edificio, Color color){
		
		if (edificios.get(color) != null) (edificios.get(color)).add(edificio);
		else {
			ArrayList<EdificioCreador> edificiosCreadores = new ArrayList<EdificioCreador>();
			edificiosCreadores.add(edificio);
			edificios.put(color, edificiosCreadores);
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

	private void agregarSerDeColor(Ser ser, Color color) {
		if (seres.get(color) != null) {
			(seres.get(color)).add(ser);
		} else {
			ArrayList<Ser> seresDeColor = new ArrayList<Ser>();
			seresDeColor.add(ser);
			seres.put(color, seresDeColor);
		}
	}

	/* Metodos de Busqueda */
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


	public ArrayList<Ser> seresDeJugador(Color color) {
		return (seres.get(color));
	}

	public ArrayList<EdificioDeRecurso> edificioDeGas(Color color) {
		return (edificiosDeGas.get(color));
	}

	public ArrayList<EdificioDeRecurso> edificioDeMineral(Color color) {
		return (edificiosDeMineral.get(color));
	}
	
	public ArrayList<EdificioCreador> edificioCreador(Color color) {
		return (edificiosCreadores.get(color));
	}
	

	/* Metodos para mover */
	public void moverTerrestre(Posicion posicionInicial, Posicion posicionFinal) {
		// aca deberia mover una unidad a la fila y columna que le pasan
		// si no se puede (ocupado) deberia lanzar NoEsPosibleMoverException.
		Celda celdaFinal = mapa.get(posicionFinal);
		if (celdaFinal.ocupadoTerrestre()) {
			System.out.println("ESTA OCUPADO Terrestre");
			throw new NoEsPosibleMoverException();
		}
		Celda celdaInicial = mapa.get(posicionInicial);
		Ser unidadAMover = celdaInicial.serEnLaCeldaTerrestre();
		ArrayList<Posicion> camino = this.encontrarMinimoCamino(
				posicionInicial, posicionFinal, unidadAMover.movimiento());
		if (camino.isEmpty()) {
			System.out.println("No se pudo mover ");
			throw new NoEsPosibleMoverException();

		}
		ponerTerrestre(posicionFinal, unidadAMover);
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
		Ser unidadAMover = celdaInicial.serEnLaCeldaAerea();
		ArrayList<Posicion> camino = this.encontrarMinimoCamino(
				posicionInicial, posicionFinal, unidadAMover.movimiento());
		if (camino.isEmpty()) {
			System.out.println("No se pudo mover ");
			throw new NoEsPosibleMoverException();
		}
		ponerAereo(posicionFinal, unidadAMover);
		celdaInicial.desocuparAerea();
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

	private Boolean encontrarMinimoCaminoRecursivo(Posicion posicionInicial,
			Posicion posicionFinal, Movimiento movimiento,
			ArrayList<Posicion> camino, ArrayList<Posicion> visitados) {
		if (posicionFinal.equals(posicionInicial)) {
			return true;
		}
		int distanciaMinima = -1;
		Posicion posicionDistanciaMinima = null;
		ArrayList<Posicion> adyacentes = this.adyacentes(posicionInicial);
		Iterator<Posicion> iter = adyacentes.iterator();
		while (iter.hasNext()) {
			Posicion adyacente = iter.next();
			if ((!this.sePuedeMover(adyacente, movimiento))
					|| (visitados.contains(adyacente))) {// Si no se puede mover
															// o fue visitado
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
		if (this.encontrarMinimoCaminoRecursivo(posicionDistanciaMinima,
				posicionFinal, movimiento, camino, visitados)) {
			return true;
		}
		return this.encontrarMinimoCaminoRecursivo(posicionInicial,
				posicionFinal, movimiento, camino, visitados);
	}

	private boolean sePuedeMover(Posicion pos, Movimiento movimiento) {
		if (movimiento == Movimiento.Terrestre) {
			return this.estaVaciaTerrestre(pos);
		}
		return (this.estaVaciaAereo(pos) || this.estaVaciaTerrestre(pos));
	}

	/* Metodos de Borrado */
	private Posicion borrarSer(Ser ser) {
		Color color = ser.color();
		ArrayList<Ser> seresDeColor = seres.get(color);
		for (int i = 0; i < seresDeColor.size(); i++) {
			Ser OtroSer = seresDeColor.get(i);
			if (ser == OtroSer)
				seresDeColor.remove(ser);
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
	}

	private Posicion buscarPosicionDeSer(Ser ser) {
		for (int i = 0; i < this.ancho; i++) {
			for (int j = 0; j < this.alto; j++) {
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

	/* Singleton */
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
		return instancia;
	}

}
