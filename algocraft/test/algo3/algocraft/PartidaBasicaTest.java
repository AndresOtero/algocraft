
package algo3.algocraft;

import java.util.ArrayList;

import junit.framework.Assert;

import org.junit.Test;

import algo3.algocraft.edificios.*;
import algo3.algocraft.exceptions.NoEsPosibleSubirException;
import algo3.algocraft.exceptions.NoHayUnidadesEnElTransporte;
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
		Assert.assertEquals(juego.gasJugadorActual(),800);
		juego.pasarTurno();
		Assert.assertEquals(juego.gasJugadorActual(),810);
		juego.pasarTurno();
		Assert.assertEquals(juego.gasJugadorActual(),810);
		juego.pasarTurno();

		Assert.assertFalse(juego.hayGanador());
		Assert.assertEquals(juego.ContenidoFilaColumna(1, 1).serEnLaCeldaTerrestre().color(), Color.VERDE);
		Assert.assertEquals(juego.JugadorActual(), "Andres");
		crearRecolectableMineral(juego);
		juego.crearCreadorSoldados(6,6);
		juego.crearCreadorAereos(0,7);
		for(int i=0;i<20;i++){
			juego.pasarTurno();
		}
		Assert.assertEquals(juego.JugadorActual(), "Andres");
		juego.crearZealot(6,6);
		juego.crearNaveTransporteProtoss(0, 7);
		for(int i=0;i<20;i++){
			juego.pasarTurno();
		}
		
		Assert.assertEquals(juego.JugadorActual(), "Andres");
		Posicion posicionNave= buscarUnidadAerea(juego);
		Assert.assertTrue(juego.ContenidoFilaColumna(posicionNave.x(), posicionNave.y()).ocupadoTerrestre());	
		Assert.assertFalse(juego.ContenidoFilaColumna(posicionNave.x(), posicionNave.y()).ocupadoAerea());	
		juego.elevar(posicionNave.x(), posicionNave.y());
		Assert.assertFalse(juego.ContenidoFilaColumna(posicionNave.x(), posicionNave.y()).ocupadoTerrestre());	
		Assert.assertTrue(juego.ContenidoFilaColumna(posicionNave.x(), posicionNave.y()).ocupadoAerea());	
		juego.descender(posicionNave.x(), posicionNave.y());
		Assert.assertTrue(juego.ContenidoFilaColumna(posicionNave.x(), posicionNave.y()).ocupadoTerrestre());	
		Assert.assertFalse(juego.ContenidoFilaColumna(posicionNave.x(), posicionNave.y()).ocupadoAerea());	
		juego.elevar(posicionNave.x(), posicionNave.y());
		Assert.assertFalse(juego.ContenidoFilaColumna(0, 9).ocupadoAerea());	
		juego.moverPosicionAereo(posicionNave.x(), posicionNave.y(), 6, 5);
		Assert.assertFalse(juego.ContenidoFilaColumna(posicionNave.x(), posicionNave.y()).ocupadoAerea());	
		Assert.assertTrue(juego.ContenidoFilaColumna(6, 5).ocupadoAerea());	
		juego.pasarTurno();
		juego.pasarTurno();
		juego.descender(6, 5);//Bajo la nave
		Posicion posicionZealot = buscarUnidad(juego);
		try {
			juego.subirAlTransporte(posicionZealot.x(), posicionZealot.y(), 6,5);
		} catch (NoEsPosibleSubirException e) {
		}
		Assert.assertFalse(juego.ContenidoFilaColumna(posicionZealot.x(), posicionZealot.y()).ocupadoTerrestre());	
		juego.pasarTurno();
		juego.pasarTurno();
		try {
			juego.bajarDelTransporte(6, 5);
		} catch (NoHayUnidadesEnElTransporte e) {
		}
		juego.moverPosicionTerrestre(6, 5, posicionNave.x(), posicionNave.y());
		 posicionZealot = buscarUnidad(juego);
		Assert.assertTrue(juego.ContenidoFilaColumna(posicionZealot.x(), posicionZealot.y()).ocupadoTerrestre());	
		Assert.assertTrue(juego.moverPosicionTerrestre(posicionZealot.x(), posicionZealot.y(), 8, 9));
		Assert.assertTrue(juego.ContenidoFilaColumna(8,9).ocupadoTerrestre());		
		Assert.assertFalse(juego.ContenidoFilaColumna(posicionZealot.x(), posicionZealot.y()).ocupadoTerrestre());	
		juego.pasarTurno();
		Assert.assertFalse(juego.hayGanador());
		Assert.assertEquals(juego.JugadorActual(), "Federico");
		juego.pasarTurno();
		Assert.assertEquals(juego.JugadorActual(), "Andres");
		Assert.assertFalse(juego.hayGanador());
		Assert.assertTrue(juego.moverPosicionTerrestre(8, 9, 12, 12));
		Assert.assertTrue(juego.ContenidoFilaColumna(12,12).ocupadoTerrestre());		
		Assert.assertFalse(juego.ContenidoFilaColumna(8,9).ocupadoTerrestre());
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

		Assert.assertTrue(juego.hayGanador());
		
		
		
		
		


	}
	@Test
	public void andanLasMagiasProtoss(){
		Juego juego =Juego.getInstance();
		juego.crearJugador("Andres", Color.VERDE, TipoRaza.PROTOSS);
		juego.crearJugador("Federico", Color.AZUL, TipoRaza.TERRAN);
		juego.iniciarJuego();
		juego.pasarTurno();
		juego.pasarTurno();
		juego.pasarTurno();
		crearRecolectableMineral(juego);
		juego.crearCreadorSoldados(6,6);
		juego.crearCreadorAereos(0,5);
		juego.crearCreadorTerrestres(0,7);
		
		for(int i=0;i<20;i++){
			juego.pasarTurno();
		}
		Assert.assertEquals(juego.JugadorActual(), "Andres");
		juego.crearAltoTemplario(0, 7);
		for(int i=0;i<19;i++){
			juego.pasarTurno();
		}
		Assert.assertEquals(juego.JugadorActual(), "Federico");
		juego.crearCreadorSoldados(9,9);
		for(int i=0 ; i< 500 ;i++) juego.pasarTurno();
		Assert.assertEquals(juego.JugadorActual(), "Federico");
		juego.crearMarine(9,9);
		for(int i = 0; i< 201 ; i++) juego.pasarTurno();
		Assert.assertEquals(juego.JugadorActual(), "Andres");
		
		Posicion posicionTemplario = this.buscarTemplario(juego);
		ArrayList<Unidad> atacados = juego.ataqueMagicoEnRadio(posicionTemplario.x(), posicionTemplario.y(), 8, 9);
		Assert.assertTrue(atacados.size() == 1);
	
		
	}

	@Test
	public void andanLasMagiasTerran(){
		Juego juego =Juego.getInstance();
		juego.crearJugador("Andres", Color.VERDE, TipoRaza.TERRAN);
		juego.crearJugador("Federico", Color.AZUL, TipoRaza.TERRAN);
		juego.iniciarJuego();
		juego.pasarTurno();
		juego.pasarTurno();
		juego.pasarTurno();
		crearRecolectableMineral(juego);
		juego.crearCreadorSoldados(6,6);
		juego.crearCreadorTerrestres(0,5);
		juego.crearCreadorAereos(0,7);
	
		
		for(int i=0;i<20;i++){
			juego.pasarTurno();
		}
		Assert.assertEquals(juego.JugadorActual(), "Andres");
		juego.crearNaveCiencia(0, 7);
		for(int i=0;i<19;i++){
			juego.pasarTurno();
		}
		Assert.assertEquals(juego.JugadorActual(), "Federico");
		juego.crearCreadorSoldados(9,9);
		for(int i=0 ; i< 500 ;i++) juego.pasarTurno();
		Assert.assertEquals(juego.JugadorActual(), "Federico");
		juego.crearMarine(9,9);
		for(int i = 0; i< 201 ; i++) juego.pasarTurno();
		Assert.assertEquals(juego.JugadorActual(), "Andres");
		
		juego.ataqueRadiacion(0, 6, 8, 9);
		for(int i = 0; i< 1000; i++){
			juego.pasarTurno();
		}
		
		Assert.assertFalse(juego.ContenidoFilaColumna(8, 9).ocupadoTerrestre());
	
		
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
	private Posicion buscarUnidadAerea(Juego juego) {
		for(int i=0;i<2;i+=1){
			for(int j=6;j<9;j+=1){
				if(juego.ContenidoFilaColumna(i, j).ocupadoTerrestre()&&!(i==0&&j==7)){
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
					juego.crearRecolectableMinerales(i, j);
					return;
				}
			}
		}
	}
	
	private Posicion buscarTemplario(Juego juego){
			for(int i=0;i<15;i+=1){
				for(int j=2;j<15;j+=1){
					
					if(juego.ContenidoFilaColumna(i, j).ocupadoTerrestre()&& juego.ContenidoFilaColumna(i, j).serEnLaCeldaTerrestre().getClass().getName() == "algo3.algocraft.unidades.AltoTemplario"){
						return new Posicion(i,j); 
					}
				}
			}
			return null;
	}
	

}
