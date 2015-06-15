package algo3.algocraft.edificios;

import java.util.ArrayList;

import algo3.algocraft.*;
import algo3.algocraft.unidades.*;
public class PuertoEstelarProtoss extends EdificioCreador implements CreadorAereos {

	private int costoMineralScout;
	private int costoGasScout;
	private int costoMineralNave;
	private int costoGasNave;

	public PuertoEstelarProtoss(Color colorJugador,Posicion pos) {
		this.posicion=pos;
		this.vida = 600;
		this.escudo = 600;
		this.tiempoDeConstruccion = 10;
		this.costoMineralScout = 300; 
		this.costoGasScout = 150;
		this.costoMineralNave = 200;
		this.costoGasNave = 0;
		this.color=colorJugador;
		this.unidadesEnCola=new ArrayList<Unidad>();
		this.unidadesCreadas=new ArrayList<Unidad>();	}
	
	public boolean crearScout(Jugador jug){
		if ( jug.GasVespeno() > this.costoGasScout && jug.Minerales()> this.costoMineralScout){	
		this.agregarACola( new Scout ( this.color));
		jug.sacarGasVespeno(this.costoGasScout);
		jug.sacarMineral(this.costoMineralScout);
		return true;
		}
		return false;
	}
	
	public boolean crearNaveTransporteProtoss(Jugador jug){
		if ( jug.GasVespeno() > this.costoGasNave && jug.Minerales()> this.costoMineralNave){	
		this.agregarACola( new NaveTransporteProtoss ( this.color));
		jug.sacarGasVespeno(this.costoGasNave);
		jug.sacarMineral(this.costoMineralNave);
		return true;
		}
		return false;
	}
}
