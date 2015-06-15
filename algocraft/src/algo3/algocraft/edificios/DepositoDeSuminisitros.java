package algo3.algocraft.edificios;

import algo3.algocraft.Color;
import algo3.algocraft.Edificio;
import algo3.algocraft.Mapa;
import algo3.algocraft.Posicion;

public class DepositoDeSuminisitros extends Edificio implements SumaPoblacion {
	public DepositoDeSuminisitros(Color colorJugador,Posicion pos) {
		this.posicion=pos;
		this.vida = 500;
		this.tiempoDeConstruccion = 6;
		this.color = colorJugador;
	}
	public void agregarseAMapa(Mapa mapa){
		mapa.ponerSumaPoblacion(this.posicion(), this);
	}
}
