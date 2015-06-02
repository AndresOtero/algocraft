package algo3.algocraft;

public class NaveTransporte extends UnidadDeTransporte {
	public NaveTransporte(Color colorJugador){
		ocupado = 0;
		capacidad = 8;
		vision = 8;
		costoMineral=200;
		costoGas=0;
		tiempoDeConstruccion=8;
		suministro=2;
		vida=60;
		escudo=80;
	}
}
