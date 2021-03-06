package algo3.algocraft;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

import algo3.algocraft.edificios.Fabrica;
import algo3.algocraft.edificios.PuertoEstelarProtoss;
import algo3.algocraft.unidades.Golliat;
import algo3.algocraft.unidades.NaveTransporteProtoss;

public class PuertoEstelarProtossTest {

	@Test
	public void setTest() {
		Ser edificio=(Ser) new PuertoEstelarProtoss(Color.ROJO, null);
		Assert.assertTrue(Color.ROJO==edificio.color());
		Assert.assertEquals( 10,edificio.tiempoDeConstruccion());
	}
	@Test
	public void recibirdanio(){
		Ser edificio=(Ser) new PuertoEstelarProtoss(Color.ROJO, null);
		edificio.recibirDanio(100);
		Assert.assertFalse(edificio.estaMuerto());
		edificio.recibirDanio(1200);
		Assert.assertTrue(edificio.estaMuerto());
	}
	@Test 
	public void crearNaveTransporteProtoss(){
		Jugador j1 = new Jugador("pedrito", Color.AMARILLO, TipoRaza.PROTOSS);
		j1.agregarGasVespeno(1000);
		j1.agregarMineral(1000);
		PuertoEstelarProtoss puertoEstelar= new PuertoEstelarProtoss(Color.ROJO, null);
		puertoEstelar.crearNaveTransporteProtoss(j1);
		for(int i=8;i>1;i--){
			puertoEstelar.pasarTurno();
			Assert.assertTrue(puertoEstelar.unidadesCreadas().isEmpty());
		}
		puertoEstelar.pasarTurno();
		Assert.assertFalse(puertoEstelar.unidadesCreadas().isEmpty());
		NaveTransporteProtoss nave = (NaveTransporteProtoss) puertoEstelar.unidadesCreadas().remove(0);
		Assert.assertTrue(puertoEstelar.unidadesCreadas().isEmpty());
		Assert.assertTrue(nave.color()==Color.ROJO);
	}

}
