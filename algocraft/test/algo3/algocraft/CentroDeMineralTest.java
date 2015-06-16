package algo3.algocraft;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

import algo3.algocraft.edificios.Asimilador;
import algo3.algocraft.edificios.CentroDeMineral;
import algo3.algocraft.edificios.EdificioDeRecurso;

public class CentroDeMineralTest {

	@Test
	public void setTest() {
		Ser edificio=(Ser) new CentroDeMineral(null, Color.ROJO, null);
		Assert.assertTrue(Color.ROJO==edificio.color());
		Assert.assertEquals( 4,edificio.tiempoDeConstruccion());
	}
	@Test
	public void recibirdanio(){
		Ser edificio=(Ser) new CentroDeMineral(null, Color.ROJO, null);
		edificio.recibirDanio(10);
		Assert.assertFalse(edificio.estaMuerto());
		edificio.recibirDanio(1000);
		Assert.assertTrue(edificio.estaMuerto());
	}
	@Test
	public void recolectarRecursio(){
		Mineral mineral=new Mineral();
		CentroDeMineral edificio=(CentroDeMineral) new CentroDeMineral(mineral, Color.ROJO, null);
		Jugador jugador =new Jugador("andres", Color.AMARILLO, TipoRaza.PROTOSS);
		edificio.recolectar(jugador);
		Assert.assertEquals(10,jugador.Minerales() );
	}
	

}
