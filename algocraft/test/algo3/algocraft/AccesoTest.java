package algo3.algocraft;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

import algo3.algocraft.edificios.*;
import algo3.algocraft.unidades.Dragon;
import algo3.algocraft.unidades.Unidad;

public class AccesoTest {
	@Test
	public void setTest() {
		Ser unidad=(Ser) new Acceso(Color.ROJO);
		Assert.assertTrue(Color.ROJO==unidad.color());
		Assert.assertEquals( 6,unidad.tiempoDeConstruccion());
		Assert.assertEquals( 125,unidad.costoMineral());
		Assert.assertEquals( 50,unidad.costoGas());
	}
	@Test
	public void recibirdanio(){
		Ser unidad=(Ser) new Acceso(Color.AMARILLO);
		unidad.recibirDanio(10);
		Assert.assertFalse(unidad.estaMuerto());
		unidad.recibirDanio(1000);
		Assert.assertTrue(unidad.estaMuerto());
	}


}
