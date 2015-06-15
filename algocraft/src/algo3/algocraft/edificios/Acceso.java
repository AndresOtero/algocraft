package algo3.algocraft.edificios;

import java.util.ArrayList;

import algo3.algocraft.*;
import algo3.algocraft.unidades.*;

public class Acceso extends EdificioCreador implements CreadorSoldados {

	private int costoMineralZealot;
	private int costoGasZealot;
	private int costoMineralDragon;
	private int costoGasDragon;

	public Acceso(Color colorJugador,Posicion pos) {
		this.posicion=pos;
		this.tiempoDeConstruccion = 8;
		this.costoMineralZealot = 100;
		this.costoGasZealot = 0;
		this.costoMineralDragon = 125;
		this.costoGasDragon =50 ;
		this.vida = 500;
		this.escudo = 500;
		this.color = colorJugador;
		this.unidadesEnCola = new ArrayList<Unidad>();
		this.unidadesCreadas = new ArrayList<Unidad>();
	}

	
	public boolean crearDragon(Jugador jug){
		if ( jug.GasVespeno() > this.costoGasDragon && jug.Minerales()> this.costoMineralDragon){	
		this.agregarACola( new Dragon ( this.color));
		jug.sacarGasVespeno(this.costoGasDragon);
		jug.sacarMineral(this.costoMineralDragon);
		return true;
		}
		return false;
	}


	public boolean crearZealot(Jugador jug){
		if ( jug.GasVespeno() > this.costoGasZealot && jug.Minerales()> this.costoMineralZealot){	
		this.agregarACola( new Zealot ( this.color));
		jug.sacarGasVespeno(this.costoGasZealot);
		jug.sacarMineral(this.costoMineralZealot);
		return true;
		}
		return false;
	}

}
