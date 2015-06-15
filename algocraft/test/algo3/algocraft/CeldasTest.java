package algo3.algocraft;

import static org.junit.Assert.*;
import junit.framework.Assert;

import org.junit.Test;

import algo3.algocraft.unidades.Marine;
import algo3.algocraft.unidades.Scout;
import algo3.algocraft.unidades.Unidad;

public class CeldasTest {

	@Test
	public void test() {
		Celda celda = new Celda();
		Unidad serTerrestre=(Unidad) new Marine(Color.ROJO,new Posicion(10,5));
		Unidad serAereo=(Unidad) new Scout(Color.ROJO,new Posicion(10,5));
		Assert.assertTrue(celda.agregarSerAereo(serAereo));
		Assert.assertTrue(celda.agregarSerTerrestre(serTerrestre));
		Assert.assertTrue(celda.ocupadoAerea());
		Assert.assertTrue(celda.ocupadoTerrestre());
		
		Assert.assertFalse(celda.esVisible());
		celda.cambiarVisibilidad(true);
		Assert.assertTrue(celda.esVisible());
		
		Assert.assertTrue(celda.serEnLaCeldaAerea() == serAereo);
		Assert.assertTrue(celda.serEnLaCeldaTerrestre() == serTerrestre);
		
		celda.desocuparAerea();
		celda.desocuparTerrestre();
		
		Assert.assertTrue(celda.serEnLaCeldaAerea() == null);
		Assert.assertTrue(celda.serEnLaCeldaTerrestre() == null);
		
	}

}
