package algo3.algocraft.edificios;

import algo3.algocraft.Color;


public interface AbstractFactoryEdificios {
	public CreadorAereos fabricarCreadorAereos(Color color);
	public CreadorTerrestres fabricarCreadorTerrestres(Color color);
	public CreadorSoldados fabricarCreadorSoldados(Color color);
	public SumaPoblacion fabricarSumaPoblacion(Color color);
	public RecolectableGas fabricarRecolectableGas(Color color);
	public RecolectableMinerales fabricarRecolectableMinerales(Color color);
}
