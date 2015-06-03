package algo3.algocraft.edificios;

import algo3.algocraft.*;
import algo3.algocraft.unidades.*;
public class Asimilador extends Edificio implements RecolectableGas {
	
	private VolcanGasVespeno volcan;

	public Asimilador(VolcanGasVespeno volcan, Color colorJugador){
		this.vida = 450;
		this.escudo = 450;
		this.tiempoDeConstruccion = 6;
		this.costoGas = 0;
		this.costoMineral = 100;
		this.volcan = volcan;
		this.color=colorJugador;

	}

	@Override
	public int recolectarGas() {
		if(this.puedoRecolectar())return 10;
		return 0;
	}

	private boolean puedoRecolectar() {
		return true;
	}

}
