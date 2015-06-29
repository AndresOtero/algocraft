package algo3.algocraft.unidades;

import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

import algo3.algocraft.exceptions.NoHayUnidadesEnElTransporte;

public abstract class UnidadDeTransporte extends Unidad implements Aerea{
	protected int capacidad;
	protected int ocupado;
	protected Queue<Transportable> transporte= new LinkedBlockingQueue<Transportable>() ;

	public Boolean subirUnidad(Transportable transportable) {
		if ((ocupado + transportable.transporte()) < capacidad) {
			transporte.add(transportable);
			ocupado+=transportable.transporte();
			return true;
		}
		return false;
	}

	public Transportable bajarUnidad() throws NoHayUnidadesEnElTransporte {
		try{
			Transportable transportable = transporte.remove();
			ocupado-=transportable.transporte();
			return transportable;
		}catch(NoSuchElementException e){
			throw new NoHayUnidadesEnElTransporte();
		}
		
		
	}
}
