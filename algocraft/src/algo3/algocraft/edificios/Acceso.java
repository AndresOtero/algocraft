package algo3.algocraft.edificios;

import algo3.algocraft.*;

public class Acceso extends Edificio implements CreadorSoldados {
	
	public Acceso(){
		this.costoGas = 0;
		this.costoMineral = 150;
		this.tiempoDeConstruccion = 8;
		this.vida = 500;
		this.escudo = 500;
	}
	
	public Dragon crearDragon(){
		return new Dragon(color);
	}
	
	public Zealot crearZealot(){
		return new Zealot(color);
	}

}


