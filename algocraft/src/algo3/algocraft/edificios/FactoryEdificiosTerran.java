package algo3.algocraft.edificios;

import algo3.algocraft.Color;
import algo3.algocraft.Mineral;
import algo3.algocraft.VolcanGasVespeno;

public class FactoryEdificiosTerran implements AbstractFactoryEdificios {
	private Color color;
	public FactoryEdificiosTerran(Color colorJugador){
		color=colorJugador;
	}
	
	@Override
	public CreadorAereos fabricarCreadorAereos() {
		return new PuertoEstelarTerran(color);
	}
	@Override
	public CreadorTerrestres fabricarCreadorTerrestres() {
		return new Fabrica(color);
	}
	@Override
	public CreadorSoldados fabricarCreadorSoldados() {
		return new Barraca(color);
	}
	@Override
	public SumaPoblacion fabricarSumaPoblacion() {
		return new DepositoDeSuminisitros(color);
	}
	@Override
	public RecolectableGas fabricarRecolectableGas(VolcanGasVespeno volcan) {
		return new Refineria(volcan, color);
	}
	@Override
	public RecolectableMinerales fabricarRecolectableMinerales(Mineral mineral) {
		return new CentroDeMineral(mineral,color);
	}

}
