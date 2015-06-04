package algo3.algocraft.edificios;

import algo3.algocraft.*;
import algo3.algocraft.unidades.Golliat;

public class Fabrica extends EdificioCreador implements CreadorTerrestres {
	
	public Fabrica(Color colorJugador){
		this.vida = 1250;
		this.tiempoDeConstruccion = 12;
		this.costoGas = 100;
		this.costoMineral = 200;
		this.color=colorJugador;

	}

	public void crearGolliat(){
		this.agregarACola( new Golliat(color));
	}
}
