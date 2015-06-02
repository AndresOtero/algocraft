package algo3.algocraft.edificios;

import algo3.algocraft.*;

public class Asimilador extends EdificioProtoss {
	
	private VolcanGasVespeno volcan;

	public Asimilador(VolcanGasVespeno volcan){
		this.vida = 450;
		this.escudoActual = 450;
		this.escudoInicial = 450;
		this.tiempo = 6;
		this.costoGas = 0;
		this.costoMineral = 100;
		this.volcan = volcan;
	}

}
