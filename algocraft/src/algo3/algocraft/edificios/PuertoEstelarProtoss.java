package algo3.algocraft.edificios;

import algo3.algocraft.*;
import algo3.algocraft.unidades.*;
public class PuertoEstelarProtoss extends Edificio implements CreadorAereos {

	public PuertoEstelarProtoss(Color colorJugador){
		this.vida = 600;
		this.escudo = 600;
		this.tiempoDeConstruccion = 10;
		this.costoGas = 150;
		this.costoMineral = 150;
		this.color=colorJugador;

	}
	
	public Scout crearScout(){
		return new Scout ( color);
	}
	
	public NaveTransporteProtoss crearNaveTransporteProtoss(){
		return new NaveTransporteProtoss(color);
	}
}
