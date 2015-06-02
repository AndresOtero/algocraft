package algo3.algocraft.edificios;

import algo3.algocraft.*;


public class Refineria extends Edificio {
	
	private VolcanGasVespeno volcan;

	public Refineria(VolcanGasVespeno volcan){
		this.vida = 750;
		this.tiempo = 6;
		this.costoGas = 0;
		this.costoMineral = 100;
		this.volcan = volcan;
	}

}
