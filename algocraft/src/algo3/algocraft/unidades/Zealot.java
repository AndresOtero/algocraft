package algo3.algocraft.unidades;

import algo3.algocraft.*;

public class Zealot extends UnidadDeAtaque implements Terrestre, Transportable {
	private int transporte = 2;

	public Zealot(Color colorJugador, Posicion pos) {
		this.posicion=pos;
		danioTierra = 8;
		danioAire = 0;
		rangoAtaqueTierra = 1;
		rangoAtaqueAire = 1;

		vida = 100;
		escudo = 60;
		suministro = 2;
		tiempoDeConstruccion = 4;
		vision = 7;

		color = colorJugador;
		movimiento=Movimiento.Terrestre;
		this.id = 17;
	}

	@Override
	public int transporte() {
		return transporte;
	}
	@Override
	public int devolverID() {
		
		return this.id;
		}
}
