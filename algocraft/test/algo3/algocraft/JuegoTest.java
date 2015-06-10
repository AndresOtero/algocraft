package algo3.algocraft;

import static org.junit.Assert.*;

import java.awt.font.NumericShaper.Range;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

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
		juego.crearEdificio(TipoEdificio.CreadorSoldados, 25, 25);
		/*juego.crearUnidad("Marines");
		juego.crearUnidad("Marines");*/
		
		
	}
	@Test
	public void testCrearUnidadSinEspacio(){
		Juego juego = Juego.getInstance();
		String[] errores = new String[tamanioErrores];
		juego.agregarJugador("fede", Color.AMARILLO, TipoRaza.TERRAN, errores);
		juego.agregarJugador("vader", Color.ROJO, TipoRaza.PROTOSS, errores);
		
		juego.iniciarJuego();
		
		boolean entro = false;
		
		juego.crearEdificio("Barraca",0,0);
		juego.crearEdificio("Nexo mineral",1,1);
		juego.crearEdificio("Asimilador",2,2);
			
		juego.pasarTurno();
		juego.pasarTurno();
		juego.pasarTurno();
		juego.pasarTurno();
		juego.pasarTurno();
		juego.pasarTurno();
		juego.pasarTurno();
		juego.pasarTurno();
		juego.pasarTurno();
			
		entro = entro & juego.crearUnidad("Marines");
		entro = entro & juego.crearUnidad("Marines");
		entro = entro & juego.crearUnidad("Marines");
		entro = entro & juego.crearUnidad("Marines");
		entro = entro & juego.crearUnidad("Marines");
		entro = entro & juego.crearUnidad("Marines");
		entro = entro & juego.crearUnidad("Marines");
		entro = entro & juego.crearUnidad("Marines");
			
			
		assertEquals(false, entro);
		
	}
	public void testCrearEdificioSinRequisito(){
		Juego juego = Juego.getInstance();
		juego.crearJugador("fede", Color.AMARILLO, TipoRaza.TERRAN);
		juego.crearJugador("vader", Color.ROJO, TipoRaza.PROTOSS);

		
		juego.iniciarJuego();
		
		boolean entro = false;
		
		try{
			juego.crearEdificio("Fabrica",0,0);

		}catch(NoEstanLosRequisitosException e){
			entro = true;
		}catch(NoHayRecursosException e){
			
		}
		assertEquals(true, entro);
		
	}
	
	

}
