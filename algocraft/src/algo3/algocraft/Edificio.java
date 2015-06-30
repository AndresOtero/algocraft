package algo3.algocraft;

public abstract class Edificio extends Ser {

	public abstract void agregarseAMapa(Mapa mapa);
	
	@Override
	public boolean esInfectablePorMagia(){
		return false;
	}

	
	
}
