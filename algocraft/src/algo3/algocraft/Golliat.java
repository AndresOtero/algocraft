package algo3.algocraft;

public class Golliat extends Unidad implements Terrestre, Transportable {
	private int transporte=2;
	public Golliat(){
		dañoTierra=12;
		dañoAire=10;
		rangoAtaqueTierra=6;
		rangoAtaqueAire=5;
		
		vida=125;
		suministro=2;
		tiempoDeConstruccion=6;
		vision=8;
	}
	@Override
	public void moverPorTierra() {
		
	}
	@Override
	public int transporte() {
		return transporte;
	}

}
