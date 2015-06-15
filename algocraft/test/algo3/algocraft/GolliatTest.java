package algo3.algocraft;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

import algo3.algocraft.unidades.*;

public class GolliatTest {

	@Test
	public void setTest() {
		Unidad unidad = (Unidad) new Golliat(Color.ROJO,new Posicion(10,5));
		Assert.assertTrue(Color.ROJO == unidad.color());
		Assert.assertEquals(8, unidad.vision());
		Assert.assertEquals(2, unidad.suministro());
		Assert.assertEquals(6, unidad.tiempoDeConstruccion());
	}

	@Test
	public void ataque(){
		UnidadDeAtaque unidad=(UnidadDeAtaque) new Golliat(Color.AMARILLO,new Posicion(10,5));
		Unidad atacado = new Marine(Color.ROJO,new Posicion(10,6));		
		for ( int i = 0 ; i< 10 ; i++ )
		unidad.atacarTierra(atacado);
		Assert.assertTrue(atacado.estaMuerto());
	}
	

	@Test
	public void recibirdanio() {
		Unidad unidad = (Unidad) new Golliat(Color.AMARILLO,new Posicion(10,5));
		unidad.recibirDanio(10);
		Assert.assertFalse(unidad.estaMuerto());
		unidad.recibirDanio(1000);
		Assert.assertTrue(unidad.estaMuerto());
	}

	@Test
	public void transportable() {
		Transportable transportable = new Golliat(Color.AZUL,new Posicion(10,5));
		Assert.assertEquals(2, transportable.transporte());
	}
	@Test
	public void pasarTurno() {
		Ser ser = new Golliat(Color.AZUL,new Posicion(10,5));
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