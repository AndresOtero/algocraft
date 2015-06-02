package algo3.algocraft.edificios;

import algo3.algocraft.*;

public class PuertoEstelarTerran extends Edificio implements CreadorAereos {

	public PuertoEstelarTerran() {
		this.vida = 1300;
		this.tiempoDeConstruccion = 10;
		this.costoGas = 100;
		this.costoMineral = 150;
	}

	public Espectro crearEspectro() {
		return new Espectro(color);
	}

	public NaveCiencia crearNaveCiencia() {
		return new NaveCiencia(color);
	}

	public NaveTransporteTerran CrearNaveTransporteTerran() {
		return new NaveTransporteTerran(color);

	}
}
