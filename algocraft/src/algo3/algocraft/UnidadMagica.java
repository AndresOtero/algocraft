package algo3.algocraft;

public abstract class UnidadMagica extends Unidad {
	protected int energia = 50;

	public void perderEnergia() {
		energia = 0;
	}

	public abstract void aumentarEnergia();
}
