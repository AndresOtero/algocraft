package algo3.algocraft.unidades;

import algo3.algocraft.Color;
import algo3.algocraft.Movimiento;

public class NaveCiencia extends UnidadMagica implements Aerea {
	private Unidad unidadObjetivo = null;

	public NaveCiencia(Color colorJugador) {
		vision = 10;
		costoMineral = 100;
		costoGas = 225;
		suministro = 2;
		vida = 200;
		this.color = colorJugador;
		this.tiempoDeConstruccion = 10;
		movimiento=Movimiento.Aereo;
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
