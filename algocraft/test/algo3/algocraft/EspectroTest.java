package algo3.algocraft;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

import algo3.algocraft.unidades.*;

public class EspectroTest {

	@Test
	public void setTest() {
		Unidad unidad=(Unidad) new Espectro(Color.ROJO);
		Assert.assertTrue(Color.ROJO==unidad.color());
		Assert.assertEquals( 7,unidad.vision());
		Assert.assertEquals( 2,unidad.suministro());
		Assert.assertEquals( 8,unidad.tiempoDeConstruccion());
		Assert.assertEquals( 150,unidad.costoMineral());
		Assert.assertEquals( 100,unidad.costoGas());
	}
	@Test
	public void ataque(){
		UnidadDeAtaque unidad=(UnidadDeAtaque) new Espectro(Color.AMARILLO);
		Unidad atacado = new Marine(Color.ROJO);		
		for ( int i = 0 ; i< 10 ; i++ )
		unidad.atacarTierra(atacado);
		Assert.assertTrue(atacado.estaMuerto());
	}
	@Test
	public void recibirdanio(){
		Unidad unidad=(Unidad) new Espectro(Color.AMARILLO);
		unidad.recibirDanio(10);
		Assert.assertFalse(unidad.estaMuerto());
		unidad.recibirDanio(1000);
		Assert.assertTrue(unidad.estaMuerto());
	}
	@Test
	public void pasarTurno() {
		Ser ser = new Espectro(Color.AZUL);
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