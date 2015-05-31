package algo3.algocraft;

public class Jugador {
	private String nombre;
	private Color color;
	private TipoRaza raza;
	 Jugador(String nombreJugador, Color colorJugador, TipoRaza razaJugador){
		 if(nombre.length()<4){
			 /*Error*/
		 }
		 this.color=colorJugador;
		 this.nombre=nombreJugador;
		 this.raza=razaJugador;
	 }
	public Boolean esNombre(String nombreJugador) {
		return (nombreJugador==nombre);
	}
	public boolean esColor(Color colorJugador) {
		return (colorJugador==color);
	}
}
