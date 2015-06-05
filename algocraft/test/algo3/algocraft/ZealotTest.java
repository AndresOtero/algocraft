package algo3.algocraft;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

import algo3.algocraft.unidades.*;

public class ZealotTest {

	@Test
	public void setTest() {
		Unidad unidad=(Unidad) new Zealot(Color.ROJO);
		Assert.assertTrue(Color.ROJO==unidad.color());
		Assert.assertEquals( 7,unidad.vision());
		Assert.assertEquals( 2,unidad.suministro());
		Assert.assertEquals( 4,unidad.tiempoDeConstruccion());
		Assert.assertEquals( 50,unidad.costoMineral());
		Assert.assertEquals( 0,unidad.costoGas());
	}
	@Test
	public void ataque(){
		UnidadDeAtaque unidad=(UnidadDeAtaque) new Zealot(Color.AMARILLO);
		Unidad atacado = new Marine(Color.ROJO);		
		for ( int i = 0 ; i< 10 ; i++ )
		unidad.atacarTierra(atacado);
		Assert.assertTrue(atacado.estaMuerto());
	}
	@Test
	public void recibirdanio(){
		Unidad unidad=(Unidad) new Zealot(Color.AMARILLO);
		unidad.recibirDanio(10);
		Assert.assertFalse(unidad.estaMuerto());
		unidad.recibirDanio(1000);
		Assert.assertTrue(unidad.estaMuerto());
	}
	@Test
	public void transportable() {
		Transportable transportable = new Zealot(Color.AZUL);
		Assert.assertEquals(2, transportable.transporte());
	}
	@Test
	public void pasarTurno() {
		Ser ser = new Dragon(Color.AZUL);
		int tiempoDeConstruccion=ser.tiempoDeConstruccion();
		for(;tiempoDeConstruccion>1;tiempoDeConstruccion--){
			ser.pasarTurno();
			Assert.assertEquals(tiempoDeConstruccion-1,ser.tiempoDeConstruccion());
			Assert.assertFalse(ser.creado());
		}
		ser.pasarTurno();
		tiempoDeConstruccion=ser.tiempoDeConstruccion();
		Assert.assertTrue(ser.creado());
	}
	

}
