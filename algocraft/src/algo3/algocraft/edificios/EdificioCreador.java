package algo3.algocraft.edificios;

import java.util.ArrayList;

import algo3.algocraft.Edificio;
import algo3.algocraft.unidades.*;

public abstract class EdificioCreador extends Edificio {
	private ArrayList<Unidad> unidadesEnCola= new ArrayList<Unidad>();

	public ArrayList<Unidad> unidadesEnCola(){
		return unidadesEnCola;
	} 
	public void pasarTurno(){
		super.pasarTurno();
		ArrayList<Unidad> unidadesCreadas= new ArrayList<Unidad>();
		for (Unidad unidad: unidadesEnCola){
			unidad.pasarTurno();
			if(unidad.creado()){
				unidadesCreadas.add(unidad);
			}
		}
		return;
	}
	protected void agregarACola(Unidad unidad){
		unidadesEnCola.add(unidad);
	}
} 
