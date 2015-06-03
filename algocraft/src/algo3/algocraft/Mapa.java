package algo3.algocraft;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class Mapa {
	private static Mapa instancia = null;
	private Map<Posicion, Celda> mapa =null; 
	private int ancho;
	private int largo;

	
	private Mapa(int ancho, int largo){
		this.mapa = new HashMap<Posicion, Celda>();
		this.ancho = ancho;
		this.largo = largo;
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
	}
	public void ponerAereo(Posicion pos,Ser ser){
		int clave = pos.hashCode();
		Celda celda = mapa.get(clave);
		if (celda.ocupadoAerea()) System.out.println("ESTA OCUPADA AEREA");//EXCEPCION
		else celda.agregarSerAereo(ser);	
	}
	
	private synchronized static void createInstance() {
		if (instancia == null) {
			instancia = new Mapa(0,0);
		}
	}
	public static Mapa getInstance() {
		if (instancia == null)
			createInstance();
		return instancia;
	}
}

