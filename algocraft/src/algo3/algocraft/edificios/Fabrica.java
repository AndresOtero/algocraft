package algo3.algocraft.edificios;

import algo3.algocraft.*;

public class Fabrica extends Edificio implements CreadorTerrestres {
	
	public Fabrica(){
		this.vida = 1250;
		this.tiempoDeConstruccion = 12;
		this.costoGas = 100;
		this.costoMineral = 200;
	}

	public Golliat crearGolliat(){
		return new Golliat(color);
	}
}
