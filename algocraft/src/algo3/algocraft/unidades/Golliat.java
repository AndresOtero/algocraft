package algo3.algocraft.unidades;

import algo3.algocraft.*;

public class Golliat extends UnidadDeAtaque implements Terrestre, Transportable {
	private int transporte = 2;

	public Golliat(Color colorJugador,Posicion pos) {
		this.posicion=pos;
		danioTierra = 12;
		danioAire = 10;
		rangoAtaqueTierra = 6;
		rangoAtaqueAire = 5;

		vida = 125;
		suministro = 2;
		tiempoDeConstruccion = 6;
		vision = 8;
		color = colorJugador;
		movimiento=Movimiento.Terrestre;
	}

	@Override
	public int transporte() {
		return transporte;
	}

}
