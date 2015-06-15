package algo3.algocraft;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

import algo3.algocraft.edificios.CentroDeMineral;
import algo3.algocraft.edificios.DepositoDeSuminisitros;

public class DepositoDeSuminisitrosTest {

	@Test
	public void setTest() {
		Ser edificio=(Ser) new DepositoDeSuminisitros(Color.ROJO, null);
		Assert.assertTrue(Color.ROJO==edificio.color());
		Assert.assertEquals( 6,edificio.tiempoDeConstruccion());
	}
	@Test
	public void recibirdanio(){
		Ser edificio=(Ser) new DepositoDeSuminisitros(Color.ROJO, null);
		edificio.recibirDanio(10);
		Assert.assertFalse(edificio.estaMuerto());
		edificio.recibirDanio(1000);
		Assert.assertTrue(edificio.estaMuerto());
	}

}
