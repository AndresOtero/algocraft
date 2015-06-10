package algo3.algocraft.edificios;

import algo3.algocraft.Color;
import algo3.algocraft.Edificio;

public class EdificioEnConstruccion extends Edificio {
	public EdificioEnConstruccion(Color colorJugador){
		this.vida = 50;
		this.color=colorJugador;
	}
}
