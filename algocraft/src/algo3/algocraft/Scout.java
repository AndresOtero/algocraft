package algo3.algocraft;

public class Scout extends Unidad implements Terrestre, Aerea {
	public Scout(){
		da�oTierra=8;
		da�oAire=14;
		rangoAtaqueTierra=4;
		rangoAtaqueAire=4;
		
		vida=150;
		escudo=100;
		suministro=3;
		tiempoDeConstruccion=9;
		vision=7;
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
