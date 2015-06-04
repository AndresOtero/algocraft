package algo3.algocraft;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

import algo3.algocraft.edificios.DepositoDeSuminisitros;
import algo3.algocraft.edificios.Pilon;

public class PilonTest {

	@Test
	public void setTest() {
		Ser edificio=(Ser) new Pilon(Color.ROJO);
		Assert.assertTrue(Color.ROJO==edificio.color());
		Assert.assertEquals( 5,edificio.tiempoDeConstruccion());
		Assert.assertEquals( 100,edificio.costoMineral());
		Assert.assertEquals( 0,edificio.costoGas());
	}
	@Test
	public void recibirdanio(){
		Ser edificio=(Ser) new Pilon(Color.ROJO);
		edificio.recibirDanio(10);
		Assert.assertFalse(edificio.estaMuerto());
		edificio.recibirDanio(1000);
		Assert.assertTrue(edificio.estaMuerto());
	}
}
