package algo3.algocraft.edificios;

import java.util.ArrayList;

import algo3.algocraft.*;
import algo3.algocraft.unidades.AltoTemplario;
import algo3.algocraft.unidades.Golliat;
import algo3.algocraft.unidades.Unidad;

public class Fabrica extends EdificioCreador implements CreadorTerrestres {

	private int costoGasGolliat;
	private int costoMineralGolliat;

	public Fabrica(Color colorJugador,Posicion pos) {
		this.posicion=pos;
		this.vida = 1250;
		this.tiempoDeConstruccion = 12;
		this.color=colorJugador;
		this.unidadesEnCola=new ArrayList<Unidad>();
		this.unidadesCreadas=new ArrayList<Unidad>();
		this.id = 4;
		}
	public void crearGolliat(){
		this.agregarACola( new Golliat(this.color,this.posicion));
		this.costoGasGolliat = 100;
		this.costoMineralGolliat = 50;
		this.unidadesEnCola = new ArrayList<Unidad>();
		this.unidadesCreadas = new ArrayList<Unidad>();
	}

	public boolean crearGolliat(Jugador jug) {
		if (jug.GasVespeno() > this.costoGasGolliat
				&& jug.Minerales() > this.costoMineralGolliat) {
			this.agregarACola(new Golliat(this.color,this.posicion));
			jug.sacarGasVespeno(this.costoGasGolliat);
			jug.sacarMineral(this.costoMineralGolliat);
			return true;
		}
		return false;
	}
	@Override
	public int devolverID() {
		
		return this.id;
		}

}
