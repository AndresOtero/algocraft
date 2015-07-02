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
	private String edificiosTerran[] = {"Centro de Mineral","Barraca","Deposito Suministro","Refineria","Fabrica","Puerto Estelar"};
	private String edificiosProtoss[] = {"Nexo Mineral","Pilon","Asimilador","Acesso","Puerto Estelar","Archivos Templarios"};
	private String unidadesTerran[] = {"Marine","Golliat","Espectro","Nave Ciencia", "Nave Transporte Terran"};
	private String unidadesProtos[] ={"Zealot","Dragon","Scout","Alto Templario","Nave Transporte Protoss"};
	
	public Color color(){
		return this.color;
	}

	public int poblacion() {
		return poblacion;
	}

	public int limitePoblacion() {
		return limitePoblacion;
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
	
	public String[] edificiosPuedeConstruir(){
		if (this.tipoRaza() == TipoRaza.TERRAN) return this.edificiosTerran;
		return this.edificiosProtoss;
	}
	
	public String[] unidadesPuedeConstruir(){
		if( this.tipoRaza() == TipoRaza.TERRAN) return this.unidadesTerran;
		return this.unidadesProtos;
	}
	
	
}
