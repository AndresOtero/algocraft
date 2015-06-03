package algo3.algocraft;

public abstract class Ser {
	protected int vida;
	protected int escudo = 0;
	protected int tiempoDeConstruccion;
	protected int costoMineral;
	protected int costoGas;
	protected Color color;

	public int tiempoDeConstruccion() {
		return tiempoDeConstruccion;
	}

	public void recibirDanio(int danio) {
		int nuevoDanio = this.recibirDanioConEscudo(danio);
		vida = vida + nuevoDanio;
	}

	private int recibirDanioConEscudo(int danio) {
		int escudoAtacado = escudo - danio;
		if ((escudoAtacado) > 0) {
			return 0;
		}
		return escudoAtacado;
	}

	public boolean estaMuerto() {
		return (vida <= 0);
	}

	public int costoMineral() {
		return costoMineral;
	}

	public int costoGas() {
		return costoGas;
	}

	public Color color() {
		return color;
	}

	public int vida() {
		return this.vida;
	}

	public int escudo() {
		return this.escudo;
	}

}