package algo3.algocraft.edificios;

import algo3.algocraft.*;

public class CentroDeMineral extends EdificioDeRecurso implements RecolectableMinerales {
	public CentroDeMineral(Mineral mineral, Color colorJugador) {
		this.vida = 500;
		this.tiempoDeConstruccion = 4;
		this.fuenteRecurso = mineral;
		this.color=colorJugador;
	}
}
