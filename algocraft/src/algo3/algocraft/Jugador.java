package algo3.algocraft;
import algo3.algocraft.exceptions.*;

public class Jugador {
	private String nombre;
	private Color color;
	private TipoRaza raza;
	private int GasVespeno = 0;
	private int Minerales = 0;
	
	public Color color(){
		return this.color;
	}
	
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
	
	public void agregarRecurso(int cantidad, FuenteRecurso fuente){
		
	}
	
	public void agregarGasVespeno(int gas){
		this.GasVespeno += gas;
	}
	
	public void agregarMineral(int mineral){
		this.GasVespeno += mineral;
	}
	
	public int Minerales(){
		return this.Minerales;
	}
	
	public int GasVespeno(){
		return this.GasVespeno;
	}
}
