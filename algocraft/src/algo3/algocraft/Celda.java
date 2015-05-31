package algo3.algocraft;
public class Celda {
	private boolean visible;
	private boolean areaTerrestre;
	/*Recurso recurso;
	Edificio edificio;*/
	
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