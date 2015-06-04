package algo3.algocraft.edificios;

import algo3.algocraft.*;


public class Refineria extends EdificioDeRecurso implements RecolectableGas{
	

	public Refineria(VolcanGasVespeno volcan, Color colorJugador){
		this.vida = 750;
		this.tiempoDeConstruccion = 6;
		this.costoGas = 0;
		this.costoMineral = 100;
		this.fuenteRecurso = volcan;
		this.color=colorJugador;
		
	}


}
