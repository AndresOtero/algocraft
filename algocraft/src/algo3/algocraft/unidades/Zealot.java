package algo3.algocraft.unidades;

import algo3.algocraft.*;

public class Zealot extends UnidadDeAtaque implements Terrestre, Transportable {
	private int transporte = 2;

	public Zealot(Color colorJugador) {
		danioTierra = 8;
		danioAire = 0;
		rangoAtaqueTierra = 1;
		rangoAtaqueAire = 1;

		vida = 100;
		escudo = 60;
		suministro = 2;
		tiempoDeConstruccion = 4;
		vision = 7;

		costoMineral = 50;
		costoGas = 0;
		color = colorJugador;
		movimiento=Movimiento.Terrestre;
	}

	@Override
	public int transporte() {
		return transporte;
	}

}
