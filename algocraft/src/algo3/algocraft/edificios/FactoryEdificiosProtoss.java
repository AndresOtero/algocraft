package algo3.algocraft.edificios;

import algo3.algocraft.*;

public class FactoryEdificiosProtoss implements AbstractFactoryEdificios {
	private Color color;
	public FactoryEdificiosProtoss(Color colorJugador){
		color=colorJugador;
	}
	
	@Override
	public CreadorAereos fabricarCreadorAereos() {
		return new PuertoEstelarProtoss(color);
	}

	@Override
	public CreadorSoldados fabricarCreadorSoldados() {
		return new Acceso(color);
	}

	@Override
	public SumaPoblacion fabricarSumaPoblacion() {
		return new Pilon(color);
	}

	@Override
	public RecolectableGas fabricarRecolectableGas(VolcanGasVespeno volcan) {
		return new Asimilador(volcan, color);
	}

	@Override
	public RecolectableMinerales fabricarRecolectableMinerales(Mineral mineral) {
		return new NexoMineral(mineral,color);
	}

	@Override
	public CreadorTerrestres fabricarCreadorTerrestres() {
		return new ArchivosTemplarios(color);
	}

}
