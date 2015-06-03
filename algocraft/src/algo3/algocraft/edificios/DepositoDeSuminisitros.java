package algo3.algocraft.edificios;

import algo3.algocraft.Color;
import algo3.algocraft.Edificio;

public class DepositoDeSuminisitros extends Edificio implements SumaPoblacion{
	
	public DepositoDeSuminisitros(Color colorJugador){
		this.vida = 500;
		this.tiempoDeConstruccion = 6;
		this.costoGas = 0;
		this.costoMineral = 100;
		this.color=colorJugador;

	}

}
