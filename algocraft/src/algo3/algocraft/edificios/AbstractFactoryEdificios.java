package algo3.algocraft.edificios;

import java.util.ArrayList;
import java.util.HashMap;

import algo3.algocraft.*;
import algo3.algocraft.unidades.Unidad;


public interface AbstractFactoryEdificios {
	public Boolean fabricarCreadorAereos(Posicion pos);
	public Boolean fabricarCreadorTerrestres(Posicion pos);
	public Boolean fabricarCreadorSoldados(Posicion pos);
	public Boolean fabricarSumaPoblacion(Posicion pos);
	public Boolean fabricarRecolectableGas(VolcanGasVespeno volcan,Posicion pos);
	public Boolean fabricarRecolectableMinerales(Mineral mineral,Posicion pos);
	public HashMap<Edificio, Posicion> pasarTurno();
}
