package algo3.algocraft.edificios;

import java.util.ArrayList;

import algo3.algocraft.*;
import algo3.algocraft.unidades.*;

public class Acceso extends EdificioCreador implements CreadorSoldados {
	
	public Acceso(Color colorJugador){
		this.costoGas = 0;
		this.costoMineral = 150;
		this.tiempoDeConstruccion = 8;
		this.vida = 500;
		this.escudo = 500;
		this.color=colorJugador;
		this.unidadesEnCola=new ArrayList<Unidad>();
	}
	public void crearDragon(){
		Dragon dragon=new Dragon(color);
		this.agregarACola(dragon);
		return;
	}
	public void crearZealot(){
		this.agregarACola( new Zealot(color));
	}

}


