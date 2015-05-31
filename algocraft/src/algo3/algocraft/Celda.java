package algo3.algocraft;
	
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
