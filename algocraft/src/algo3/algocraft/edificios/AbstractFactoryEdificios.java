package algo3.algocraft.edificios;

import java.util.ArrayList;

import algo3.algocraft.*;
import algo3.algocraft.unidades.Unidad;


public interface AbstractFactoryEdificios {
	public void fabricarCreadorAereos();
	public void fabricarCreadorTerrestres();
	public void fabricarCreadorSoldados();
	public void fabricarSumaPoblacion();
	public void fabricarRecolectableGas(VolcanGasVespeno volcan);
	public void fabricarRecolectableMinerales(Mineral mineral);
	public ArrayList<Edificio> pasarTurno();
}
