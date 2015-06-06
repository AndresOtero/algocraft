package algo3.algocraft;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.HashMap;
import java.util.Map;

import algo3.algocraft.edificios.EdificioDeRecurso;
import algo3.algocraft.exceptions.*;


public class Mapa {
	private static Mapa instancia = null;
	private Map<Posicion, Celda> mapa =null; 
	private int ancho;
	private int alto;
	private Map<Color, ArrayList<Ser>> seres;
	private Map<Color, ArrayList<EdificioDeRecurso>> edificiosDeGas;
	private Map<Color, ArrayList<EdificioDeRecurso>> edificiosDeMineral;
	

	
	private Mapa(int ancho, int largo,ArrayList<Jugador> jugadores){
		this.mapa = new HashMap<Posicion, Celda>();
		this.seres = new HashMap<Color, ArrayList<Ser>>();
		this.edificiosDeGas = new HashMap<Color, ArrayList<EdificioDeRecurso>>();
		this.edificiosDeMineral = new HashMap<Color, ArrayList<EdificioDeRecurso>>();
		this.ancho = ancho;
		this.alto = largo;
		for (int i = 0; i <= this.ancho; i++) {
			for (int j=0; j<=this.alto; j++) {
				Posicion p = new Posicion(i,j);
				Celda c = new Celda();
				mapa.put(p,c);
			}
		}
		inicializarMapa();
	}
	
	
	private void inicializarMapa(){
		/*Posicion pos = new Posicion(1,1);
		Celda celda = mapa.get(pos);
		VolcanGasVespeno volcan = new VolcanGasVespeno();
		celda.agregarFuenteRecurso(volcan);
		for(int i = 1;i<6;i++){
			Posicion pos2 = new Posicion(i+1,1);
			Celda celda2 = mapa.get(pos2);
			Mineral mineral = new Mineral();
			celda2.agregarFuenteRecurso(mineral);
			}
		for(int i = this.alto -1;0<i;i--){
			Posicion pos2 = new Posicion(i-2,this.alto);
			Celda celda2 = mapa.get(pos2);
			Mineral mineral = new Mineral();
			celda2.agregarFuenteRecurso(mineral);
			}
		
	} despues vemos el algoritmo para calcularlo empecemos con uno de 5x5 y eligiendo manualmente las posiciones*/
	VolcanGasVespeno volcan11= new VolcanGasVespeno(); 
	VolcanGasVespeno volcan55= new VolcanGasVespeno();
	Mineral mineral12 = new Mineral();
	Mineral mineral22 = new Mineral();
	Mineral mineral21 = new Mineral();
	Mineral mineral13 = new Mineral();
	Mineral mineral54 = new Mineral();
	Mineral mineral44 = new Mineral();
	Mineral mineral45 = new Mineral();
	Mineral mineral35 = new Mineral();
	
		
	Posicion pos11 = new Posicion (1,1);
	Posicion pos12 = new Posicion (1,2);
	Posicion pos13 = new Posicion (1,3);
	Posicion pos21 = new Posicion (2,1);
	Posicion pos22 = new Posicion (2,2);
	Posicion pos55 = new Posicion (5,5);
	Posicion pos54 = new Posicion (5,4);
	Posicion pos44 = new Posicion (4,4);
	Posicion pos45 = new Posicion (4,5);
	Posicion pos35 = new Posicion (3,5);
	
	Celda celda11 = mapa.get(pos11); celda11.agregarFuenteRecurso(volcan11);
	Celda celda12 = mapa.get(pos12); celda12.agregarFuenteRecurso(mineral12);
	Celda celda13 = mapa.get(pos13); celda13.agregarFuenteRecurso(mineral13);
	Celda celda21 = mapa.get(pos21); celda21.agregarFuenteRecurso(mineral21);
	Celda celda22 = mapa.get(pos22); celda22.agregarFuenteRecurso(mineral22);
	Celda celda55 = mapa.get(pos55); celda55.agregarFuenteRecurso(volcan55);
	Celda celda54 = mapa.get(pos54); celda54.agregarFuenteRecurso(mineral54);
	Celda celda44 = mapa.get(pos44); celda44.agregarFuenteRecurso(mineral44);
	Celda celda45 = mapa.get(pos45); celda45.agregarFuenteRecurso(mineral45);
	Celda celda35 = mapa.get(pos35); celda35.agregarFuenteRecurso(mineral35);
	
	
	}
	
	public void ponerTerrestre(Posicion pos,Ser ser){
		Celda celda = mapa.get(pos);
		if (celda.ocupadoTerrestre()) System.out.println("La celda esta ocupada");
		else celda.agregarSerTerrestre(ser);	
		Color color = ser.color();
		agregarSerDeColor(ser,color);
	}

	private void ponerEdificioDeRecurso(Posicion pos,EdificioDeRecurso edificio){
		Celda celda = mapa.get(pos);
		if (celda.fuenteRecurso() == null) System.out.println("NO HAY RECURSO AHI"); //expection
		else {
			ponerTerrestre(pos,edificio);
		}
	}
	
	public void ponerEdificioGas(Posicion pos,EdificioDeRecurso edificio){
		ponerEdificioDeRecurso(pos,edificio);
		agregarEdificioDeRecursosDeColor(edificiosDeGas,edificio,edificio.color());
	}
	
