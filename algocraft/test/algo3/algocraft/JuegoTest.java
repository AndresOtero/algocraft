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

	@Test /*(expected = NoHayRecurso.class)*/
	public void testCrearUnidadSinRecurso() {
		Juego juego = Juego.getInstance();
		juego.crearJugador("fede", Color.AMARILLO, TipoRaza.TERRAN);
		juego.crearJugador("vader", Color.ROJO, TipoRaza.PROTOSS);
		
		juego.iniciarJuego();
		juego.crearCreadorSoldados(6, 6);
		Unidades unidad = Unidades.MARINE;
		
		Assert.assertEquals(juego.JugadorActual(), "vader");
		
		for(int i=0;i<20;i++){
			juego.pasarTurno();
		}
		
		
		Assert.assertEquals(juego.JugadorActual(), "vader");
		
		boolean entro = true;
		
		entro = entro & juego.crearUnidad(9, 6, unidad);
		
		assertEquals(false, entro);
	}
	
	@Test /*(expected = NoHayRecurso.class)*/
	
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
	public void testCrearEdificioSinRequisito(){
		Juego juego = Juego.getInstance();
		juego.crearJugador("fede", Color.AMARILLO, TipoRaza.TERRAN);
		juego.crearJugador("vader", Color.ROJO, TipoRaza.PROTOSS);
		juego.iniciarJuego();
		for(int i=0;i<5;i++){
			for(int j=0;j<5;j++){
				if((i!=1)&&(j!=1)&&(juego.ContenidoFilaColumna(i, j).fuenteRecurso()!=null)){
					juego.crearRecolectableMinerales(i,j);
				}
			}
		}
		juego.crearCreadorSoldados(6, 6);
		juego.pasarTurno();
		Assert.assertFalse(juego.hayGanador());
		for(int i=10;i<15;i++){
			for(int j=10;j<15;j++){
				if((i!=15)&&(j!=15)&&(juego.ContenidoFilaColumna(i, j).fuenteRecurso()!=null)){
					juego.crearRecolectableMinerales(i, j);
				}
			}
		}
		juego.crearCreadorSoldados(9,9);
		juego.pasarTurno();
		Assert.assertFalse(juego.hayGanador());
		
		
		
		
	}
	@Test
	public void testPartida(){
		Juego juego =  Juego.getInstance();
		juego.crearJugador("fede", Color.AMARILLO, TipoRaza.TERRAN);
		juego.crearJugador("vader", Color.ROJO, TipoRaza.PROTOSS);
		
		
		
	}
	

	
	@Test
	public void testPonerDosObjetosEnElMismoEspacio(){
		Juego juego =  Juego.getInstance();
		boolean entro = false;
		juego.crearJugador("fede", Color.AMARILLO, TipoRaza.TERRAN);
		juego.crearJugador("vader", Color.ROJO, TipoRaza.PROTOSS);
		
		juego.iniciarJuego();
		juego.crearCreadorSoldados(0,0);
		try{
			juego.crearCreadorSoldados(0,0);
		}catch(LaCeldaTerrestreEstaOcupada e){
			entro = true;
		}
		assertEquals(true, entro);
		
	}
	
	@Test /*(expected = NoHayRecurso.class)*/
	
	public void testCrearEdificioSinRecurso() {
		Juego juego = Juego.getInstance();
		juego.crearJugador("fede", Color.AMARILLO, TipoRaza.TERRAN);
		juego.crearJugador("vader", Color.ROJO, TipoRaza.PROTOSS);
		boolean entro = false;
		
		juego.iniciarJuego();
		juego.crearCreadorSoldados(5,0);
		try{
			juego.crearCreadorSoldados(6, 0);
			juego.crearCreadorSoldados(7, 0);
			juego.crearCreadorSoldados(8, 0);
			juego.crearCreadorSoldados(9, 0);
			juego.crearCreadorSoldados(10, 0);
			juego.crearCreadorSoldados(11, 0);
			juego.crearCreadorSoldados(12, 0);

		}catch(NoHayRecursosException e){
			entro = true;
		}
		
		assertEquals(true, entro);
	}
	

}

