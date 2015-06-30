package algo3.algocraft.unidades;

import algo3.algocraft.Posicion;
import algo3.algocraft.Ser;

public abstract class Unidad extends Ser {

	private boolean contaminadoRadiacion= false;
	private int meQuedaTormenta;
	

	public void cambiarPosicion(Posicion pos){
		this.posicion = pos;
	}
	
	public boolean movimientoPosible(Posicion pInicial, Posicion pFinal){
		if (this.vision >= pInicial.distancia(pFinal)){
			return true;
		}
		return false;
	}
	
	/* danios magicos*/
	public void recibirEmp() {
		this.escudo = 0;
		
	}

	public void recibirTormenta() {
		meQuedaTormenta = 2;
		
	}
	
	public void recibirRadiacion(){
		this.contaminadoRadiacion = true;

	}
	
	public boolean estoyContaminadoPorTormenta(){
		meQuedaTormenta-=1;
		if(meQuedaTormenta>0) return true;
		return false;
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
		if (this.contaminadoRadiacion){
			this.recibirDanio(20);
		}
		if ( estoyContaminadoPorTormenta())
			this.recibirDanio(100); 
	}
	
	@Override
	public boolean esInfectablePorMagia(){
		return true;
	}

	
}
