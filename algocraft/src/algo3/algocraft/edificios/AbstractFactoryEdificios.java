package algo3.algocraft.edificios;

import java.util.ArrayList;
import java.util.HashMap;

import algo3.algocraft.*;
import algo3.algocraft.unidades.Unidad;


public interface AbstractFactoryEdificios {
	public Boolean fabricarCreadorAereos(Jugador jugador,Posicion pos);
	public Boolean fabricarCreadorTerrestres(Jugador jugador,Posicion pos);
	public Boolean fabricarCreadorSoldados(Jugador jugador,Posicion pos);
	public Boolean fabricarSumaPoblacion(Jugador jugador,Posicion pos);
	public Boolean fabricarRecolectableGas(VolcanGasVespeno volcan,Jugador jugador,Posicion pos);
	public Boolean fabricarRecolectableMinerales(Mineral mineral,Jugador jugador,Posicion pos);
	public HashMap<Edificio, Posicion> pasarTurno();
}
