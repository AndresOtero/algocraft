package algo3.algocraft.edificios;

import algo3.algocraft.*;

public class NexoMineral extends EdificioProtoss {

	private Mineral mineral;

	public NexoMineral(Mineral mineral) {
		this.vida = 250;
		this.escudoActual = 250;
		this.escudoInicial = 250;
		this.tiempo = 4;
		this.costoGas = 0;
		this.costoMineral = 50;
		this.mineral = mineral;
	
	}

}
