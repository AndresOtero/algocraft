package algo3.algocraft.edificios;

import algo3.algocraft.*;
import algo3.algocraft.unidades.*;

public class Acceso extends Edificio implements CreadorSoldados {
	
	public Acceso(Color colorJugador){
		this.costoGas = 0;
		this.costoMineral = 150;
		this.tiempoDeConstruccion = 8;
		this.vida = 500;
		this.escudo = 500;
		this.color=colorJugador;
	}
	
	public Dragon crearDragon(){
		return new Dragon(color);
	}
	
	public Zealot crearZealot(){
		return new Zealot(color);
	}

}


