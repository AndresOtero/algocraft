package algo3.algocraft;

import Celda;
import CeldaVacia;
import Mapa;
import Posicion;

import java.util.HashMap;
import java.util.Map;

//public class Mapa {
//	/* ver implementacion tablero tateti */
	//private Map<Posicion, Celda> mapa;

	//public Mapa() {
		//this.mapa = new HashMap<>();
	//}

//}
public class Mapa {
	//private Mapa mapa;
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
	public Mapa getMapa(){
		if (mapa == null){
			mapa = new Mapa(ancho,largo);
		}
		return (Mapa) mapa;
	}
	

}

