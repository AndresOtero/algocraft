package algo3.algocraft;

public class Marine extends Unidad implements Terrestre,Transportable {
	private int transporte=1;
	
	public Marine(){
		dañoTierra=6;
		dañoAire=6;
		rangoAtaqueTierra=4;
		rangoAtaqueAire=4;

		vida=40;
		suministro=1;
		tiempoDeConstruccion=3;
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
