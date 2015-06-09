package algo3.algocraft.edificios;

import algo3.algocraft.*;

public class NexoMineral extends EdificioDeRecurso implements RecolectableMinerales{
	public NexoMineral(Mineral mineral, Color colorJugador) {
		this.vida = 250;
		this.escudo = 250;
		this.tiempoDeConstruccion = 4;
		this.fuenteRecurso = mineral;
		this.color=colorJugador;	
	}
}
