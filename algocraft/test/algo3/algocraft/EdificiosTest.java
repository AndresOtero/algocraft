package algo3.algocraft;

import junit.framework.Assert;

import org.junit.Test;

import algo3.algocraft.edificios.*;

public class EdificiosTest {

	@Test
	public void edificioAtacado() {
		Mineral min = new Mineral();
		CentroDeMineral ed = new CentroDeMineral(min, null, null);
		ed.recibirDanio(450);
		Assert.assertTrue(ed.vida() == 50);

	}

	@Test
	public void edificioAtacadoYdestruido() {
		Mineral min = new Mineral();
		Edificio ed = new CentroDeMineral(min, Color.ROJO, null);
		ed.recibirDanio(500);
		Assert.assertTrue(ed.vida() == 0);

	}

	@Test
	public void edificioProtossAtaqueYVidaRestante() {
		Mineral min = new Mineral();
		Edificio ed = new NexoMineral(min, Color.ROJO, null);
		ed.recibirDanio(450);
		Assert.assertTrue(ed.vida() == 50);

	}

	@Test
	public void edificioProtossAtaqueYRegeneracionDeEscudo() {
		Mineral min = new Mineral();
		Edificio ed = new NexoMineral(min, Color.ROJO, null);
		ed.recibirDanio(400);
		Assert.assertTrue(ed.estaMuerto() == false);

	}

	@Test
	public void edificioProtossAtaqueYDestruccion() {
		Mineral min = new Mineral();
		Edificio ed = new NexoMineral(min,Color.ROJO, null);
		ed.recibirDanio(1000);
		Assert.assertTrue(ed.estaMuerto() == true);

	}

}