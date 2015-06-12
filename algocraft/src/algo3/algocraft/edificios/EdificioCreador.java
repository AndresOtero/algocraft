package algo3.algocraft.edificios;

import java.util.ArrayList;

import algo3.algocraft.Edificio;
import algo3.algocraft.unidades.*;

public abstract class EdificioCreador extends Edificio {
	protected ArrayList<Unidad> unidadesCreadas;
	protected ArrayList<Unidad> unidadesEnCola;

	public ArrayList<Unidad> unidadesCreadas() {
		return unidadesCreadas;
	}

	public void pasarTurno() {
		super.pasarTurno();
		unidadesCreadas.clear();
		for (Unidad unidad : unidadesEnCola) {
			unidad.pasarTurno();
			if (unidad.creado()) {
				this.unidadesCreadas.add(unidad);
			}
		}
		for(Unidad unidad: unidadesCreadas){
			if(unidadesEnCola.contains(unidad))unidadesEnCola.remove(unidad);
		}
	}

	protected void agregarACola(Unidad unidad) {
		unidadesEnCola.add(unidad);
	}
}
