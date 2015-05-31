package algo3.algocraft;

import Celda;
import CeldaVacia;
import Mapa;
import Posicion;

import java.util.HashMap;
import java.util.Map;

public class Mapa {
	
	private Mapa instancia = null;
	private Map<Posicion, Celda> mapa; 
	private int ancho;
	private int largo;

	
	private Mapa(int ancho, int largo){
		this.mapa = new HashMap<Posicion, Celda>();
		this.ancho = ancho;
		this.largo = largo;
		for (int i = 1; i <= ancho; i++) {
			for (int j=1; j<=largo; j++) {
				Posicion p = new Posicion(i,j);
				mapa.put(p, new Celda());
			}
		}
	}
	private synchronized void createInstance() {
		if (instancia == null) {
			instancia = new Mapa(ancho,largo);
		}
	}
	
	public Mapa getInstance(){
		if (instancia == null){
			instancia = new Mapa(ancho,largo);
		}
		return (Mapa) instancia;
	}
	

}
