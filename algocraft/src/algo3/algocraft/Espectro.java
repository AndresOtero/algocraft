package algo3.algocraft;

public class Espectro extends Unidad implements Terrestre, Aerea {

	public Espectro(Color colorJugador) {
		danioTierra = 8;
		danioAire = 20;
		rangoAtaqueTierra = 5;
		rangoAtaqueAire = 5;

		vida = 120;
		suministro = 2;
		tiempoDeConstruccion = 8;
		vision = 7;
		
		color=colorJugador;
		costoMineral=150;
		costoGas=100;
		
	}

	

}
