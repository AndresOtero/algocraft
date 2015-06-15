package algo3.algocraft.edificios;

import java.util.ArrayList;
import java.util.HashMap;

import algo3.algocraft.Color;
import algo3.algocraft.Edificio;
import algo3.algocraft.Jugador;
import algo3.algocraft.Mapa;
import algo3.algocraft.Mineral;
import algo3.algocraft.Posicion;
import algo3.algocraft.VolcanGasVespeno;
import algo3.algocraft.exceptions.NoHayRecursosException;

public class FactoryEdificiosTerran implements AbstractFactoryEdificios {
	private Jugador jugador;
	private ArrayList<Edificio> edificiosEnCola = new ArrayList<Edificio>();
	private ArrayList<Edificio> edificiosCreados = new ArrayList<Edificio> ();
	private Mapa mapa;
	
	public FactoryEdificiosTerran(Jugador jugador,Mapa mapa) {
		this.jugador = jugador;
		this.mapa =mapa;
	}

	
	@Override
	public void pasarTurno() {
		edificiosCreados.clear();
		for (Edificio edificio : edificiosEnCola) {
			edificio.pasarTurno();
			if (edificio.creado()) {
				edificiosCreados.add(edificio);
				if(!mapa.estaVaciaTerrestre(edificio.posicion())){
					mapa.borrarSerTerrestre(mapa.ContenidoPosicion(edificio.posicion()).serEnLaCeldaTerrestre());	
					edificio.agregarseAMapa(this.mapa);
				}
			}
		}
		for(Edificio edificio:edificiosCreados){
			if (edificiosEnCola.contains(edificio))
			edificiosEnCola.remove(edificio);
		}
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
