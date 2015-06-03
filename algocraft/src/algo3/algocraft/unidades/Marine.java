package algo3.algocraft.unidades;

import algo3.algocraft.*;

public class Marine extends UnidadDeAtaque implements Terrestre,Transportable {
	private int transporte=1;
	
	public Marine(Color colorJugador){
		danioTierra=6;
		danioAire=6;
		rangoAtaqueTierra=4;
		rangoAtaqueAire=4;

		vida=40;
		suministro=1;
		tiempoDeConstruccion=3;
		vision=7;
		
		color=colorJugador;
		costoMineral=50;
		costoGas=0;
		
	}
	@Override
	public int transporte() {
		return transporte;
	}
	
}
