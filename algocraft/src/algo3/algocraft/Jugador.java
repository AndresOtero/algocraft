package algo3.algocraft;
import algo3.algocraft.exceptions.*;

public class Jugador {
	private String nombre;
	private Color color;
	private TipoRaza raza;
	private int GasVespeno = 0;
	private int Minerales = 0;
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
	
	public void agregar
}
