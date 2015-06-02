package algo3.algocraft.edificios;

import algo3.algocraft.*;

public class CentroDeMineral extends Edificio {

	private Mineral mineral;

	public CentroDeMineral(Mineral mineral){
		this.vida = 500;
		this.tiempo = 4;
		this.costoGas = 0;
		this.costoMineral = 50;
		this.mineral = mineral;
	
	}

}
