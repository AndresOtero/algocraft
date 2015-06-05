package algo3.algocraft.unidades;

import algo3.algocraft.Color;

public class NaveCiencia extends UnidadMagica {
	private Unidad unidadObjetivo = null;

	public NaveCiencia(Color colorJugador) {
		vision = 10;
		costoMineral = 100;
		costoGas = 225;
		suministro = 2;
		vida = 200;
		this.color = colorJugador;
		this.tiempoDeConstruccion = 10;
	}

	@Override
	public void aumentarEnergia() {
		energia = energia + 10;
	}

	public void emp() {

	}

	public void radiacion() {

	}

}
