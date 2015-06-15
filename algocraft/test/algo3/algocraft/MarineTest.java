package algo3.algocraft;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

import algo3.algocraft.unidades.*;



public class MarineTest {

	@Test
	public void setTest() {
		Unidad unidad=(Unidad) new Marine(Color.ROJO,new Posicion(10,9));
		Assert.assertTrue(Color.ROJO==unidad.color());
		Assert.assertEquals( 7,unidad.vision());
		Assert.assertEquals( 1,unidad.suministro());
		Assert.assertEquals( 3,unidad.tiempoDeConstruccion());
	}
	@Test
	public void ataque(){
		UnidadDeAtaque unidad=(UnidadDeAtaque) new Marine(Color.AMARILLO,new Posicion(10,9));
		Unidad atacado = new Marine(Color.ROJO,new Posicion(10,10));		
		for ( int i = 0 ; i< 10 ; i++ )
		unidad.atacarTierra(atacado);
		Assert.assertTrue(atacado.estaMuerto());
	}
	@Test
	public void recibirdanio(){
		Unidad unidad=(Unidad) new Marine(Color.AMARILLO,new Posicion(10,9));
		unidad.recibirDanio(10);
		Assert.assertFalse(unidad.estaMuerto());
		unidad.recibirDanio(1000);
		Assert.assertTrue(unidad.estaMuerto());
	}
	@Test
	public void transportable() {
		Transportable transportable = new Marine(Color.AZUL,new Posicion(10,9));
		Assert.assertEquals(1, transportable.transporte());
	}
	@Test
	public void pasarTurno() {
		Ser ser = new Marine(Color.AZUL,new Posicion(10,9));
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

