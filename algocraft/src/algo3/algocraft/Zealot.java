package algo3.algocraft;

public class Zealot extends Unidad implements Terrestre,Transportable {
	private int transporte=2;
	public Zealot(){
		dañoTierra=8;
		dañoAire=0;
		rangoAtaqueTierra=1;
		rangoAtaqueAire=1;
		
		vida=100;
		escudo=60;
		suministro=2;
		tiempoDeConstruccion=4;
		vision=7;
	}
	@Override
	public void moverPorTierra() {
		// TODO Auto-generated method stub

	}
	@Override
	public int transporte() {
		return transporte;
	}

}
