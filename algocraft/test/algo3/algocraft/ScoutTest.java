package algo3.algocraft;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

import algo3.algocraft.unidades.*;

public class ScoutTest {

	@Test
	public void setTest() {
		Unidad unidad=(Unidad) new Scout(Color.ROJO);
		Assert.assertTrue(Color.ROJO==unidad.color());
		Assert.assertEquals( 7,unidad.vision());
		Assert.assertEquals( 3,unidad.suministro());
		Assert.assertEquals( 9,unidad.tiempoDeConstruccion());
		Assert.assertEquals( 300,unidad.costoMineral());
		Assert.assertEquals( 150,unidad.costoGas());
	}
	@Test
	public void ataque(){
		UnidadDeAtaque unidad=(UnidadDeAtaque) new Scout(Color.AMARILLO);
		Posicion fuente= new Posicion(0,0);
		Posicion enRango = new Posicion(1,0);
		Posicion fueraDeRango=new Posicion(100,100);
		Assert.assertEquals( 8,unidad.atacarPorTierra(fuente,enRango));
		Assert.assertEquals( 0,unidad.atacarPorTierra(fuente,fueraDeRango));
		Assert.assertEquals( 14,unidad.atacarPorAire(fuente,enRango));
		Assert.assertEquals( 0,unidad.atacarPorAire(fuente,fueraDeRango));
	}
	@Test
	public void recibirdanio(){
		Unidad unidad=(Unidad) new Scout(Color.AMARILLO);
		unidad.recibirDanio(10);
		Assert.assertFalse(unidad.estaMuerto());
		unidad.recibirDanio(1000);
		Assert.assertTrue(unidad.estaMuerto());
	}
	

}
