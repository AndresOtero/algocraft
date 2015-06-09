package algo3.algocraft.edificios;

import java.util.ArrayList;

import algo3.algocraft.*;
import algo3.algocraft.unidades.Unidad;


public interface AbstractFactoryEdificios {
	public Boolean fabricarCreadorAereos(Jugador jugador);
	public Boolean fabricarCreadorTerrestres(Jugador jugador);
	public Boolean fabricarCreadorSoldados(Jugador jugador);
	public Boolean fabricarSumaPoblacion(Jugador jugador);
	public Boolean fabricarRecolectableGas(VolcanGasVespeno volcan,Jugador jugador);
	public Boolean fabricarRecolectableMinerales(Mineral mineral,Jugador jugador);
	public ArrayList<Edificio> pasarTurno();
}
