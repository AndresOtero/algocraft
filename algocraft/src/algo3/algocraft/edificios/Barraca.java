package algo3.algocraft.edificios;

import algo3.algocraft.*;

public class Barraca extends Edificio implements CreadorSoldados{
	
	public Barraca(){
		this.vida = 1000;
		this.tiempoDeConstruccion = 12;
		this.costoGas = 0;
		this.costoMineral = 150;
	}

	
	public Marine crearMarine(){
		return new Marine(color);
	}
}
