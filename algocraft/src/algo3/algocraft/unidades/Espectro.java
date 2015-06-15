package algo3.algocraft.unidades;

import algo3.algocraft.*;

public class Espectro extends UnidadDeAtaque implements Terrestre, Aerea {

	public Espectro(Color colorJugador,Posicion pos) {
		this.posicion=pos;
		danioTierra = 8;
		danioAire = 20;
		rangoAtaqueTierra = 5;
		rangoAtaqueAire = 5;
		
		vida = 120;
		suministro = 2;
		tiempoDeConstruccion = 8;
		vision = 7;
		color = colorJugador;
		movimiento=Movimiento.Aereo;

	}

}
