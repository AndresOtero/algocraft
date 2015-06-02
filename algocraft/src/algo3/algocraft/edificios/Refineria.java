package algo3.algocraft.edificios;

import algo3.algocraft.*;


public class Refineria extends Edificio implements RecolectableGas{
	
	private VolcanGasVespeno volcan;

	public Refineria(VolcanGasVespeno volcan){
		this.vida = 750;
		this.tiempoDeConstruccion = 6;
		this.costoGas = 0;
		this.costoMineral = 100;
		this.volcan = volcan;
	}

	@Override
	public int recolectarGas() {
		if(puedoRecolectar())return 10;
		return 0;
	}

	private boolean puedoRecolectar() {
		
		return true;
	}

}
