package algo3.algocraft.unidades;

import algo3.algocraft.Posicion;

public abstract class UnidadDeAtaque extends Unidad {

	protected int danioTierra;
	protected int danioAire;
	protected int rangoAtaqueTierra;
	protected int rangoAtaqueAire;
	public int atacarPorTierra(Posicion fuente, Posicion destino) {
		if ((fuente.distancia(destino)) <= rangoAtaqueTierra) {
			return (danioTierra);
		}
		return 0;
	}

	public int atacarPorAire(Posicion fuente, Posicion destino) {
		if ((fuente.distancia(destino)) <= rangoAtaqueAire) {
			return (danioAire);
		}
		return 0;
	}

}
