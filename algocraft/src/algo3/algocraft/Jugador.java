package algo3.algocraft;

public class Jugador {
	private String nombre;
	private Color color;
	private TipoRaza raza;
	 Jugador(String nombreJugador, Color colorJugador, TipoRaza razaJugador) throws NombreIncorrectoException{
		 if(nombre.length()<4){
			 throw new  NombreIncorrectoException();
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
	
	public String nombre(){
		return this.nombre;
	}
}
