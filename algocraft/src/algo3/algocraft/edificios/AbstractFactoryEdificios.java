package algo3.algocraft.edificios;

import java.util.ArrayList;
import java.util.HashMap;

import algo3.algocraft.*;
import algo3.algocraft.unidades.Unidad;


public interface AbstractFactoryEdificios {
	public void fabricarCreadorAereos(Posicion pos);
	public void fabricarCreadorTerrestres(Posicion pos);
	public void fabricarCreadorSoldados(Posicion pos);
	public void fabricarSumaPoblacion(Posicion pos);
	public void fabricarRecolectableGas(VolcanGasVespeno volcan,Posicion pos);
	public void fabricarRecolectableMinerales(Mineral mineral,Posicion pos);
	public void pasarTurno();
}
