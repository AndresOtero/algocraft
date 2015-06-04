package algo3.algocraft.edificios;

import algo3.algocraft.Color;
import algo3.algocraft.Mineral;
import algo3.algocraft.VolcanGasVespeno;


public interface AbstractFactoryEdificios {
	public CreadorAereos fabricarCreadorAereos();
	public CreadorTerrestres fabricarCreadorTerrestres();
	public CreadorSoldados fabricarCreadorSoldados();
	public SumaPoblacion fabricarSumaPoblacion();
	public RecolectableGas fabricarRecolectableGas(VolcanGasVespeno volcan);
	public RecolectableMinerales fabricarRecolectableMinerales(Mineral mineral);
}
