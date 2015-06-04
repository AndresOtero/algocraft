package algo3.algocraft;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import algo3.algocraft.edificios.EdificioDeRecurso;
import algo3.algocraft.exceptions.*;


public class Mapa {
	private static Mapa instancia = null;
	private Map<Posicion, Celda> mapa =null; 
	private int ancho;
	private int largo;
	private Map<Color, ArrayList<Ser>> seres;
	private ArrayList<Jugador> jugadores;
	private Map<Color, ArrayList<EdificioDeRecurso>> edificiosDeGas;
	private Map<Color, ArrayList<EdificioDeRecurso>> edificiosDeMineral;
	

	
	private Mapa(int ancho, int largo,ArrayList<Jugador> jugadores){
		this.mapa = new HashMap<Posicion, Celda>();
		this.seres = new HashMap<Color, ArrayList<Ser>>();
		this.edificiosDeGas = new HashMap<Color, ArrayList<EdificioDeRecurso>>();
		this.edificiosDeMineral = new HashMap<Color, ArrayList<EdificioDeRecurso>>();
		this.ancho = ancho;
		this.largo = largo;
		this.jugadores = jugadores;
		for (int i = 0; i <= this.ancho; i++) {
			for (int j=0; j<=this.largo; j++) {
				Posicion p = new Posicion(i,j);
				Celda c = new Celda();
				mapa.put(p,c);
			}
		}
		inicializarMapa();
	}
	
	
	private void inicializarMapa(){
		Posicion pos = new Posicion(1,1);
		Celda celda = mapa.get(pos);
		VolcanGasVespeno volcan = new VolcanGasVespeno();
		celda.agregarFuenteRecurso(volcan);
		for(int i = 1;i<6;i++){
			Posicion pos2 = new Posicion(i+2,1);
			Celda celda2 = mapa.get(pos2);
			Mineral mineral = new Mineral();
			celda2.agregarFuenteRecurso(mineral);
			}
		
	}
	
	public void ponerTerrestre(Posicion pos,Ser ser){
		Celda celda = mapa.get(pos);
		if (celda.ocupadoTerrestre()) System.out.println("La celda esta ocupada");
		else celda.agregarSerTerrestre(ser);	
		Color color = ser.color();
		agregarSerDeColor(ser,color);
	}

	public void ponerEdificioDeRecurso(Posicion pos,EdificioDeRecurso edificio){
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

	private synchronized static void createInstance(ArrayList<Jugador> jugadores) {
		if (instancia == null) {
			instancia = new Mapa(50,50,jugadores);
		}
	}
	
	public static Mapa getInstance(ArrayList<Jugador> jugadores) {
		if (instancia == null)
			createInstance(jugadores);
		return instancia;
	}
	
	public  Celda ContenidoFilaColumna(int fila, int columna) {
		return mapa.get((new Posicion(fila,columna)));
	}
	
	public ArrayList<Ser> seresDeJugador(Color color){
		return (seres.get(color));
	}

	public void moverTerrestre(Ser unidadAMover, int xInicial, int yInicial,int xFinal,int yFinal){
		// aca deberia mover una unidad a la fila y columna que le pasan
		//si no se puede (ocupado) deberia lanzar NoEsPosibleMoverException.
		Celda celda = mapa.get((new Posicion(xFinal,yFinal)));
		if (celda.ocupadoTerrestre()) {
			System.out.println("ESTA OCUPADO TERRESTRE"); //throw new NoEsPosibleMoverException();
		}
		else {
			ponerTerrestre(new Posicion(xFinal,yFinal),unidadAMover);	
			celda.desocuparTerrestre();
		}
		
	}
	
	public void moverAerea(Ser unidadAMover, int xInicial, int yInicial,int xFinal,int yFinal) {
		// aca deberia mover una unidad a la fila y columna que le pasan
		//si no se puede (ocupado) deberia lanzar NoEsPosibleMoverException.
		Celda celda = mapa.get((new Posicion(xFinal,yFinal)));
		if (celda.ocupadoAerea()) {
			System.out.println("ESTA OCUPADO Aerea"); //throw new NoEsPosibleMoverException();
		}
		else {
			ponerAereo(new Posicion(xFinal,yFinal),unidadAMover);	
			celda.desocuparAerea();
		}
	}
	
	public ArrayList<EdificioDeRecurso> edificioDeGas (Color color){
		return (edificiosDeGas.get(color));
	}
	
	public ArrayList<EdificioDeRecurso> edificioDeMineral (Color color){
		return (edificiosDeMineral.get(color));
	}
}



