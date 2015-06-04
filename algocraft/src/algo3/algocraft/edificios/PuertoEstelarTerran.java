package algo3.algocraft.edificios;

import java.util.ArrayList;

import algo3.algocraft.*;
import algo3.algocraft.unidades.*;
public class PuertoEstelarTerran extends EdificioCreador implements CreadorAereos {

	public PuertoEstelarTerran(Color colorJugador) {
		this.vida = 1300;
		this.tiempoDeConstruccion = 10;
		this.costoGas = 100;
		this.costoMineral = 150;
		this.color=colorJugador;
		this.unidadesEnCola=new ArrayList<Unidad>();
	}	
	public void crearEspectro() {
		this.agregarACola( new Espectro(color));
	}
	public void crearNaveCiencia() {
		this.agregarACola( new NaveCiencia(color));
	}
	public void CrearNaveTransporteTerran() {
		this.agregarACola( new NaveTransporteTerran(color));
	}

}
