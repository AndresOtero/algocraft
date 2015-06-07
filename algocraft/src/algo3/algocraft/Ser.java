package algo3.algocraft;

import java.util.ArrayList;

import algo3.algocraft.unidades.Unidad;

public abstract class Ser {
	protected int vida;
	protected int escudo = 0;
	protected int tiempoDeConstruccion;
	protected int costoMineral;
	protected int costoGas;
	protected Color color;
	protected Movimiento movimiento=null;
	public Movimiento movimiento(){
		return movimiento;
	}
	public int tiempoDeConstruccion() {
		return this.tiempoDeConstruccion;
	}
	public void pasarTurno(){
		if(this.tiempoDeConstruccion==0){
			return;
		}
		this.tiempoDeConstruccion=this.tiempoDeConstruccion-1;
		return;
	}
	public boolean creado(){
		return (tiempoDeConstruccion==0);
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
		return this.color;
	}

	public int vida() {
		return this.vida;
	}

	public int escudo() {
		return this.escudo;
	}

}
