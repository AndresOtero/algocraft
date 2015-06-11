package algo3.algocraft.edificios;

import java.util.ArrayList;
import java.util.HashMap;

import algo3.algocraft.*;

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
	public Boolean fabricarCreadorAereos(Posicion pos) {
		if((jugador.Minerales()>150)&&(jugador.GasVespeno()>150)){
			Edificio ed= new PuertoEstelarProtoss(jugador.color());
			edificiosEnCola.add(ed);
			posiciones.put(ed, pos);
			jugador.sacarGasVespeno(150);
			jugador.sacarMineral(150);
			return true;
		}
		return false;
	}

	@Override
	public Boolean fabricarCreadorSoldados(Posicion pos) {
		if((jugador.Minerales()>150)&&(jugador.GasVespeno()>0)){
			Edificio ed= new Acceso(jugador.color());
			edificiosEnCola.add(ed);
			posiciones.put(ed, pos);
			jugador.sacarGasVespeno(150);
			jugador.sacarMineral(0);
			return true;
		}
		return false;
	}

	@Override
	public Boolean fabricarCreadorTerrestres(Posicion pos) {
		if((jugador.Minerales()>150)&&(jugador.GasVespeno()>200)){
			Edificio ed= new ArchivosTemplarios(jugador.color());
			edificiosEnCola.add(ed);
			posiciones.put(ed,pos);
			jugador.sacarGasVespeno(150);
			jugador.sacarMineral(200);
			return true;
		}
		return false;
	}

	@Override
	public Boolean fabricarSumaPoblacion(Posicion pos) {
		if((jugador.Minerales()>100)&&(jugador.GasVespeno()>0)){
			Edificio ed= new Pilon(jugador.color());
			edificiosEnCola.add(ed);
			posiciones.put(ed,pos);
			jugador.sacarGasVespeno(0);
			jugador.sacarMineral(100);
			return true;
		}
		
		return false;
	}

	@Override
	public Boolean fabricarRecolectableGas(VolcanGasVespeno volcan,Posicion pos) {
		if((jugador.Minerales()>100)&&(jugador.GasVespeno()>0)){
			Edificio ed= new Asimilador(volcan, jugador.color());
			edificiosEnCola.add(ed);
			posiciones.put(ed,pos);
			jugador.sacarGasVespeno(0);
			jugador.sacarMineral(100);
			return true;
		}
		
		return false;
	}

	@Override
	public Boolean fabricarRecolectableMinerales(Mineral mineral,Posicion pos) {
		if((jugador.Minerales()>50)&&(jugador.GasVespeno()>0)){
			Edificio ed= new NexoMineral(mineral, jugador.color());
			edificiosEnCola.add(ed);
			posiciones.put(ed,pos);
			jugador.sacarGasVespeno(0);
			jugador.sacarMineral(50);
			return true;
		}	
		return false;
	}

}
