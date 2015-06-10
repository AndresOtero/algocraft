package algo3.algocraft.edificios;

import java.util.ArrayList;
import java.util.HashMap;

import algo3.algocraft.Color;
import algo3.algocraft.Edificio;
import algo3.algocraft.Jugador;
import algo3.algocraft.Mineral;
import algo3.algocraft.Posicion;
import algo3.algocraft.VolcanGasVespeno;

public class FactoryEdificiosTerran implements AbstractFactoryEdificios {
	private Color color;
	private ArrayList<Edificio> edificiosEnCola = new ArrayList<Edificio>();
	private HashMap<Edificio, Posicion> posiciones = new HashMap<Edificio, Posicion>();

	public FactoryEdificiosTerran(Color colorJugador) {
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
		if((jugador.Minerales()>150)&&(jugador.GasVespeno()>100)){
			Edificio ed= new PuertoEstelarTerran(color);
			edificiosEnCola.add(ed);
			posiciones.put(ed, pos);
			jugador.sacarGasVespeno(100);
			jugador.sacarMineral(150);
			return true;
		}
		return false;
	}

	@Override
	public Boolean fabricarCreadorTerrestres(Jugador jugador,Posicion pos) {
		if((jugador.Minerales()>200)&&(jugador.GasVespeno()>100)){
			edificiosEnCola.add(new Fabrica(color));
			jugador.sacarGasVespeno(100);
			jugador.sacarMineral(200);
			return true;
		}
		
		return false;
	}

	@Override
	public Boolean fabricarCreadorSoldados(Jugador jugador,Posicion pos) {
		if((jugador.Minerales()>150)&&(jugador.GasVespeno()>0)){
			Edificio ed= new Barraca(color);
			edificiosEnCola.add(ed);
			posiciones.put(ed, pos);
			jugador.sacarGasVespeno(0);
			jugador.sacarMineral(150);
			return true;
		}
		return false;
	}

	@Override
	public Boolean fabricarSumaPoblacion(Jugador jugador,Posicion pos) {
		if((jugador.Minerales()>100)&&(jugador.GasVespeno()>0)){
			Edificio ed= new DepositoDeSuminisitros(color);
			edificiosEnCola.add(ed);
			posiciones.put(ed, pos);
			jugador.sacarGasVespeno(0);
			jugador.sacarMineral(100);
			return true;
			
		}
		return false;
	}

	@Override
	public Boolean fabricarRecolectableGas(VolcanGasVespeno volcan,
			Jugador jugador,Posicion pos) {
		if((jugador.Minerales()>100)&&(jugador.GasVespeno()>0)){
			Edificio ed= new Refineria(volcan, color);
			edificiosEnCola.add(ed);
			posiciones.put(ed, pos);
			jugador.sacarGasVespeno(0);
			jugador.sacarMineral(100);
			return true;
		}
		return false;
	}

	@Override
	public Boolean fabricarRecolectableMinerales(Mineral mineral,
			Jugador jugador,Posicion pos) {
		if((jugador.Minerales()>50)&&(jugador.GasVespeno()>0)){
			Edificio ed= new CentroDeMineral(mineral, color);
			edificiosEnCola.add(ed);
			posiciones.put(ed, pos);
			jugador.sacarGasVespeno(0);
			jugador.sacarMineral(50);
			return true;
		}
		return false;
	}

}
