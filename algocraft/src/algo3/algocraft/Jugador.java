package algo3.algocraft;
import algo3.algocraft.edificios.AbstractFactoryEdificios;
import algo3.algocraft.edificios.Fabrica;
import algo3.algocraft.edificios.FactoryEdificiosProtoss;
import algo3.algocraft.edificios.FactoryEdificiosTerran;
import algo3.algocraft.exceptions.*;

public class Jugador {
	private String nombre;
	private Color color;
	private TipoRaza raza;
	private int GasVespeno = 0;
	private int Minerales = 0;
	private int poblacion=0;
	private int limitePoblacion=10;
	
	public Color color(){
		return this.color;
	}
	
	public TipoRaza tipoRaza(){
		return this.raza;
	}
	public void agregarEspacionParaPoblacion(){
		limitePoblacion+=10;
	}
	public boolean agregarPoblacion(int suministro){	
		if(poblacion+suministro>limitePoblacion){
			return false;
		}
		poblacion+=suministro;
		return true;
	}
	public void quitarPoblacion(int suministro){	
		poblacion-=suministro;
	}
	
	
	 Jugador(String nombreJugador, Color colorJugador, TipoRaza razaJugador) {
		 if(nombreJugador.length()<4){
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
	
	
	
	public void agregarGasVespeno(int gas){
		this.GasVespeno += gas;
	}
	
	public void sacarGasVespeno (int gas){
		this.GasVespeno -=gas;
	}
	
	
	public void agregarMineral(int mineral){
		this.Minerales += mineral;
	}
	
	public void sacarMineral(int mineral){
		this.Minerales -= mineral;
	}
	
	public int Minerales(){
		return this.Minerales;
	}
	
	
	
	public int GasVespeno(){
		return this.GasVespeno;
	}


}
