package algo3.algocraft.edificios;

import java.util.ArrayList;

import algo3.algocraft.*;
import algo3.algocraft.unidades.*;

public class Barraca extends EdificioCreador implements CreadorSoldados {
	private int costoGasMarine;
	private int costoMineralMarine;


	public Barraca(Color colorJugador) {
		this.vida = 1000;
		this.tiempoDeConstruccion = 12;
		this.costoMineralMarine =50;
		this.costoGasMarine = 0;
		this.color = colorJugador;
		this.unidadesEnCola = new ArrayList<Unidad>();
		this.unidadesCreadas = new ArrayList<Unidad>();
	}
	
	
	public boolean crearMarine(Jugador jug) {
		if ( jug.GasVespeno() > this.costoGasMarine && jug.Minerales()> this.costoMineralMarine){	
		this.agregarACola( new Marine ( this.color));
		jug.sacarGasVespeno(this.costoGasMarine);
		jug.sacarMineral(this.costoMineralMarine);
		return true;
		}
		return false;
	}
}