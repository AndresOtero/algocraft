package algo3.algocraft;

import junit.framework.Assert;

import org.junit.Test;

import algo3.algocraft.edificios.*;

public class EdificiosTest {

	@Test
	public void edificioAtacado() {
		Mineral min = new Mineral();
		Edificio ed = new CentroDeMineral(min);
		int vidaRestante = ed.atacado(450);
		Assert.assertTrue(vidaRestante == 50);

	}

	public void edificioAtacadoYdestruido() {
		Mineral min = new Mineral();
		Edificio ed = new CentroDeMineral(min);
		int vidaRestante = ed.atacado(500);
		Assert.assertTrue(vidaRestante == 0);

	}

	public void edificioConstruccionCompleta() {
		Edificio ed = new Acceso();
		int i = 0;
		while (i < 100) { /* 100 por poner un numero > 8 */
			ed.nuevoTurno();
			i++;
		}
		Assert.assertTrue(ed.disponible() == true);

	}

	public void edificioProtossAtaqueYVidaRestante() {
		Mineral min = new Mineral();
		EdificioProtoss ed = new NexoMineral(min);
		Assert.assertTrue(ed.atacado(450) == 50);

	}

	public void edificioProtossAtaqueYRegeneracionDeEscudo() {
		Mineral min = new Mineral();
		EdificioProtoss ed = new NexoMineral(min);
		ed.atacado(450);
		Assert.assertTrue(ed.valorEscudo() == 250);

	}

	public void edificioProtossAtaqueYDestruccion() {
		Mineral min = new Mineral();
		EdificioProtoss ed = new NexoMineral(min);
		int vida = ed.atacado(700);
		Assert.assertTrue(vida == 0);

	}

}
