package algo3.algocraft.unidades;

import algo3.algocraft.*;

public class Marine extends UnidadDeAtaque implements Terrestre, Transportable {
	private int transporte = 1;

	public Marine(Color colorJugador,Posicion pos) {
		this.posicion=pos;
		danioTierra = 6;
		danioAire = 6;
		rangoAtaqueTierra = 4;
		rangoAtaqueAire = 4;

		vida = 40;
		suministro = 1;
		tiempoDeConstruccion = 3;
		vision = 7;
		color = colorJugador;
		movimiento=Movimiento.Terrestre;
		this.id = 12;
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
