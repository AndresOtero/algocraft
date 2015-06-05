package algo3.algocraft.edificios;

import java.util.ArrayList;

import algo3.algocraft.*;
import algo3.algocraft.unidades.*;
public class PuertoEstelarProtoss extends EdificioCreador implements CreadorAereos {

	public PuertoEstelarProtoss(Color colorJugador){
		this.vida = 600;
		this.escudo = 600;
		this.tiempoDeConstruccion = 10;
		this.costoGas = 150;
		this.costoMineral = 150;
		this.color=colorJugador;
		this.unidadesEnCola=new ArrayList<Unidad>();
		this.unidadesCreadas=new ArrayList<Unidad>();	}
	
	public void crearScout(){
		this.agregarACola( new Scout ( this.color));
	}
	
	public void crearNaveTransporteProtoss(){
		this.agregarACola( new NaveTransporteProtoss(this.color));
	}
}
