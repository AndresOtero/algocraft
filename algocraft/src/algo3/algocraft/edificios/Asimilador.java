package algo3.algocraft.edificios;

import algo3.algocraft.*;
import algo3.algocraft.unidades.*;

public class Asimilador extends EdificioDeRecurso implements RecolectableGas {
	public Asimilador(VolcanGasVespeno volcan, Color colorJugador,Posicion pos) {
		this.posicion=pos;
		this.vida = 450;
		this.escudo = 450;
		this.tiempoDeConstruccion = 6;
		this.fuenteRecurso = volcan;
		this.color = colorJugador;
	}
	public void agregarseAMapa(Mapa mapa){
		mapa.ponerEdificioGas(this.posicion(), this);
	}
	@Override
	public void recolectar(Jugador jugador) {
		jugador.agregarGasVespeno(fuenteRecurso.devolverRecurso());
	}

}
