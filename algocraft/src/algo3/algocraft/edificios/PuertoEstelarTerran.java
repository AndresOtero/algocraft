package algo3.algocraft.edificios;

import algo3.algocraft.*;
import algo3.algocraft.unidades.*;
public class PuertoEstelarTerran extends Edificio implements CreadorAereos {

	public PuertoEstelarTerran(Color colorJugador) {
		this.vida = 1300;
		this.tiempoDeConstruccion = 10;
		this.costoGas = 100;
		this.costoMineral = 150;
		this.color=colorJugador;

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
