package algo3.algocraft.controlador;

public class Elemento {
	String nombre;
	ColorDibujable color;
	
	public Elemento(String nombre){
		this.nombre = nombre;
	}
	
	public void setColorDibujable(ColorDibujable dibu){
		color = dibu;
	}
	
	public ColorDibujable getColorDibujable( ){
		 return color;
	}
	
	public String nombre(){
		return nombre;
	}
}
