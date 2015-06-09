package algo3.algocraft.edificios;

import java.util.ArrayList;

import algo3.algocraft.Color;
import algo3.algocraft.Edificio;
import algo3.algocraft.Jugador;
import algo3.algocraft.Mineral;
import algo3.algocraft.VolcanGasVespeno;

public class FactoryEdificiosTerran implements AbstractFactoryEdificios {
	private Color color;
	private ArrayList<Edificio> edificiosEnCola = new ArrayList<Edificio>();

	public FactoryEdificiosTerran(Color colorJugador) {
		color = colorJugador;
	}

	@Override
	public ArrayList<Edificio> pasarTurno() {
		ArrayList<Edificio> edificiosCreados = new ArrayList<Edificio>();
		for (Edificio edificio : edificiosEnCola) {
			edificio.pasarTurno();
			if (edificio.creado()) {
				edificiosCreados.add(edificio);
			}
		}
		return edificiosCreados;
	}
	@Override
	public Boolean fabricarCreadorAereos(Jugador jugador) {
		if((jugador.Minerales()>150)&&(jugador.GasVespeno()>100)){
			edificiosEnCola.add(new PuertoEstelarTerran(color));
			jugador.sacarGasVespeno(100);
			jugador.sacarMineral(150);
			return true;
		}
		return false;
	}

	@Override
	public Boolean fabricarCreadorTerrestres(Jugador jugador) {
		if((jugador.Minerales()>200)&&(jugador.GasVespeno()>100)){
			edificiosEnCola.add(new Fabrica(color));
			jugador.sacarGasVespeno(100);
			jugador.sacarMineral(200);
			return true;
		}
		
		return false;
	}

	@Override
	public Boolean fabricarCreadorSoldados(Jugador jugador) {
		if((jugador.Minerales()>150)&&(jugador.GasVespeno()>0)){
			edificiosEnCola.add(new Barraca(color));
			jugador.sacarGasVespeno(0);
			jugador.sacarMineral(150);
			return true;
		}
		return false;
	}

	@Override
	public Boolean fabricarSumaPoblacion(Jugador jugador) {
		if((jugador.Minerales()>100)&&(jugador.GasVespeno()>0)){
			edificiosEnCola.add(new DepositoDeSuminisitros(color));
			jugador.sacarGasVespeno(0);
			jugador.sacarMineral(100);
			return true;
			
		}
		return false;
	}

	@Override
	public Boolean fabricarRecolectableGas(VolcanGasVespeno volcan,
			Jugador jugador) {
		if((jugador.Minerales()>100)&&(jugador.GasVespeno()>0)){
			edificiosEnCola.add(new Refineria(volcan, color));
			jugador.sacarGasVespeno(0);
			jugador.sacarMineral(100);
			return true;
		}
		return false;
	}

	@Override
	public Boolean fabricarRecolectableMinerales(Mineral mineral,
			Jugador jugador) {
		if((jugador.Minerales()>50)&&(jugador.GasVespeno()>0)){
			edificiosEnCola.add(new CentroDeMineral(mineral, color));
			jugador.sacarGasVespeno(0);
			jugador.sacarMineral(50);
			return true;
		}
		return false;
	}

}
