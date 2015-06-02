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
		danio = this.recibirDanioConEscudo(danio);
		vida = vida - danio;
	}

	private int recibirDanioConEscudo(int danio) {
		escudo = escudo - danio;
		if ((escudo) > 0) {
			return 0;
		}
		return danio - escudo;
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

}