	public void ponerEdificioMineral(Posicion pos,EdificioDeRecurso edificio){
		ponerEdificioDeRecurso(pos,edificio);
		agregarEdificioDeRecursosDeColor(edificiosDeMineral,edificio,edificio.color());
	}
	
	private void agregarEdificioDeRecursosDeColor(Map<Color, ArrayList<EdificioDeRecurso>> edificios,EdificioDeRecurso edificio, Color color) {
		if (edificios.get(color) != null){
			(edificios.get(color)).add(edificio);	
		}
		else{
			ArrayList<EdificioDeRecurso> edificiosDeRecursoDeColor = new ArrayList<EdificioDeRecurso>();
			edificiosDeRecursoDeColor.add(edificio);
			edificios.put(color,edificiosDeRecursoDeColor);
		}
	}
	
	public void ponerAereo(Posicion pos,Ser ser) {
		Celda celda = mapa.get(pos);
		if (celda.ocupadoAerea()) System.out.println("La celda esta ocupada");
		else celda.agregarSerAereo(ser);	
		Color color = ser.color();
		agregarSerDeColor(ser,color);
	}
	
	private void agregarSerDeColor(Ser ser, Color color) {
		if (seres.get(color) != null){
			(seres.get(color)).add(ser);	
		}
		else{
			ArrayList<Ser> seresDeColor = new ArrayList<Ser>();
			seresDeColor.add(ser);
			seres.put(color,seresDeColor);
		}
	}

	private synchronized static void createInstance(int fil, int col ,ArrayList<Jugador> jugadores) {
		if (instancia == null) {
			instancia = new Mapa(fil,col,jugadores);
		}
	}
	
	public static Mapa getInstance(int fil , int col ,ArrayList<Jugador> jugadores) {
		if (instancia == null)
			createInstance(fil , col ,jugadores);
		return instancia;
	}
	
	public  Celda ContenidoPosicion(Posicion pos) {
		return mapa.get(pos);
	}
	
	public ArrayList<Ser> seresDeJugador(Color color){
		return (seres.get(color));
	}

	public void moverTerrestre(Posicion posicionInicial, Posicion posicionFinal){
		// aca deberia mover una unidad a la fila y columna que le pasan
		//si no se puede (ocupado) deberia lanzar NoEsPosibleMoverException.
		Celda celdaFinal = mapa.get(posicionFinal);
		Celda celdaInicial = mapa.get(posicionInicial);
		Ser unidadAMover = celdaInicial.serEnLaCeldaTerrestre();
		if (celdaFinal.ocupadoTerrestre()) {
			System.out.println("ESTA OCUPADO Terrestre"); //throw new NoEsPosibleMoverException();
		}
		else {
			ponerTerrestre(posicionFinal,unidadAMover);	
			celdaInicial.desocuparTerrestre();
		}
		
	}
	
	public void moverAerea(Posicion posicionInicial,Posicion posicionFinal) {
		// aca deberia mover una unidad a la fila y columna que le pasan
		//si no se puede (ocupado) deberia lanzar NoEsPosibleMoverException.
		Celda celdaFinal = mapa.get(posicionFinal);
		Celda celdaInicial = mapa.get(posicionInicial);
		Ser unidadAMover = celdaInicial.serEnLaCeldaAerea();
		if (celdaFinal.ocupadoAerea()) {
			System.out.println("ESTA OCUPADO Aerea"); //throw new NoEsPosibleMoverException();
		}
		else {
			ponerAereo(posicionFinal,unidadAMover);	
			celdaInicial.desocuparAerea();
		}
	}
	
	public ArrayList<EdificioDeRecurso> edificioDeGas (Color color){
		return (edificiosDeGas.get(color));
	}
	
	public ArrayList<EdificioDeRecurso> edificioDeMineral (Color color){
		return (edificiosDeMineral.get(color));
	}
	
	private Posicion borrarSer(Ser ser){
		Color color = ser.color();
		ArrayList<Ser> seresDeColor = seres.get(color);
		for (int i=0;i<seresDeColor.size();i++){
			Ser OtroSer = seresDeColor.get(i);
			if (ser == OtroSer) seresDeColor.remove(ser);			
		}
		return buscarPosicionDeSer(ser);
		
	}
	public void borrarSerAereo(Ser ser){
		Posicion pos = borrarSer(ser);
		Celda celda = mapa.get(pos);
		celda.desocuparAerea();
		
	}
	public void borrarSerTerrestre(Ser ser){
		Posicion pos = borrarSer(ser);
		Celda celda = mapa.get(pos);
		celda.desocuparTerrestre();
	}

	private Posicion buscarPosicionDeSer(Ser ser) {
		for(int i = 0;i<this.ancho;i++){
			for(int j=0;j<this.alto;j++){
				Posicion p = new Posicion(i,j);
				Celda celda = mapa.get(p);
				if(ser == celda.serEnLaCeldaAerea()) return p;	
				if(ser == celda.serEnLaCeldaTerrestre()) return p;
			}
		}

		//poner excepcion

		return null;
	}
	
	
	
	
	
}



