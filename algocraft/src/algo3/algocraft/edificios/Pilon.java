package algo3.algocraft.edificios;

import algo3.algocraft.*;

public class Pilon extends Edificio implements SumaPoblacion {

	public Pilon(Color colorJugador) {
		this.vida = 300;
		this.escudo = 300;
		this.tiempoDeConstruccion = 5;
		this.color = colorJugador;

	}

}
