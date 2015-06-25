package algo3.algocraft;

public enum Color {
	ROJO(0),AZUL(1),AMARILLO(2),VERDE(3);
	private int numero;
	private Color(int numero){
		numero=numero;
	}
	public int numero(){
		return numero;
	}
}
