package algo3.algocraft;

public abstract class Unidad {
	
	protected int dañoTierra;
	protected int dañoAire;
	protected int rangoAtaqueTierra;
	protected int rangoAtaqueAire;
	
	protected int vida;
	protected int escudo=0;
	protected int suministro;
	protected int tiempoDeConstruccion;
	
	protected int vision;
	
	/*costo tienen que ser recursos*/
	
	/*daño tienen que ser recursos*/

	public int vision(){
		return vision;
	}
	public int suministro(){
		return suministro;
	}
	public int tiempoDeConstruccion(){
		return tiempoDeConstruccion;
	}
	public void recibirDaño(int daño) {
		daño = this.recibirDañoConEscudo(daño);
		vida = vida - daño;
	}

	private int recibirDañoConEscudo(int daño) {
		escudo = escudo - daño;
		if ((escudo) > 0) {
			return 0;
		}
		return escudo;
	}

	public boolean estaMuerto() {
		return (vida > 0);
	}

	public int atacarPorTierra(Posicion fuente, Posicion destino) {
		if ((fuente.distancia(destino)) <= rangoAtaqueTierra) {
			return (dañoTierra);
		}
		return 0;
	}

	public int atacarPorAire(Posicion fuente, Posicion destino) {
		if ((fuente.distancia(destino)) <= rangoAtaqueAire) {
			return (dañoAire);
		}
		return 0;
	}

}
