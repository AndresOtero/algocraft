package algo3.algocraft.edificios;

import algo3.algocraft.*;

public class PuertoEstelarProtoss extends Edificio implements CreadorAereos {

	public PuertoEstelarProtoss(){
		this.vida = 600;
		this.escudo = 600;
		this.tiempoDeConstruccion = 10;
		this.costoGas = 150;
		this.costoMineral = 150;
	}
	
	public Scout crearScout(){
		return new Scout ( color);
	}
	
	public NaveTransporteProtoss crearNaveTransporteProtoss(){
		return new NaveTransporteProtoss(color);
	}
}
