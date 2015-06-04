package algo3.algocraft.edificios;

import algo3.algocraft.*;

public class CentroDeMineral extends Edificio implements RecolectableMinerales {

	private Mineral mineral;

	public CentroDeMineral(Mineral mineral, Color colorJugador) {
		this.vida = 500;
		this.tiempoDeConstruccion = 4;
		this.costoGas = 0;
		this.costoMineral = 50;
		this.mineral = mineral;
		this.color=colorJugador;

		

	}

	@Override
	public int recolectarMineral() {
		if (this.puedoRecolectar())
			return 10;
		return 0;
	}

	private boolean puedoRecolectar() {

		return true;
	}

}
