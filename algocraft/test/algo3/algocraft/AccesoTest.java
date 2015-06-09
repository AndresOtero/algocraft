package algo3.algocraft;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;

import algo3.algocraft.edificios.*;
import algo3.algocraft.unidades.Dragon;
import algo3.algocraft.unidades.Unidad;
import algo3.algocraft.unidades.Zealot;

public class AccesoTest {
	@Test
	public void setTest() {
		Ser unidad=(Ser) new Acceso(Color.ROJO);
		Assert.assertTrue(Color.ROJO==unidad.color());
		Assert.assertEquals( 8,unidad.tiempoDeConstruccion());
	}
	@Test
	public void recibirdanio(){
		Ser unidad=(Ser) new Acceso(Color.AMARILLO);
		unidad.recibirDanio(10);
		Assert.assertFalse(unidad.estaMuerto());
		unidad.recibirDanio(1000);
		Assert.assertTrue(unidad.estaMuerto());
	}
	@Test 
	public void crearDragon(){
		Jugador j1 = new Jugador("pedrito", Color.AMARILLO, TipoRaza.PROTOSS);
		j1.agregarGasVespeno(1000);
		j1.agregarMineral(1000);
		Acceso acceso=(Acceso) new Acceso(Color.AMARILLO);
		acceso.crearDragon(j1);
		for(int i=6;i>1;i--){
			acceso.pasarTurno();
			Assert.assertTrue(acceso.unidadesCreadas().isEmpty());
		}
		acceso.pasarTurno();
		Assert.assertFalse(acceso.unidadesCreadas().isEmpty());
		Dragon dragon = (Dragon) acceso.unidadesCreadas().remove(0);
		Assert.assertTrue(acceso.unidadesCreadas().isEmpty());
		Assert.assertTrue(dragon.color()==Color.AMARILLO);
	}
	@Test 
	public void crearZealot(){
		Jugador j1 = new Jugador("pedrito", Color.AMARILLO, TipoRaza.PROTOSS);
		j1.agregarGasVespeno(1000);
		j1.agregarMineral(1000);
		Acceso acceso=(Acceso) new Acceso(Color.AMARILLO);
		acceso.crearZealot(j1);
		for(int i=4;i>1;i--){
			acceso.pasarTurno();
			Assert.assertTrue(acceso.unidadesCreadas().isEmpty());
		}
		acceso.pasarTurno();
		Assert.assertFalse(acceso.unidadesCreadas().isEmpty());
		Zealot zealot = (Zealot) acceso.unidadesCreadas().remove(0);
		Assert.assertTrue(acceso.unidadesCreadas().isEmpty());
		Assert.assertTrue(zealot.color()==Color.AMARILLO);
	}


}
