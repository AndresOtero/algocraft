package algo3.algocraft;

public abstract class Unidad {
	
	protected int da�oTierra;
	protected int da�oAire;
	protected int rangoAtaqueTierra;
	protected int rangoAtaqueAire;
	
	protected int vida;
	protected int escudo=0;
	protected int suministro;
	protected int tiempoDeConstruccion;
	
	protected int vision;
	
	/*costo tienen que ser recursos*/
	
	/*da�o tienen que ser recursos*/

	public int vision(){
		return vision;
	}
	public int suministro(){
		return suministro;
	}
	public int tiempoDeConstruccion(){
		return tiempoDeConstruccion;
	}
	public void recibirDa�o(int da�o) {
		da�o = this.recibirDa�oConEscudo(da�o);
		vida = vida - da�o;
	}

	private int recibirDa�oConEscudo(int da�o) {
		escudo = escudo - da�o;
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
			return (da�oTierra);
		}
		return 0;
	}

	public int atacarPorAire(Posicion fuente, Posicion destino) {
		if ((fuente.distancia(destino)) <= rangoAtaqueAire) {
			return (da�oAire);
		}
		return 0;
	}

}
