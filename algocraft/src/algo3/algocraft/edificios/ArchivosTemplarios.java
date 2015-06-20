package algo3.algocraft.edificios;

import java.util.ArrayList;

import algo3.algocraft.*;
import algo3.algocraft.unidades.*;

public class ArchivosTemplarios extends EdificioCreador implements
		CreadorTerrestres {
	private int costoMineralTemplario;
	private int costoGasTemplario;

	public ArchivosTemplarios(Color colorJugador, Posicion pos) {
		this.posicion = pos;
		this.vida = 500;
		this.escudo = 500;
		this.tiempoDeConstruccion = 9;
		this.costoMineralTemplario = 50;
		this.costoGasTemplario = 150;
		this.color = colorJugador;
		this.unidadesEnCola = new ArrayList<Unidad>();
		this.unidadesCreadas = new ArrayList<Unidad>();
		this.id = 11;
	}

	public boolean crearAltoTemplario(Jugador jug) {
		if (jug.GasVespeno() > this.costoGasTemplario
				&& jug.Minerales() > this.costoMineralTemplario) {
			this.agregarACola(new AltoTemplario(this.color, this.posicion));
			jug.sacarGasVespeno(this.costoGasTemplario);
			jug.sacarMineral(this.costoMineralTemplario);
			return true;
		}
		return false;
	}

	@Override
	public int devolverID() {

		return this.id;
	}
}
