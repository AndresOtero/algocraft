package algo3.algocraft.edificios;

import java.util.ArrayList;

import algo3.algocraft.*;
import algo3.algocraft.unidades.*;

public class Barraca extends EdificioCreador implements CreadorSoldados {
	public Barraca(Color colorJugador) {
		this.vida = 1000;
		this.tiempoDeConstruccion = 12;
		this.costoGas = 0;
		this.costoMineral = 150;
		this.color = colorJugador;
		this.unidadesEnCola = new ArrayList<Unidad>();
		this.unidadesCreadas = new ArrayList<Unidad>();
	}
	public void crearMarine() {
		this.agregarACola(new Marine(this.color));
}
