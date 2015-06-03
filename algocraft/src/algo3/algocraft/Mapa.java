package algo3.algocraft;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import algo3.algocraft.exceptions.*;


public class Mapa {
	private static Mapa instancia = null;
	private Map<Posicion, Celda> mapa =null; 
	private int ancho;
	private int largo;
	private Map<Color, ArrayList<Ser>> seres =null; 
	private ArrayList<Jugador> jugadores;

	
	private Mapa(int ancho, int largo,ArrayList<Jugador> jugadores){
		this.mapa = new HashMap<Posicion, Celda>();
		this.ancho = ancho;
		this.largo = largo;
		this.jugadores = jugadores;
		for (int i = 1; i <= this.ancho; i++) {
			for (int j=1; j<=this.largo; j++) {
				Posicion p = new Posicion(i,j);
				mapa.put(p, new Celda());
			}
		}
		inicializarMapa();
	}
	
	
	private void inicializarMapa(){
		Posicion pos = new Posicion(1,1);
		int clave = pos.hashCode();
		/*Aunque el objeto pos de tipo Posicion sea distinto al ya creado dentro del mapa
		 * el hashCode de ambos va a ser le mismo por tener las mismas coordenadas*/
		Celda celda = mapa.get(clave);
		VolcanGasVespeno volcan = new VolcanGasVespeno(pos);
		celda.agregarFuenteRecurso(volcan);
		for(int i = 1;i<6;i++){
			Posicion pos2 = new Posicion(i+2,1);
			int clave2 = pos2.hashCode();
			Celda celda2 = mapa.get(clave2);
			Mineral mineral = new Mineral(pos2);
			celda2.agregarFuenteRecurso(mineral);
			}
		
	}
	public void ponerTerrestre(Posicion pos,Ser ser){
		int clave = pos.hashCode();
		Celda celda = mapa.get(clave);
		if (celda.ocupadoTerrestre()) System.out.println("ESTA OCUPADA TERRESTRE");//EXCEPCION
		else celda.agregarSerTerrestre(ser);	
		Color color = ser.color();
		agregarSerDeColor(ser,color);
	}
		
	public void ponerAereo(Posicion pos,Ser ser){
		int clave = pos.hashCode();
		Celda celda = mapa.get(clave);
		if (celda.ocupadoAerea()) System.out.println("ESTA OCUPADA AEREA");//EXCEPCION
		else celda.agregarSerAereo(ser);	
		Color color = ser.color();
		agregarSerDeColor(ser,color);
	}
	
	private void agregarSerDeColor(Ser ser, Color color) {
		if (seres.containsKey(color)){
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
		return mapa.get((new Posicion(fila,columna)).hashCode());
	}
	public ArrayList<Ser> seresDeJugador(Color color){
		
	}

	public void mover(Ser unidadAMover, int fila, int columna) throws NoEsPosibleMoverException {
		// aca deberia mover una unidad a la fila y columna que le pasan
		//si no se puede (ocupado) deberia lanzar NoEsPosibleMoverException. Suerte :)
		throw new NoEsPosibleMoverException();
		
	}
}



