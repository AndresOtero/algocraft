package algo3.algocraft;

import junit.framework.Assert;

import org.junit.Test;

import algo3.algocraft.edificios.*;

public class EdificiosTest {

	@Test
	public void edificioAtacado() {
		Mineral min = new Mineral(null);
		CentroDeMineral ed = new CentroDeMineral(min, null);
		ed.recibirDanio(450);
		Assert.assertTrue(ed.vida() == 50);

	}

	@Test
	public void edificioAtacadoYdestruido() {
		Mineral min = new Mineral(null);
		Edificio ed = new CentroDeMineral(min);
		ed.recibirDanio(500);
		Assert.assertTrue(ed.vida() == 0);

	}

	@Test
	public void edificioProtossAtaqueYVidaRestante() {
		Mineral min = new Mineral();
		Edificio ed = new NexoMineral(min);
		ed.recibirDanio(450);
		Assert.assertTrue(ed.vida() == 50);

	}

	@Test
	public void edificioProtossAtaqueYRegeneracionDeEscudo() {
		Mineral min = new Mineral();
		Edificio ed = new NexoMineral(min);
		ed.recibirDanio(400);
		Assert.assertTrue(ed.estaMuerto() == false);

	}

	@Test
	public void edificioProtossAtaqueYDestruccion() {
		Mineral min = new Mineral();
		Edificio ed = new NexoMineral(min);
		ed.recibirDanio(1000);
		Assert.assertTrue(ed.estaMuerto() == true);

	}

}