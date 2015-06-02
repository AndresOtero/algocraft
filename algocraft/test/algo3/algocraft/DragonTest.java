package algo3.algocraft;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

public class DragonTest {
	@Test
	public void setTest() {
		Unidad unidad=(Unidad) new Dragon(Color.ROJO);
		Assert.assertTrue(Color.ROJO==unidad.color());
		Assert.assertEquals( 8,unidad.vision());
		Assert.assertEquals( 2,unidad.suministro());
		Assert.assertEquals( 6,unidad.tiempoDeConstruccion());
		Assert.assertEquals( 125,unidad.costoMineral());
		Assert.assertEquals( 50,unidad.costoGas());
	}
	@Test
	public void ataque(){
		UnidadDeAtaque unidad=(UnidadDeAtaque) new Dragon(Color.AMARILLO);
		Posicion fuente= new Posicion(0,0);
		Posicion enRango = new Posicion(1,0);
		Posicion fueraDeRango=new Posicion(100,100);
		Assert.assertEquals( 20,unidad.atacarPorTierra(fuente,enRango));
		Assert.assertEquals( 0,unidad.atacarPorTierra(fuente,fueraDeRango));
		Assert.assertEquals( 20,unidad.atacarPorAire(fuente,enRango));
		Assert.assertEquals( 0,unidad.atacarPorAire(fuente,fueraDeRango));
	}
	@Test
	public void recibirdanio(){
		Unidad unidad=(Unidad) new Dragon(Color.AMARILLO);
		unidad.recibirDanio(10);
		Assert.assertFalse(unidad.estaMuerto());
		unidad.recibirDanio(1000);
		Assert.assertTrue(unidad.estaMuerto());
	}
	@Test
	public void transportable() {
		Transportable transportable = new Dragon(Color.AZUL);
		Assert.assertEquals(4, transportable.transporte());
	}
	

}


