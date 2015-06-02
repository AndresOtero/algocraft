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
		for (int i = 1; i <= ancho; i++) {
			for (int j=1; j<=largo; j++) {
				Posicion p = new Posicion(i,j);
				mapa.put(p, new Celda());
			}
		}
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

