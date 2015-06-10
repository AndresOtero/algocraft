package algo3.algocraft.edificios;

import java.util.ArrayList;
import java.util.HashMap;

import algo3.algocraft.*;

public class FactoryEdificiosProtoss implements AbstractFactoryEdificios {
	private Color color;
	private ArrayList<Edificio> edificiosEnCola = new ArrayList<Edificio>();
	private HashMap<Edificio, Posicion> posiciones = new HashMap<Edificio, Posicion>();
	public FactoryEdificiosProtoss(Color colorJugador) {
		color = colorJugador;
	}
	@Override
	public HashMap<Edificio, Posicion> pasarTurno() {
		HashMap<Edificio, Posicion> edificiosCreados = new HashMap<Edificio, Posicion>();
		for (Edificio edificio : edificiosEnCola) {
			edificio.pasarTurno();
			if (edificio.creado()) {
				edificiosCreados.put(edificio,posiciones.get(edificio));
			}
		}
		return edificiosCreados;
	}

	@Override
	public Boolean fabricarCreadorAereos(Jugador jugador,Posicion pos) {
		if((jugador.Minerales()>150)&&(jugador.GasVespeno()>150)){
			Edificio ed= new PuertoEstelarProtoss(color);
			edificiosEnCola.add(ed);
			posiciones.put(ed, pos);
			jugador.sacarGasVespeno(150);
			jugador.sacarMineral(150);
			return true;
		}
		return false;
	}

	@Override
	public Boolean fabricarCreadorSoldados(Jugador jugador,Posicion pos) {
		if((jugador.Minerales()>150)&&(jugador.GasVespeno()>0)){
			Edificio ed= new Acceso(color);
			edificiosEnCola.add(ed);
			posiciones.put(ed, pos);
			jugador.sacarGasVespeno(150);
			jugador.sacarMineral(0);
			return true;
		}
		return false;
	}

	@Override
	public Boolean fabricarCreadorTerrestres(Jugador jugador,Posicion pos) {
		if((jugador.Minerales()>150)&&(jugador.GasVespeno()>200)){
			Edificio ed= new ArchivosTemplarios(color);
			edificiosEnCola.add(ed);
			posiciones.put(ed,pos);
			jugador.sacarGasVespeno(150);
			jugador.sacarMineral(200);
			return true;
		}
		return false;
	}

	@Override
	public Boolean fabricarSumaPoblacion(Jugador jugador,Posicion pos) {
		if((jugador.Minerales()>100)&&(jugador.GasVespeno()>0)){
			Edificio ed= new Pilon(color);
			edificiosEnCola.add(ed);
			posiciones.put(ed,pos);
			jugador.sacarGasVespeno(0);
			jugador.sacarMineral(100);
			return true;
		}
		
		return false;
	}

	@Override
	public Boolean fabricarRecolectableGas(VolcanGasVespeno volcan,Jugador jugador,Posicion pos) {
		if((jugador.Minerales()>100)&&(jugador.GasVespeno()>0)){
			Edificio ed= new Asimilador(volcan, color);
			edificiosEnCola.add(ed);
			posiciones.put(ed,pos);
			jugador.sacarGasVespeno(0);
			jugador.sacarMineral(100);
			return true;
		}
		
		return false;
	}

	@Override
	public Boolean fabricarRecolectableMinerales(Mineral mineral,Jugador jugador,Posicion pos) {
		if((jugador.Minerales()>50)&&(jugador.GasVespeno()>0)){
			Edificio ed= new NexoMineral(mineral, color);
			edificiosEnCola.add(ed);
			posiciones.put(ed,pos);
			jugador.sacarGasVespeno(0);
			jugador.sacarMineral(50);
			return true;
		}	
		return false;
	}

}
