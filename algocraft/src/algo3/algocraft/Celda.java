package algo3.algocraft;

import Edificio;
import Recurso;

public class Celda {
	Recurso recurso;
	Edificio edificio;
	private boolean visible,areaTerrestre;
	
	public Celda(){
		visible = false;
		areaTerrestre = true;
	}
	
	public boolean esVisible(){
		return visible;
	}
	public void cambiarVisibilidad(boolean nuevaVisibilidad){
		visible = nuevaVisibilidad;
	}
	public boolean esTerrestre(){
		return areaTerrestre;
	}
	public void cambiarAreaTerrestre(boolean area){
		areaTerrestre = area;
	}
	
}