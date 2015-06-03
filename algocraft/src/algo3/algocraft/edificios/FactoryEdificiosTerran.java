package algo3.algocraft.edificios;

import algo3.algocraft.Color;

public class FactoryEdificiosTerran implements AbstractFactoryEdificios {
	@Override
	public CreadorAereos fabricarCreadorAereos(Color color) {
		return new PuertoEstelarTerran(color);
	}

	@Override
	public CreadorTerrestres fabricarCreadorTerrestres(Color color) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CreadorSoldados fabricarCreadorSoldados(Color color) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SumaPoblacion fabricarSumaPoblacion(Color color) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RecolectableGas fabricarRecolectableGas(Color color) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RecolectableMinerales fabricarRecolectableMinerales(Color color) {
		// TODO Auto-generated method stub
		return null;
	}

}
