package algo3.algocraft;

public abstract class Edificio {
	/*
	 * Podria llegar a usarse una clase Recurso en vez de int .. lo veremos
	 * despues por ahora alcanza >Jc
	 */
	protected int costoMineral;
	protected int costoGas;
	protected int tiempo;
	protected int vida;
	protected TipoRaza tipoRazaQueAcepta; /* Aca no hice nada, no se cuan necesario sera */
	
	
	/* Devuelve cuanto le queda de vida, si es 0 , esta destruido > Jc */
	public int atacado(int ataque) {
		return bajarVida(ataque);

	}

	protected int bajarVida(int ataque) {
		this.vida = this.vida - ataque;
		if (this.vida < 0)
			this.vida = 0;
		return this.vida;
	}

	/*
	 * Desde su creacion hasta su disponibilidad hay que esperar X cant de
	 * turnos, esta funcion avisa cuando se puede disponer de la construccion
	 * FALTARIA LA LOGICA DE TURNOS, PUEDE SER CAMBIADO ESTO > Jc
	 */
	public boolean disponible() {
		if (this.tiempo == 0)
			return true;
		return false;
	}

	/*
	 * Siguiendo la logica de arriba, como aviso que hay nuevo turno?? por ahora
	 * lo dejo como metodo de edificio, MODIFICABLE A FUTURO , cuando llega a 0
	 * , deja de importar . > Jc
	 */

	public void nuevoTurno() {
		if (this.tiempo > 0)
			this.tiempo = this.tiempo - 1;

	}
}
