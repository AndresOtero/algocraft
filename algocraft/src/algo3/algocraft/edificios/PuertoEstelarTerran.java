package algo3.algocraft.edificios;

import java.util.ArrayList;

import algo3.algocraft.*;
import algo3.algocraft.unidades.*;
public class PuertoEstelarTerran extends EdificioCreador implements CreadorAereos {

	private int costoGasEspectro;
	private int costoMineralEspectro;
	private int costoMineralNaveDeTransporte;
	private int costoGasNaveDeTransporte;
	private int costoGasNaveCiencia;
	private int costoMineralNaveCiencia;
	public PuertoEstelarTerran(Color colorJugador,Posicion pos) {
		this.posicion=pos;
		this.vida = 1300;
		this.tiempoDeConstruccion = 10;
		this.costoGasEspectro = 100;
		this.costoMineralEspectro = 150;
		this.costoMineralNaveDeTransporte =100;
		this.costoGasNaveDeTransporte = 100;
		this.costoGasNaveCiencia = 225;
		this.costoMineralNaveCiencia = 100;
		this.color=colorJugador;
		this.unidadesEnCola=new ArrayList<Unidad>();
		this.unidadesCreadas=new ArrayList<Unidad>();
		this.id = 5;
		}	
	
	public boolean crearEspectro(Jugador jug){
		if ( jug.GasVespeno() > this.costoGasEspectro && jug.Minerales()> this.costoMineralEspectro){	
		this.agregarACola( new Espectro ( this.color,this.posicion));
		jug.sacarGasVespeno(this.costoGasEspectro);
		jug.sacarMineral(this.costoMineralEspectro);
		return true;
		}
		return false;
	}
	
	public boolean crearNaveCiencia(Jugador jug){
		if ( jug.GasVespeno() > this.costoGasNaveCiencia && jug.Minerales()> this.costoMineralNaveCiencia){	
		this.agregarACola( new NaveCiencia ( this.color,this.posicion));
		jug.sacarGasVespeno(this.costoGasNaveCiencia);
		jug.sacarMineral(this.costoMineralNaveCiencia);
		return true;
		}
		return false;
	}
	
	public boolean crearNaveTransporteTerran(Jugador jug){
		if ( jug.GasVespeno() > this.costoGasNaveDeTransporte && jug.Minerales()> this.costoMineralNaveDeTransporte){	
		this.agregarACola( new NaveTransporteTerran ( this.color,this.posicion));
		jug.sacarGasVespeno(this.costoGasNaveDeTransporte);
		jug.sacarMineral(this.costoMineralNaveDeTransporte);
		return true;
		}
		return false;
	}
	@Override
	public int devolverID() {
		
		return this.id;
		}

}
