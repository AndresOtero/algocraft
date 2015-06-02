package algo3.algocraft;

public class Golliat extends Unidad implements Terrestre, Transportable {
	private int transporte=2;
	public Golliat(Color colorJugador){
		danioTierra=12;
		danioAire=10;
		rangoAtaqueTierra=6;
		rangoAtaqueAire=5;
		
		vida = 125;
		suministro=2;
		tiempoDeConstruccion=6;
		vision=8;
		
		color=colorJugador;
		costoMineral=100;
		costoGas=50;
	}

	@Override
	public int transporte() {
		return transporte;
	}

}
