package algo3.algocraft.unidades;

import algo3.algocraft.Posicion;
import algo3.algocraft.Ser;

public abstract class Unidad extends Ser {
	protected int vision;
	protected boolean contaminadoRadiacion = false;
	private int meQuedaTormenta;
	

	public void cambiarPosicion(Posicion pos){
		this.posicion = pos;
	}
	public int vision() {
		return vision;
	}

	
	/* danios magicos*/
	public void recibirEmp() {
		this.escudo = 0;
		
	}

	public void recibirTormenta() {
		meQuedaTormenta = 2;
		
	}
	
	public void recibirRadiacion(){
		contaminadoRadiacion = true;
	}
	
	public boolean estoyContaminadoPorTormenta(){
		meQuedaTormenta-=1;
		if(meQuedaTormenta>0) return true;
		return false;
	}
	
	public boolean estoyContaminadoPorRadiacion(){
		return contaminadoRadiacion; // En cada turno si esta contaminado le bajas vida
	}
	
	@Override
	public void pasarTurno(){
		this.revisarMagias();
		if(this.tiempoDeConstruccion==0){
			return;
		}
		this.tiempoDeConstruccion=this.tiempoDeConstruccion-1;
		return;
	}


	private void revisarMagias() {
		if (this.estoyContaminadoPorRadiacion())
			this.recibirDanio(20);
		if ( estoyContaminadoPorTormenta())
			this.recibirDanio(100); 
	}
}
