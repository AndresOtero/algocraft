package algo3.algocraft;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

import algo3.algocraft.edificios.Asimilador;
import algo3.algocraft.edificios.EdificioDeRecurso;
import algo3.algocraft.edificios.Refineria;

public class RefineriaTest {


	@Test
	public void setTest() {
		Ser edificio=(Ser) new Refineria(null, Color.ROJO);
		Assert.assertTrue(Color.ROJO==edificio.color());
		Assert.assertEquals( 6,edificio.tiempoDeConstruccion());
	}
	@Test
	public void recibirdanio(){
		Ser edificio=(Ser) new Refineria(null, Color.ROJO);
		edificio.recibirDanio(10);
		Assert.assertFalse(edificio.estaMuerto());
		edificio.recibirDanio(1000);
		Assert.assertTrue(edificio.estaMuerto());
	}
	@Test
	public void recolectarRecursio(){
		VolcanGasVespeno volcan=new VolcanGasVespeno();
		EdificioDeRecurso edificio=(EdificioDeRecurso) new Refineria(volcan, Color.ROJO);
		Assert.assertEquals(10, edificio.recolectar());
	}
	

}
