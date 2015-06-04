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
		Ser edificio=(Ser) new PuertoEstelarProtoss(Color.ROJO);
		Assert.assertTrue(Color.ROJO==edificio.color());
		Assert.assertEquals( 10,edificio.tiempoDeConstruccion());
		Assert.assertEquals( 150,edificio.costoMineral());
		Assert.assertEquals( 150,edificio.costoGas());
	}
	@Test
	public void recibirdanio(){
		Ser edificio=(Ser) new PuertoEstelarProtoss(Color.ROJO);
		edificio.recibirDanio(100);
		Assert.assertFalse(edificio.estaMuerto());
		edificio.recibirDanio(1200);
		Assert.assertTrue(edificio.estaMuerto());
	}
	@Test 
	public void crearNaveTransporteProtoss(){
		PuertoEstelarProtoss puertoEstelar= new PuertoEstelarProtoss(Color.ROJO);
		puertoEstelar.crearNaveTransporteProtoss();
		for(int i=4;i>1;i--){
			puertoEstelar.pasarTurno();
			Assert.assertTrue(puertoEstelar.unidadesEnCola().isEmpty());
		}
		puertoEstelar.pasarTurno();
		Assert.assertFalse(puertoEstelar.unidadesEnCola().isEmpty());
		NaveTransporteProtoss nave = (NaveTransporteProtoss) puertoEstelar.unidadesEnCola().remove(0);
		Assert.assertTrue(puertoEstelar.unidadesEnCola().isEmpty());
		Assert.assertTrue(nave.color()==Color.AMARILLO);
	}

}
