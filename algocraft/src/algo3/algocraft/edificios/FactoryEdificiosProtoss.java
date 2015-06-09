package algo3.algocraft.edificios;

import java.util.ArrayList;

import algo3.algocraft.*;

public class FactoryEdificiosProtoss implements AbstractFactoryEdificios {
	private Color color;
	private ArrayList<Edificio> edificiosEnCola = new ArrayList<Edificio>();

	public FactoryEdificiosProtoss(Color colorJugador) {
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
		if((jugador.Minerales()>150)&&(jugador.GasVespeno()>150)){
			edificiosEnCola.add(new PuertoEstelarProtoss(color));
		}
		return false;
	}

	@Override
	public Boolean fabricarCreadorTerrestres(Jugador jugador) {
		if((jugador.Minerales()>150)&&(jugador.GasVespeno()>0)){
			edificiosEnCola.add(new DepositoDeSuminisitros(color));
		}
		return false;
	}

	@Override
	public Boolean fabricarCreadorSoldados(Jugador jugador) {
		if((jugador.Minerales()>150)&&(jugador.GasVespeno()>200)){
			edificiosEnCola.add(new ArchivosTemplarios(color));
		}
		return false;
	}

	@Override
	public Boolean fabricarSumaPoblacion(Jugador jugador) {
		if((jugador.Minerales()>100)&&(jugador.GasVespeno()>0)){
			edificiosEnCola.add(new Pilon(color));
		}
		return false;
	}

	@Override
	public Boolean fabricarRecolectableGas(VolcanGasVespeno volcan,
			Jugador jugador) {
		if((jugador.Minerales()>100)&&(jugador.GasVespeno()>0)){
			edificiosEnCola.add(new Asimilador(volcan, color));
		}
		return false;
	}

	@Override
	public Boolean fabricarRecolectableMinerales(Mineral mineral,
			Jugador jugador) {
		if((jugador.Minerales()>50)&&(jugador.GasVespeno()>0)){
			edificiosEnCola.add(new NexoMineral(mineral, color));
		}	

		return false;
	}

}
