package algo3.algocraft;

import java.util.ArrayList;

import algo3.algocraft.unidades.Unidad;

public abstract class Ser {
	protected Posicion posicion;
	protected int vida;
	protected int escudo = 0;
	protected int tiempoDeConstruccion;
	protected Color color;
	protected Movimiento movimiento=null;
	protected int suministro=0;
	public Posicion posicion() {
		return posicion;
	}
	public int suministro() {
		return suministro;
	}
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
