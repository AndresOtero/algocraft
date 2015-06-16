package algo3.algocraft.edificios;

import algo3.algocraft.*;

public class Refineria extends EdificioDeRecurso implements RecolectableGas {

	public Refineria(VolcanGasVespeno volcan,Color colorJugador,Posicion pos) {
		this.posicion=pos;
		this.vida = 750;
		this.tiempoDeConstruccion = 6;
		this.fuenteRecurso = volcan;
		this.color = colorJugador;
	}
	public void agregarseAMapa(Mapa mapa){
		mapa.ponerEdificioDeRecurso(this.posicion(), this);
	}
	@Override
	public void recolectar(Jugador jugador) {
		jugador.agregarGasVespeno(fuenteRecurso.devolverRecurso());
	}
	

}
