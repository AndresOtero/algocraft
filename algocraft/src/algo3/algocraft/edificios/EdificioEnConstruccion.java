package algo3.algocraft.edificios;

import algo3.algocraft.Color;
import algo3.algocraft.Edificio;
import algo3.algocraft.Mapa;

public class EdificioEnConstruccion extends Edificio {
	public EdificioEnConstruccion(Color colorJugador){
		this.vida = 50;
		this.color=colorJugador;
		this.id = 24;
	}

	@Override
	public void agregarseAMapa(Mapa mapa) {
		mapa.ponerTerrestre(this.posicion(), this);
	}
	@Override
	public int devolverID() {
		
		return this.id;
		}
}


