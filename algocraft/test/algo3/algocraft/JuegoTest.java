package algo3.algocraft;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

@SuppressWarnings("unused")
public class JuegoTest {

	@Test
	public void test() {
		Assert.assertEquals(2, 2);
		Juego juego = Juego.getInstance();
		TipoRaza raza = TipoRaza.TERRAN;
		Color color = Color.ROJO;
		juego.crearJugador("alan", color, raza);
		juego.crearJugador("pepe",Color.AMARILLO,TipoRaza.TERRAN);
		Unidad unidad1 = new Unidad();
		Unidad unidad2 = new Unidad();
		unidad1.disparar(unidad2); //codear
		assertEquals(unidad2.vida(),100);//codear, el 100 es para testear nomas
		
		
	}

}
