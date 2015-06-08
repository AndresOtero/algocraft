package algo3.algocraft;

import java.util.ArrayList;

import org.junit.Test;
import algo3.algocraft.unidades.*;

public class AtaqueTest {
	
	@Test
	public void ataqueValido(){
		Jugador jugador1 = new Jugador("jugador1",Color.AZUL,TipoRaza.PROTOSS);
		Jugador jugador2 = new Jugador("jugador2",Color.AMARILLO,TipoRaza.TERRAN);
		ArrayList <Jugador> jugadores = new ArrayList<Jugador>();
		jugadores.add(jugador1);
		jugadores.add(jugador2);
		Mapa mapa = Mapa.getInstance(5,5,jugadores);
		
		Zealot zealot = new Zealot(Color.AZUL);
		Marine mar = new Marine(Color.AMARILLO);
		Espectro esp = new Espectro(Color.AMARILLO);
		mapa.ponerTerrestre(new Posicion (5,1),mar);
		mapa.ponerAereo(new Posicion (5,1), esp);
		mapa.ponerTerrestre(new Posicion (5,2),zealot);
		
		
	}

}
