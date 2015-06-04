package algo3.algocraft.edificios;

import java.util.ArrayList;

import algo3.algocraft.*;

public class FactoryEdificiosProtoss implements AbstractFactoryEdificios {
	private Color color;
	private ArrayList<Edificio> edificiosEnCola= new ArrayList<Edificio>();
	public FactoryEdificiosProtoss(Color colorJugador){
		color=colorJugador;
	}
	
	@Override
	public void fabricarCreadorAereos() {
		edificiosEnCola.add( new PuertoEstelarProtoss(color));
	}

	@Override
	public void fabricarCreadorSoldados() {
		edificiosEnCola.add(new Acceso(color));
	}

	@Override
	public void fabricarSumaPoblacion() {
		edificiosEnCola.add( new Pilon(color));
	}

	@Override
	public void fabricarRecolectableGas(VolcanGasVespeno volcan) {
		edificiosEnCola.add( new Asimilador(volcan, color));
	}

	@Override
	public void fabricarRecolectableMinerales(Mineral mineral) {
		edificiosEnCola.add(new NexoMineral(mineral,color));
	}

	@Override
	public void fabricarCreadorTerrestres() {
		edificiosEnCola.add(new ArchivosTemplarios(color));
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
