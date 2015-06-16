package algo3.algocraft.edificios;

import algo3.algocraft.*;

public class NexoMineral extends EdificioDeRecurso implements RecolectableMinerales{
	public NexoMineral(Mineral mineral, Color colorJugador,Posicion pos) {
		this.posicion=pos;
		this.vida = 250;
		this.escudo = 250;
		this.tiempoDeConstruccion = 4;
		this.fuenteRecurso = mineral;
		this.color=colorJugador;	
	}
	public void agregarseAMapa(Mapa mapa){
		mapa.ponerEdificioDeRecurso(this.posicion(), this);
	}
	@Override
	public void recolectar(Jugador jugador) {
		jugador.agregarMineral(fuenteRecurso.devolverRecurso());
	}
}
