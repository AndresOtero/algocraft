package algo3.algocraft.unidades;

import algo3.algocraft.Ser;

public abstract class Unidad extends Ser {
	protected int vision;
	protected int suministro;
	protected boolean contaminadoRadiacion = false;
	
	public int vision() {
		return vision;
	}

	public int suministro() {
		return suministro;
	}

	/* danios magicos*/
	public void recibirEmp() {
		this.escudo = 0;
		
	}

	public void recibirTormenta() {
		this.recibirDanio(100); // POR 2 TURNOS !!
	}
	
	public void recibirRadiacion(){
		contaminadoRadiacion = true;
	}
	
	public boolean estoyContaminado(){
		return contaminadoRadiacion; // En cada turno si esta contaminado le bajas vida
	}
}
