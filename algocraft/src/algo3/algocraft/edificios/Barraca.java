package algo3.algocraft.edificios;

import algo3.algocraft.*;
import algo3.algocraft.unidades.*;

public class Barraca extends Edificio implements CreadorSoldados{
	
	public Barraca(Color colorJugador){
		this.vida = 1000;
		this.tiempoDeConstruccion = 12;
		this.costoGas = 0;
		this.costoMineral = 150;
		this.color=colorJugador;

	}

	
	public Marine crearMarine(){
		return new Marine(color);
	}
}
