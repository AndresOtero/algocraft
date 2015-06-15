package algo3.algocraft.edificios;

import java.util.ArrayList;
import java.util.HashMap;

import algo3.algocraft.Color;
import algo3.algocraft.Edificio;
import algo3.algocraft.Jugador;
import algo3.algocraft.Mineral;
import algo3.algocraft.Posicion;
import algo3.algocraft.VolcanGasVespeno;
import algo3.algocraft.exceptions.NoHayRecursosException;

public class FactoryEdificiosTerran implements AbstractFactoryEdificios {
	private Jugador jugador;
	private ArrayList<Edificio> edificiosEnCola = new ArrayList<Edificio>();
	private HashMap<Edificio, Posicion> edificiosCreados = new HashMap<Edificio, Posicion>();
	
	public FactoryEdificiosTerran(Jugador jugador) {
		this.jugador = jugador;
	}

	@Override
	public HashMap<Edificio, Posicion> pasarTurno() {
		edificiosCreados.clear();
		for (Edificio edificio : edificiosEnCola) {
			edificio.pasarTurno();
			if (edificio.creado()) {
				edificiosCreados.put(edificio,edificio.posicion());
			}
		}
		for(Edificio edificio:edificiosCreados.keySet()){
			if (edificiosEnCola.contains(edificio))
			edificiosEnCola.remove(edificio);
		}
		return edificiosCreados;
	}
	@Override
	public void fabricarCreadorAereos(Posicion pos) {
		if((jugador.Minerales()>150)&&(jugador.GasVespeno()>100)){
			Edificio ed= new PuertoEstelarTerran(jugador.color(),pos);
			edificiosEnCola.add(ed);
			jugador.sacarGasVespeno(100);
			jugador.sacarMineral(150);
			return;
		}
		throw new NoHayRecursosException();
	}

	@Override
	public void fabricarCreadorTerrestres(Posicion pos) {
		if((jugador.Minerales()>200)&&(jugador.GasVespeno()>100)){
			Edificio ed= new Fabrica(jugador.color(),pos);
			edificiosEnCola.add(ed);
			jugador.sacarGasVespeno(100);
			jugador.sacarMineral(200);
			return;
		}
		throw new NoHayRecursosException();
	}

	@Override
	public void fabricarCreadorSoldados(Posicion pos) {
		if((jugador.Minerales()>150)&&(jugador.GasVespeno()>0)){
			Edificio ed= new Barraca(jugador.color(),pos);
			edificiosEnCola.add(ed);
			jugador.sacarGasVespeno(0);
			jugador.sacarMineral(150);
			return;
		}
		throw new NoHayRecursosException();
	}

	@Override
	public void fabricarSumaPoblacion(Posicion pos) {
		if((jugador.Minerales()>100)&&(jugador.GasVespeno()>0)){
			Edificio ed= new DepositoDeSuminisitros(jugador.color(),pos);
			edificiosEnCola.add(ed);
			jugador.sacarGasVespeno(0);
			jugador.sacarMineral(100);
			return;
		}
		throw new NoHayRecursosException();
	}

	@Override
	public void fabricarRecolectableGas(VolcanGasVespeno volcan,Posicion pos) {
		if((jugador.Minerales()>100)&&(jugador.GasVespeno()>0)){
			Edificio ed= new Refineria(volcan, jugador.color(),pos);
			edificiosEnCola.add(ed);
			jugador.sacarGasVespeno(0);
			jugador.sacarMineral(100);
			return;
		}
		throw new NoHayRecursosException();
	}

	@Override
	public void fabricarRecolectableMinerales(Mineral mineral,Posicion pos) {
		if((jugador.Minerales()>50)&&(jugador.GasVespeno()>0)){
			Edificio ed= new CentroDeMineral(mineral, jugador.color(),pos);
			edificiosEnCola.add(ed);
			jugador.sacarGasVespeno(0);
			jugador.sacarMineral(50);
			return;
		}
		throw new NoHayRecursosException();
	}

}
