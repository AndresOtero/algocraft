package algo3.algocraft;

import java.util.ArrayList;

import junit.framework.Assert;

import org.junit.Test;

import algo3.algocraft.edificios.*;
import algo3.algocraft.unidades.*;

public class PartidaBasicaHardcodeadaTest {
	@Test
	public void HayGanador(){
		// Inicializo
		Jugador jugador1 = new Jugador("jugador1",Color.AZUL,TipoRaza.PROTOSS);
		Jugador jugador2 = new Jugador("jugador2",Color.AMARILLO,TipoRaza.TERRAN);
		ArrayList <Jugador> jugadores = new ArrayList<Jugador>();
		jugadores.add(jugador1);
		jugadores.add(jugador2);
		Mapa mapa = Mapa.getInstance(5,5,jugadores);
		
		Zealot zealotj1 = new Zealot(Color.AZUL);
		Zealot zealot2j1 = new Zealot (Color.AZUL);
		Dragon dragonj1 = new Dragon(Color.AZUL);
		Scout scoutj1 = new Scout (Color.AZUL);
		
		Marine marinej2 = new Marine(Color.AMARILLO);
		Golliat golliatj2 = new Golliat(Color.AMARILLO);
		
		Pilon pil1 = new Pilon(Color.AZUL);
		DepositoDeSuminisitros depj2 = new DepositoDeSuminisitros(Color.AMARILLO);
		
		
		
		mapa.ponerTerrestre(new Posicion(4,1), dragonj1);
		mapa.ponerTerrestre(new Posicion(4,2), zealot2j1);
		mapa.ponerTerrestre(new Posicion(3,1), scoutj1);
		mapa.ponerTerrestre(new Posicion(3,2), zealotj1);
		mapa.ponerTerrestre(new Posicion(3,4), marinej2);
		mapa.ponerTerrestre(new Posicion(2,4), golliatj2 );
		mapa.ponerTerrestre(new Posicion(5,1), pil1);
		mapa.ponerTerrestre(new Posicion(5,3), depj2);
		
		// Algunas verificaciones 
		
		Assert.assertFalse(mapa.estaVaciaTerrestre(new Posicion(1,1)));
		Assert.assertTrue(mapa.estaVaciaTerrestre(new Posicion(1,5)));
		Assert.assertTrue(mapa.estaVaciaAereo(new Posicion(1,1)));
		Assert.assertTrue(mapa.ContenidoPosicion(new Posicion(3,2)).serEnLaCeldaTerrestre() == zealotj1);
		
		mapa.moverTerrestre(new Posicion(4,2),new Posicion (4,3));
		mapa.moverTerrestre(new Posicion(4,1),new Posicion (5,2));
		
		Assert.assertFalse(mapa.ContenidoPosicion(new Posicion(4,2)).ocupadoTerrestre());
		
		Assert.assertTrue(mapa.ContenidoPosicion(new Posicion(4,3)).serEnLaCeldaTerrestre() == zealot2j1);
		Assert.assertFalse(mapa.ContenidoPosicion(new Posicion(4,1)).ocupadoTerrestre());
		Assert.assertTrue(mapa.ContenidoPosicion(new Posicion(5,2)).serEnLaCeldaTerrestre() == dragonj1);
		
		// Ataque
		int i=0;
		while ( i < 100){
			dragonj1.atacarTierra(depj2);
			zealot2j1.atacarTierra(depj2);
			scoutj1.atacarTierra(marinej2);
			zealotj1.atacarTierra(golliatj2);
			i++;
		}
		
		Assert.assertTrue(marinej2.estaMuerto());
		Assert.assertTrue(depj2.estaMuerto());
		Assert.assertTrue(golliatj2.estaMuerto());
		
		 // mapa.borrarSerTerrestre(depj2); SE QUEJA DEL NULL . HAY QUE REVISAR ESO
		
		
	}
	
	

}
