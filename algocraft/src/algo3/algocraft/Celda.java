package algo3.algocraft;


public class Celda{	
	
	private FuenteRecurso recurso;
	private Ser ser;
	private boolean visible;
	private boolean areaTerrestre;
	private boolean ocupadaTerrestre;
	private boolean ocupadaAerea;
	

	public Celda(){
		visible = false;
		areaTerrestre = true;
		ocupadaTerrestre = false;
		ocupadaAerea = false;
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
	public void cambiarAreaTerrestreAccesible(boolean area){
		areaTerrestre = area;
	}
	public Ser serDeLaCelda(){
		return ser;
	}
	public void agregarSerAereo(Ser ser){
		this.ser =ser;
		ocupadaAerea = true;
	}
	public void agregarSerTerrestre(Ser ser){
		this.ser =ser;
		ocupadaTerrestre = true;
	}
	public FuenteRecurso fuenteRecurso(){
		return recurso;
	}
	public void agregarFuenteRecurso(FuenteRecurso fuenteRecurso){
		recurso = fuenteRecurso;
		visible = true;
	}
	public boolean ocupadaTerrestre(){
		return ocupadaTerrestre;
	}
	public boolean ocupadaAerea(){
		return ocupadaAerea;
	}
	public void sacarTerrestre(){
		ocupadaTerrestre = false;
	}
	public void sacarAereo(){
		ocupadaAerea = false;
	}
}
