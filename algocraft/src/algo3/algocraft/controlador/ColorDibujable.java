package algo3.algocraft.controlador;

public class ColorDibujable {
	private float rojo;
	private float azul;
	private float verde;
	
	public ColorDibujable(float Rojo, float Azul, float Verde){
		rojo = Rojo;
		verde = Verde;
		azul = Azul;
	}
	
	public float Rojo(){
		return rojo;
	}
	public float Azul(){
		return azul;
	}
	public float Verde(){
		return verde;
	}
}
