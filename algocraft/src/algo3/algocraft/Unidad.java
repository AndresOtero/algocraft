package algo3.algocraft;

public abstract class Unidad {
	protected int danioTierra;
	protected int danioAire;
	protected int rangoAtaqueTierra;
	protected int rangoAtaqueAire;
	protected int vida;
	protected int escudo=0;
	protected int suministro;
	protected int tiempoDeConstruccion;
	protected int vision;
	private int transporte;
	protected int costoMineral;
	protected int costoGas;
	private int tiempo;	

	public int vision(){
		return vision;
	}
	public int suministro(){
		return suministro;
	}
	public int tiempoDeConstruccion(){
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
		return escudo;
	}

	public boolean estaMuerto() {
		//si la vida es mayor a 0 devuelve true, osea que estaria muerto??? no es alrevez??
		return (vida > 0);
	}

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
