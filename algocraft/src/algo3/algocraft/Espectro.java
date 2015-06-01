package algo3.algocraft;

public class Espectro extends Unidad implements Terrestre, Aerea {

	public Espectro() {
		danioTierra = 8;
		danioAire = 20;
		rangoAtaqueTierra = 5;
		rangoAtaqueAire = 5;

		vida = 120;
		suministro = 2;
		tiempoDeConstruccion = 8;
		vision = 7;
	}

	@Override
	public void moverPorAgua() {
		// TODO Auto-generated method stub

	}

	@Override
	public void moverPorTierra() {
		// TODO Auto-generated method stub

	}

}
