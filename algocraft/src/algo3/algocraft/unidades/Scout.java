package algo3.algocraft.unidades;

import algo3.algocraft.*;

public class Scout extends UnidadDeAtaque implements Terrestre, Aerea {
	public Scout(Color colorJugador){
		danioTierra=8;
		danioAire=14;
		rangoAtaqueTierra=4;
		rangoAtaqueAire=4;
		
		vida=150;
		escudo=100;
		suministro=3;
		tiempoDeConstruccion=9;
		vision=7;
		
		this.color=colorJugador;
		costoMineral=300;
		costoGas=150;
	}


}
