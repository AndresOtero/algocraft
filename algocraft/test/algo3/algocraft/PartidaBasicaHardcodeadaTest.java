package algo3.algocraft;

import java.util.ArrayList;

import junit.framework.Assert;

import org.junit.Test;

import algo3.algocraft.edificios.*;
import algo3.algocraft.unidades.*;

public class PartidaBasicaHardcodeadaTest {
	@Test
	public void HayGanador(){
		Juego juego =Juego.getInstance();
		juego.crearJugador("Andres", Color.ROJO, TipoRaza.PROTOSS);
		juego.crearJugador("Federico", Color.AZUL, TipoRaza.TERRAN);
		juego.iniciarJuego();
		Assert.assertFalse(juego.hayGanador());
		Assert.assertEquals(juego.JugadorActual(), "Federico");
		juego.pasarTurno();
		Assert.assertFalse(juego.hayGanador());
		Assert.assertEquals(juego.ContenidoFilaColumna(1, 1).serEnLaCeldaTerrestre().color(), Color.ROJO);
		Assert.assertEquals(juego.JugadorActual(), "Andres");
		crearRecolectableMineral(juego);
		juego.crearEdificio(TipoEdificio.CreadorSoldados, 6, 6);
		for(int i=0;i<20;i++){
			juego.pasarTurno();
		}
		Assert.assertEquals(juego.JugadorActual(), "Andres");
		juego.crearUnidad(6, 6, Unidades.ZEALOT);
		for(int i=0;i<6;i++){
			juego.pasarTurno();
		}
		Assert.assertEquals(juego.JugadorActual(), "Andres");
		Assert.assertTrue(juego.ContenidoFilaColumna(6, 6).ocupadoTerrestre());	
		juego.moverPosicionTerrestre(6, 6, 9, 9);
	
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
