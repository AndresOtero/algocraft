package algo3.algocraft;

public abstract class UnidadDeTransporte extends Unidad {
	protected int capacidad;
	protected int ocupado;
	
	public Boolean subirUnidad(Transportable transportable){
		if((ocupado+transportable.transporte())<capacidad){
			return true;
		}
		return false;
	}

	public void bajarUnidad(Transportable transportable){
		
	}
}
