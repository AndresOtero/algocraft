package algo3.algocraft.unidades;

import algo3.algocraft.*;

public class Dragon extends UnidadDeAtaque implements Terrestre, Transportable {
	private int transporte = 4;

	public Dragon(Color colorJugador) {
		danioTierra = 20;
		danioAire = 20;
		rangoAtaqueTierra = 4;
		rangoAtaqueAire = 4;
		
		vida = 100;
		escudo = 80;
		suministro = 2;
		tiempoDeConstruccion = 6;
		vision = 8;
		costoMineral = 125;
		costoGas = 50;
		this.color = colorJugador;
	}

	@Override
	public int transporte() {
		return transporte;
	}

}
