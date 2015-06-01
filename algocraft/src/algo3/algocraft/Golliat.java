package algo3.algocraft;

public class Golliat extends Unidad implements Terrestre, Transportable {
	private int transporte=2;
	public Golliat(){
		danioTierra=12;
		danioAire=10;
		rangoAtaqueTierra=6;
		rangoAtaqueAire=5;
		
		/*heredando de unidad no deberia tirar error cuando se declaran estos 4.. no?
		 * osea... porque los anteriores no hay error y aca si?		 */
		vida = 125;
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
