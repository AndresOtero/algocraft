package algo3.algocraft;

public class Dragon extends Unidad implements Terrestre, Transportable {
	private int transporte=4;
	public Dragon(){
		danioTierra=20;
		danioAire=20;
		rangoAtaqueTierra=4;
		rangoAtaqueAire=4;
		
		vida=100;
		escudo=80;
		suministro=2;
		tiempoDeConstruccion=6;
		vision=8;
	}
	@Override
	public int transporte() {
		return transporte;
	}

	@Override
	public void moverPorTierra() {
		// TODO Auto-generated method stub

	}

}
