package algo3.algocraft.unidades;

import algo3.algocraft.Ser;

public abstract class Unidad extends Ser {
	protected int vision;
	protected int suministro;

	public int vision() {
		return vision;
	}

	public int suministro() {
		return suministro;
	}

}
