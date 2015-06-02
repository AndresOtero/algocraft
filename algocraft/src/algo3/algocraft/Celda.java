package algo3.algocraft;


public class Celda{	
	
	FuenteRecurso recurso;
	Ser ser;
	private boolean visible;
	private boolean areaTerrestre;

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
