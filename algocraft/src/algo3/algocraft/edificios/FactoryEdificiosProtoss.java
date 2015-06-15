package algo3.algocraft.edificios;

import java.util.ArrayList;
import java.util.HashMap;

import algo3.algocraft.*;
import algo3.algocraft.exceptions.NoHayRecursosException;

public class FactoryEdificiosProtoss implements AbstractFactoryEdificios {
	private Jugador jugador;
	private ArrayList<Edificio> edificiosEnCola = new ArrayList<Edificio>();
	private HashMap<Edificio, Posicion> edificiosCreados = new HashMap<Edificio, Posicion>();
	private HashMap<Edificio, Posicion> posiciones = new HashMap<Edificio, Posicion>();
	public FactoryEdificiosProtoss(Jugador jugador) {
		this.jugador = jugador;
	}
	@Override
	public HashMap<Edificio, Posicion> pasarTurno() {
		edificiosCreados.clear();
		for (Edificio edificio : edificiosEnCola) {
			edificio.pasarTurno();
			if (edificio.creado()) {
				edificiosCreados.put(edificio,posiciones.get(edificio));
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
		if((jugador.Minerales()>150)&&(jugador.GasVespeno()>150)){
			Edificio ed= new PuertoEstelarProtoss(jugador.color(),pos);
			edificiosEnCola.add(ed);
			posiciones.put(ed, pos);
			jugador.sacarGasVespeno(150);
			jugador.sacarMineral(150);
			return;
		}
		throw new NoHayRecursosException();
	}

	@Override
	public void fabricarCreadorSoldados(Posicion pos) {
		if((jugador.Minerales()>150)&&(jugador.GasVespeno()>0)){
			Edificio ed= new Acceso(jugador.color(),pos);
			edificiosEnCola.add(ed);
			posiciones.put(ed, pos);
			jugador.sacarGasVespeno(150);
			jugador.sacarMineral(0);
			return;
		}
		throw new NoHayRecursosException();
	}

	@Override
	public void fabricarCreadorTerrestres(Posicion pos) {
		if((jugador.Minerales()>150)&&(jugador.GasVespeno()>200)){
			Edificio ed= new ArchivosTemplarios(jugador.color(),pos);
			edificiosEnCola.add(ed);
			posiciones.put(ed,pos);
			jugador.sacarGasVespeno(150);
			jugador.sacarMineral(200);
			return;
		}
		throw new NoHayRecursosException();
	}

	@Override
	public void fabricarSumaPoblacion(Posicion pos) {
		if((jugador.Minerales()>100)&&(jugador.GasVespeno()>0)){
			Edificio ed= new Pilon(jugador.color(),pos);
			edificiosEnCola.add(ed);
			posiciones.put(ed,pos);
			jugador.sacarGasVespeno(0);
			jugador.sacarMineral(100);
			return;
		}
		throw new NoHayRecursosException();
	}

	@Override
	public void fabricarRecolectableGas(VolcanGasVespeno volcan,Posicion pos) {
		if((jugador.Minerales()>100)&&(jugador.GasVespeno()>0)){
			Edificio ed= new Asimilador(volcan, jugador.color(),pos);
			edificiosEnCola.add(ed);
			posiciones.put(ed,pos);
			jugador.sacarGasVespeno(0);
			jugador.sacarMineral(100);
			return;
		}
		throw new NoHayRecursosException();
	}

	@Override
	public void fabricarRecolectableMinerales(Mineral mineral,Posicion pos) {
		if((jugador.Minerales()>50)&&(jugador.GasVespeno()>0)){
			Edificio ed= new NexoMineral(mineral, jugador.color(),pos);
			edificiosEnCola.add(ed);
			posiciones.put(ed,pos);
			jugador.sacarGasVespeno(0);
			jugador.sacarMineral(50);
			return;
		}
		throw new NoHayRecursosException();
	}

}
