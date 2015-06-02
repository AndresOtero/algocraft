package algo3.algocraft.edificios;

import algo3.algocraft.*;

public class Acceso extends Edificio implements RecolectableMinerales {
	
	public Acceso(){
		this.costoGas = 0;
		this.costoMineral = 150;
		this.tiempoDeConstruccion = 8;
		this.vida = 500;
		this.escudo = 500;
	}
	
	public int recolectarMineral(){
		if (this.puedoRecolectar()) return 10;
		return 0;
	}

	
	/*LOGICA DE TURNOS -- */
	private boolean puedoRecolectar() {
		return true;
	}


}


