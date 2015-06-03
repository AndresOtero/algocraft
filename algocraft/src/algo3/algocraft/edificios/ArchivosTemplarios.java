package algo3.algocraft.edificios;

import algo3.algocraft.*;
import algo3.algocraft.unidades.*;
public class ArchivosTemplarios extends Edificio implements CreadorTerrestres {

	public ArchivosTemplarios(Color colorJugador) {
		this.vida = 500;
		this.escudo = 500;
		this.tiempoDeConstruccion = 9;
		this.costoGas = 200;
		this.costoMineral = 150;
		this.color=colorJugador;


	}
	
	public AltoTemplario crearAltoTemplario(){
		return new AltoTemplario(color);
	}
}
