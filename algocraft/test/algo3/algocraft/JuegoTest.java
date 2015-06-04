package algo3.algocraft;

import static org.junit.Assert.*;

import java.awt.font.NumericShaper.Range;

import org.junit.Assert;
import org.junit.Test;

import algo3.algocraft.exceptions.NoEstanLosRequisitosException;
import algo3.algocraft.exceptions.NoHayEspacioException;
import algo3.algocraft.exceptions.NoHayRecursosException;

@SuppressWarnings("unused")
public class JuegoTest {

	private  final int tamanioErrores = 4;
	
	@Test
	
	public void testCrearUnidadSinRecurso() {
		Juego juego = Juego.getInstance();
		String[] errores = new String[tamanioErrores];
		juego.agregarJugador("fede", Color.AMARILLO, TipoRaza.TERRAN, errores);
		juego.agregarJugador("vader", Color.ROJO, TipoRaza.PROTOSS, errores);
		
		juego.iniciarJuego();
		
		boolean entro = false;
		
		try{
			juego.agregarEdificio("Barraca",0,0);
			juego.crearUnidad("Marines");
			juego.crearUnidad("Marines");
			juego.crearUnidad("Marines");
			juego.crearUnidad("Marines");
			
		}catch(NoHayRecursosException e){
			entro = true;
		}catch(NoHayEspacioException  | NoEstanLosRequisitosException e){
			
		}
		assertEquals(true, entro);
		
	}
	
	public void testCrearUnidadSinEspacio(){
		Juego juego = Juego.getInstance();
		String[] errores = new String[tamanioErrores];
		juego.agregarJugador("fede", Color.AMARILLO, TipoRaza.TERRAN, errores);
		juego.agregarJugador("vader", Color.ROJO, TipoRaza.PROTOSS, errores);
		
		juego.iniciarJuego();
		
		boolean entro = false;
		
		try{
			juego.agregarEdificio("Barraca",0,0);
			juego.agregarEdificio("Nexo mineral",1,1);
			juego.agregarEdificio("Asimilador",2,2);
			
			juego.pasarTurno();
			juego.pasarTurno();
			juego.pasarTurno();
			juego.pasarTurno();
			juego.pasarTurno();
			juego.pasarTurno();
			juego.pasarTurno();
			juego.pasarTurno();
			juego.pasarTurno();
			
			juego.crearUnidad("Marines");
			juego.crearUnidad("Marines");
			juego.crearUnidad("Marines");
			juego.crearUnidad("Marines");
			juego.crearUnidad("Marines");
			juego.crearUnidad("Marines");
			juego.crearUnidad("Marines");
			juego.crearUnidad("Marines");
			
			
			
		}catch(NoHayEspacioException e){
			entro = true;
		}catch(NoHayRecursosException  | NoEstanLosRequisitosException e){
			
		}
		assertEquals(true, entro);
		
	}
	public void testCrearEdificioSinRequisito(){
		Juego juego = Juego.getInstance();
		String[] errores = new String[tamanioErrores];
		juego.agregarJugador("fede", Color.AMARILLO, TipoRaza.TERRAN, errores);
		juego.agregarJugador("vader", Color.ROJO, TipoRaza.PROTOSS, errores);
		
		juego.iniciarJuego();
		
		boolean entro = false;
		
		try{
			juego.agregarEdificio("Fabrica",0,0);

		}catch(NoEstanLosRequisitosException e){
			entro = true;
		}catch(NoHayRecursosException e){
			
		}
		assertEquals(true, entro);
		
	}
	
	

}
