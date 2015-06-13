
package algo3.algocraft;

import java.util.ArrayList;

import junit.framework.Assert;

import org.junit.Test;

import algo3.algocraft.edificios.*;
import algo3.algocraft.unidades.*;

public class PartidaBasicaTest {
	@Test
	public void HayGanador(){
		Juego juego =Juego.getInstance();
		juego.crearJugador("Andres", Color.VERDE, TipoRaza.PROTOSS);
		juego.crearJugador("Federico", Color.AZUL, TipoRaza.TERRAN);
		juego.iniciarJuego();
		Assert.assertFalse(juego.hayGanador());
		Assert.assertEquals(juego.JugadorActual(), "Federico");
		juego.pasarTurno();
		Assert.assertFalse(juego.hayGanador());
		Assert.assertEquals(juego.ContenidoFilaColumna(1, 1).serEnLaCeldaTerrestre().color(), Color.VERDE);
		Assert.assertEquals(juego.JugadorActual(), "Andres");
		crearRecolectableMineral(juego);
		juego.crearEdificio(TipoEdificio.CreadorSoldados, 6, 6);
		for(int i=0;i<20;i++){
			juego.pasarTurno();
		}
		Assert.assertEquals(juego.JugadorActual(), "Andres");
		juego.crearUnidad(6, 6, Unidades.ZEALOT);
		for(int i=0;i<12;i++){
			juego.pasarTurno();
		}
		Assert.assertEquals(juego.JugadorActual(), "Andres");
		Posicion posicionZealot= buscarUnidad(juego);
		Assert.assertTrue(juego.ContenidoFilaColumna(posicionZealot.abscisa(), posicionZealot.ordenada()).ocupadoTerrestre());	
		Assert.assertTrue(juego.moverPosicionTerrestre(posicionZealot.abscisa(), posicionZealot.ordenada(), 9, 9));
		Assert.assertTrue(juego.ContenidoFilaColumna(9,9).ocupadoTerrestre());		
		Assert.assertFalse(juego.ContenidoFilaColumna(posicionZealot.abscisa(), posicionZealot.ordenada()).ocupadoTerrestre());	
		juego.pasarTurno();
		Assert.assertFalse(juego.hayGanador());
		Assert.assertEquals(juego.JugadorActual(), "Federico");
		juego.pasarTurno();
		Assert.assertEquals(juego.JugadorActual(), "Andres");
		Assert.assertFalse(juego.hayGanador());
		Assert.assertTrue(juego.moverPosicionTerrestre(9, 9, 12, 12));
		Assert.assertTrue(juego.ContenidoFilaColumna(12,12).ocupadoTerrestre());		
		Assert.assertFalse(juego.ContenidoFilaColumna(9,9).ocupadoTerrestre());
		juego.pasarTurno();
		Assert.assertFalse(juego.hayGanador());
		Assert.assertEquals(juego.JugadorActual(), "Federico");
		juego.pasarTurno();
		Assert.assertEquals(juego.JugadorActual(), "Andres");
		Assert.assertFalse(juego.hayGanador());
		Assert.assertTrue(juego.moverPosicionTerrestre(12, 12, 14, 15));
		Assert.assertTrue(juego.ContenidoFilaColumna(14,15).ocupadoTerrestre());		
		Assert.assertFalse(juego.ContenidoFilaColumna(12,12).ocupadoTerrestre());
		Assert.assertTrue(juego.atacarTierra(14, 15, 15, 15));
		Assert.assertTrue(juego.ContenidoFilaColumna(15,15).ocupadoTerrestre());		
		Ser ser = juego.ContenidoFilaColumna(15,15).serEnLaCeldaTerrestre();	
		Assert.assertEquals(ser.vida(),742);//da�o en refineria
		juego.pasarTurno();
		Assert.assertFalse(juego.hayGanador());
		Assert.assertEquals(juego.JugadorActual(), "Federico");
		juego.pasarTurno();
		atacar(juego);
		Assert.assertEquals(juego.JugadorActual(), "Andres");
		Assert.assertEquals(juego.ContenidoFilaColumna(15,15).serEnLaCeldaTerrestre().vida(),6);//da�o en refineria		
		Assert.assertTrue(juego.atacarTierra(14, 15, 15, 15));
		Assert.assertFalse(juego.ContenidoFilaColumna(15,15).ocupadoTerrestre());		
		juego.pasarTurno();
		Assert.assertFalse(juego.hayGanador());



	}
	private void atacar(Juego juego) {
		for(int i=0;i<184;i++){
			if(i%2==0){
				Assert.assertEquals(juego.JugadorActual(), "Andres");
				Assert.assertFalse(juego.hayGanador());
				Assert.assertTrue(juego.atacarTierra(14, 15, 15, 15));
				Assert.assertEquals(juego.ContenidoFilaColumna(15,15).serEnLaCeldaTerrestre().vida(),742-(1+i/2)*8);//da�o en refineria		
			}
			juego.pasarTurno();
		}
	}
	private Posicion buscarUnidad(Juego juego) {
		for(int i=5;i<8;i+=1){
			for(int j=5;j<8;j+=1){
				if(juego.ContenidoFilaColumna(i, j).ocupadoTerrestre()&&!(i==6&&j==6)){
					return new Posicion(i,j);
				}
			}
		}
		return null;
	}
	public void crearRecolectableMineral(Juego juego){
		for(int i=0;i<5;i++){
			for(int j=0;j<5;j++){
				Celda celda = juego.ContenidoFilaColumna(i, j);
				if((celda.fuenteRecurso() != null)&&(i!=1)&&(j!=1)){
					juego.crearEdificio(TipoEdificio.RecolectableMinerales, i, j);
					return;
				}
			}
		}
	}
	
	

}