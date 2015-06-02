package algo3.algocraft.edificios;

import algo3.algocraft.*;

public class ArchivosTemplarios extends Edificio implements CreadorTerrestres {

	public ArchivosTemplarios() {
		this.vida = 500;
		this.escudo = 500;
		this.tiempoDeConstruccion = 9;
		this.costoGas = 200;
		this.costoMineral = 150;

	}
	
	public AltoTemplario crearAltoTemplario(){
		return new AltoTemplario(color);
	}
}
