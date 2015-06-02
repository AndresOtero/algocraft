package algo3.algocraft;

public class Transporte extends Unidad implements Transportador {
	private int ocupado=0;
	private int capacidad=8;
	
	@Override
	public Boolean subirUnidad(Transportable transportable) {
		return true;
	}

	@Override
	public void bajarUnidad(Transportable transportable) {
		// TODO Auto-generated method stub
		
	}

}
