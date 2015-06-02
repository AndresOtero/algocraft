package algo3.algocraft;

public class EdificioProtoss extends Edificio {
	protected int escudoActual;
	protected int escudoInicial;

	private int bajarEscudo (int ataque){
		this.escudoActual = this.escudoActual - ataque;
		return escudoActual;
		
	}
	
	@Override
	public int atacado(int ataque){
		int escudoRestante = this.bajarEscudo(ataque);
		if (escudoRestante < 0) this.bajarVida( - ataque);
		this.regenerarEscudo();
		return this.vida;
		
	}

	private void regenerarEscudo() {
		this.escudoActual = this.escudoInicial;
		
	}
	/*METODO SOLO DE PRUEBA, NO LE VEO UTILIDAD POR AHORA > Jc */
	public int valorEscudo (){
		return this.escudoActual;
		
	}
	
}


