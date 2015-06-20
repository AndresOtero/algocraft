package algo3.algocraft.unidades;

import algo3.algocraft.Color;
import algo3.algocraft.Movimiento;
import algo3.algocraft.Posicion;

public class NaveTransporteTerran extends UnidadDeTransporte {
	public NaveTransporteTerran(Color colorJugador,Posicion pos) {
		this.posicion=pos;
		ocupado = 0;
		capacidad = 8;
		
		vision = 8;
		tiempoDeConstruccion = 7;
		suministro = 2;
		vida = 150;
		escudo = 0;
		this.color = colorJugador;
		movimiento=Movimiento.Aereo;
		this.id = 16;

	}
	@Override
	public int devolverID() {
		
		return this.id;
		}
}
