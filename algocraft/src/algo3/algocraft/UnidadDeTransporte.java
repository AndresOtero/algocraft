package algo3.algocraft;

import java.util.ArrayList;

public abstract class UnidadDeTransporte extends Unidad {
	protected int capacidad;
	protected int ocupado;
	protected ArrayList<Transportable> transporte;
	public Boolean subirUnidad(Transportable transportable){
		if((ocupado+transportable.transporte())<capacidad){
			return true;
		}
		return false;
	}

	public void bajarUnidad(Transportable transportable){
		
	}
}
