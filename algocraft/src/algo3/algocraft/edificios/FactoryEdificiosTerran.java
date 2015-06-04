package algo3.algocraft.edificios;

import java.util.ArrayList;

import algo3.algocraft.Color;
import algo3.algocraft.Edificio;
import algo3.algocraft.Mineral;
import algo3.algocraft.VolcanGasVespeno;

public class FactoryEdificiosTerran implements AbstractFactoryEdificios {
	private Color color;
	private ArrayList<Edificio> edificiosEnCola= new ArrayList<Edificio>();
	public FactoryEdificiosTerran(Color colorJugador){
		color=colorJugador;
	}
	
	@Override
	public void fabricarCreadorAereos() {
		edificiosEnCola.add(new PuertoEstelarTerran(color));
	}
	@Override
	public void fabricarCreadorTerrestres() {
		edificiosEnCola.add( new Fabrica(color));
	}
	@Override
	public void fabricarCreadorSoldados() {
		edificiosEnCola.add( new Barraca(color));
	}
	@Override
	public void fabricarSumaPoblacion() {
		edificiosEnCola.add( new DepositoDeSuminisitros(color));
	}
	@Override
	public void fabricarRecolectableGas(VolcanGasVespeno volcan) {
		edificiosEnCola.add( new Refineria(volcan, color));
	}
	@Override
	public void fabricarRecolectableMinerales(Mineral mineral) {
		edificiosEnCola.add( new CentroDeMineral(mineral,color));
	}
	@Override
	public ArrayList<Edificio> pasarTurno() {
		ArrayList<Edificio> edificiosCreados= new ArrayList<Edificio>();
		for (Edificio edificio: edificiosEnCola){
			edificio.pasarTurno();
			if(edificio.creado()){
				edificiosCreados.add(edificio);
			}
		}
		return edificiosCreados;
	}

}
