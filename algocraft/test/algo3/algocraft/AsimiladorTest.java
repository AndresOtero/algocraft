package algo3.algocraft;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

import algo3.algocraft.edificios.Acceso;
import algo3.algocraft.edificios.Asimilador;
import algo3.algocraft.edificios.EdificioDeRecurso;

public class AsimiladorTest {

	@Test
	public void setTest() {
		Ser edificio=(Ser) new Asimilador(null, Color.ROJO, null);
		Assert.assertTrue(Color.ROJO==edificio.color());
		Assert.assertEquals( 6,edificio.tiempoDeConstruccion());
	}
	@Test
	public void recibirdanio(){
		Ser edificio=(Ser) new Asimilador(null, Color.ROJO, null);
		edificio.recibirDanio(10);
		Assert.assertFalse(edificio.estaMuerto());
		edificio.recibirDanio(1000);
		Assert.assertTrue(edificio.estaMuerto());
	}
	@Test
	public void recolectarRecursio(){
		VolcanGasVespeno volcan=new VolcanGasVespeno();
		EdificioDeRecurso edificio=(EdificioDeRecurso) new Asimilador(volcan, Color.ROJO, null);
		Jugador jugador =new Jugador("andres", Color.AMARILLO, TipoRaza.PROTOSS);
		edificio.recolectar(jugador);
		Assert.assertEquals(10,jugador.GasVespeno() );	
		}
	

}
