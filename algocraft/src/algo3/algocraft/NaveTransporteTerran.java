package algo3.algocraft;

public class NaveTransporteTerran extends UnidadDeTransporte{
	public NaveTransporteTerran(Color colorJugador){
		ocupado = 0;
		capacidad = 8;
		vision = 8;
		costoMineral=100;
		costoGas=100;
		tiempoDeConstruccion=7;
		suministro=2;
		vida=150;
		escudo=0;
		this.color = color;
	}
}
