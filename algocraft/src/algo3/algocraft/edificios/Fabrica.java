package algo3.algocraft.edificios;

import java.util.ArrayList;

import algo3.algocraft.*;
import algo3.algocraft.unidades.Golliat;
import algo3.algocraft.unidades.Unidad;

public class Fabrica extends EdificioCreador implements CreadorTerrestres {
	
	public Fabrica(Color colorJugador){
		this.vida = 1250;
		this.tiempoDeConstruccion = 12;
		this.color=colorJugador;
		this.unidadesEnCola=new ArrayList<Unidad>();
		this.unidadesCreadas=new ArrayList<Unidad>();	}
	public void crearGolliat(){
		this.agregarACola( new Golliat(this.color));
	}
}
