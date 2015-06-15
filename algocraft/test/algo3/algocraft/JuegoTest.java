package algo3.algocraft;

import static org.junit.Assert.*;

import java.awt.font.NumericShaper.Range;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import algo3.algocraft.exceptions.LaCeldaAereaEstaOcupada;
import algo3.algocraft.exceptions.LaCeldaTerrestreEstaOcupada;
import algo3.algocraft.exceptions.NoEstanLosRequisitosException;
import algo3.algocraft.exceptions.NoHayEspacioException;
import algo3.algocraft.exceptions.NoHayRecursosException;

@SuppressWarnings("unused")
public class JuegoTest {

	//@Test /*(expected = NoHayRecurso.class)*/
	public void testCrearUnidadSinRecurso() {
		Juego juego = Juego.getInstance();
		juego.crearJugador("fede", Color.AMARILLO, TipoRaza.TERRAN);
		juego.crearJugador("vader", Color.ROJO, TipoRaza.PROTOSS);
		
		juego.iniciarJuego();
		juego.crearEdificio(TipoEdificio.CreadorSoldados, 6, 6);
		Unidades unidad = Unidades.MARINE;
		
		Assert.assertEquals(juego.JugadorActual(), "vader");
		
		/*juego.pasarTurno();
		juego.pasarTurno();
		juego.pasarTurno();
		juego.pasarTurno();
		juego.pasarTurno();
		juego.pasarTurno();
		juego.pasarTurno();
		juego.pasarTurno();*/
		for(int i=0;i<20;i++){
			juego.pasarTurno();
		}
		
		
		Assert.assertEquals(juego.JugadorActual(), "vader");
		
		boolean entro = true;
		
		entro = entro & juego.crearUnidad(9, 6, unidad);
		/*entro = entro & juego.crearUnidad(1, 0, unidad);
		entro = entro & juego.crearUnidad(2, 0, unidad);
		entro = entro & juego.crearUnidad(3, 0, unidad);
		entro = entro & juego.crearUnidad(4, 0, unidad);
		entro = entro & juego.crearUnidad(5, 0, unidad);*/
		
		assertEquals(false, entro);
	}
	
	//@Test /*(expected = NoHayRecurso.class)*/
	
	public void testCrearUnidadSinEdificio() {
		Juego juego = Juego.getInstance();
		juego.crearJugador("fede", Color.AMARILLO, TipoRaza.TERRAN);
		juego.crearJugador("vader", Color.ROJO, TipoRaza.PROTOSS);
		
		juego.iniciarJuego();
		//juego.crearEdificio(TipoEdificio.CreadorSoldados, 7, 7);
		Unidades unidad = Unidades.MARINE;
		boolean entro = true;
		
		entro = entro & juego.crearUnidad(7, 7, unidad);
		
		assertEquals(false, entro);
	}
	@Test
	
