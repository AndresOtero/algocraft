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
		Ser edificio=(Ser) new CentroDeMineral(null, Color.ROJO);
		Assert.assertTrue(Color.ROJO==edificio.color());
		Assert.assertEquals( 4,edificio.tiempoDeConstruccion());
		Assert.assertEquals( 50,edificio.costoMineral());
		Assert.assertEquals( 0,edificio.costoGas());
	}
	@Test
	public void recibirdanio(){
		Ser edificio=(Ser) new CentroDeMineral(null, Color.ROJO);
		edificio.recibirDanio(10);
		Assert.assertFalse(edificio.estaMuerto());
		edificio.recibirDanio(1000);
		Assert.assertTrue(edificio.estaMuerto());
	}
	@Test
	public void recolectarRecursio(){
		Mineral mineral=new Mineral();
		CentroDeMineral edificio=(CentroDeMineral) new CentroDeMineral(mineral, Color.ROJO);
		Assert.assertEquals(10, edificio.recolectar());
	}
	

}
