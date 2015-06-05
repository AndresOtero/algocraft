package algo3.algocraft.unidades;

import algo3.algocraft.*;

public class AltoTemplario extends UnidadMagica implements Transportable {
	private int transporte = 2;

	public AltoTemplario(Color colorJugador) {
		vida = 40;
		escudo = 40;
		suministro = 2;
		tiempoDeConstruccion = 7;
		vision = 7;
		costoMineral = 50;
		costoGas = 150;
		this.color = colorJugador;
	}

	public void tormenta() {

	}

	public void alucinacion() {

	}

	@Override
	public void aumentarEnergia() {
		energia = energia + 15;
	}

	@Override
	public int transporte() {
		return transporte;
	}
}