	public void testCrearUnidadSinEspacio(){
		Juego juego = Juego.getInstance();
		juego.crearJugador("fede", Color.AMARILLO, TipoRaza.TERRAN);
		juego.crearJugador("vader", Color.ROJO, TipoRaza.PROTOSS);
		
		juego.iniciarJuego();
		String a = juego.JugadorActual();
		System.out.print(a);
		
		boolean entro = true;
		
		TipoEdificio tipo = TipoEdificio.CreadorSoldados;
		juego.crearEdificio(tipo, 1, 0);
		System.out.print("aaa"+ juego.ContenidoFilaColumna(1,0).serEnLaCeldaTerrestre()+"\n");
		
		for(int i=0;i<5;i++){
			for(int j=0;j<5;j++){
				Celda celda = juego.ContenidoFilaColumna(i, j);
				if((celda.fuenteRecurso() != null)&&(i!=1)&&(j!=1))juego.crearEdificio(TipoEdificio.RecolectableMinerales, i, j);
			}
		}

			
		for(int i=0;i<20;i++){
			juego.pasarTurno();
		}
		
		Unidades unidad = Unidades.ZEALOT;
		entro = entro & juego.crearUnidad(1, 0, unidad);
		entro = entro & juego.crearUnidad(1, 0, unidad);
		entro = entro & juego.crearUnidad(1, 0, unidad);
		entro = entro & juego.crearUnidad(1, 0, unidad);
		entro = entro & juego.crearUnidad(1, 0, unidad);
		entro = entro & juego.crearUnidad(1, 0, unidad);
		entro = entro & juego.crearUnidad(1, 0, unidad);
		entro = entro & juego.crearUnidad(1, 0, unidad);
			
			
		assertEquals(false, entro);
		
	}
	//@Test
	public void testCrearEdificioSinRequisito(){
		Juego juego = Juego.getInstance();
		juego.crearJugador("fede", Color.AMARILLO, TipoRaza.TERRAN);
		juego.crearJugador("vader", Color.ROJO, TipoRaza.PROTOSS);
		juego.iniciarJuego();
		for(int i=0;i<5;i++){
			for(int j=0;j<5;j++){
				if((i!=1)&&(j!=1)&&(juego.ContenidoFilaColumna(i, j).fuenteRecurso()!=null)){
					juego.crearEdificio(TipoEdificio.RecolectableMinerales,i,j);
				}
			}
		}
		juego.crearEdificio(TipoEdificio.CreadorSoldados,6,6);
		juego.pasarTurno();
		Assert.assertTrue(juego.hayGanador());
		for(int i=10;i<15;i++){
			for(int j=10;j<15;j++){
				if((i!=15)&&(j!=15)&&(juego.ContenidoFilaColumna(i, j).fuenteRecurso()!=null)){
					juego.crearEdificio(TipoEdificio.RecolectableMinerales,i,j);
				}
			}
		}
		juego.crearEdificio(TipoEdificio.CreadorSoldados,9,9);
		juego.pasarTurno();
		Assert.assertTrue(juego.hayGanador());
		
		
		
		
	}
	//@Test
	public void testPartida(){
		Juego juego =  Juego.getInstance();
		juego.crearJugador("fede", Color.AMARILLO, TipoRaza.TERRAN);
		juego.crearJugador("vader", Color.ROJO, TipoRaza.PROTOSS);
		
		
		
	}
	

	
	//@Test
	public void testPonerDosObjetosEnElMismoEspacio(){
		Juego juego =  Juego.getInstance();
		boolean entro = false;
		juego.crearJugador("fede", Color.AMARILLO, TipoRaza.TERRAN);
		juego.crearJugador("vader", Color.ROJO, TipoRaza.PROTOSS);
		
		juego.iniciarJuego();
		juego.crearEdificio(TipoEdificio.CreadorSoldados, 0, 0);
		try{
			juego.crearEdificio(TipoEdificio.CreadorSoldados, 0, 0);
		}catch(LaCeldaTerrestreEstaOcupada e){
			entro = true;
		}
		assertEquals(true, entro);
		
	}
	
	//@Test /*(expected = NoHayRecurso.class)*/
	
	public void testCrearEdificioSinRecurso() {
		Juego juego = Juego.getInstance();
		juego.crearJugador("fede", Color.AMARILLO, TipoRaza.TERRAN);
		juego.crearJugador("vader", Color.ROJO, TipoRaza.PROTOSS);
		boolean entro = false;
		
		juego.iniciarJuego();
		juego.crearEdificio(TipoEdificio.CreadorSoldados, 5, 0);
		try{
			juego.crearEdificio(TipoEdificio.CreadorSoldados, 7, 0);
			juego.crearEdificio(TipoEdificio.CreadorSoldados, 8, 0);
			juego.crearEdificio(TipoEdificio.CreadorSoldados, 4, 0);
			juego.crearEdificio(TipoEdificio.CreadorSoldados, 9, 0);
			juego.crearEdificio(TipoEdificio.CreadorSoldados, 10, 0);
			juego.crearEdificio(TipoEdificio.CreadorSoldados, 6, 0);
			juego.crearEdificio(TipoEdificio.CreadorSoldados, 9, 7);
			juego.crearEdificio(TipoEdificio.CreadorSoldados, 4, 8);
			juego.crearEdificio(TipoEdificio.CreadorSoldados, 9, 9);
			juego.crearEdificio(TipoEdificio.CreadorSoldados, 10, 9);
		}catch(NoHayRecursosException e){
			entro = true;
		}
		
		assertEquals(true, entro);
	}
	

}
