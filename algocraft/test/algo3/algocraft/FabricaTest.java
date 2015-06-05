package algo3.algocraft;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

import algo3.algocraft.edificios.Barraca;
import algo3.algocraft.edificios.DepositoDeSuminisitros;
import algo3.algocraft.edificios.Fabrica;
import algo3.algocraft.unidades.Golliat;
import algo3.algocraft.unidades.Marine;

public class FabricaTest {


	@Test
	public void setTest() {
		Ser edificio=(Ser) new Fabrica(Color.ROJO);
		Assert.assertTrue(Color.ROJO==edificio.color());
		Assert.assertEquals( 12,edificio.tiempoDeConstruccion());
		Assert.assertEquals( 200,edificio.costoMineral());
		Assert.assertEquals( 100,edificio.costoGas());
	}
	@Test
	public void recibirdanio(){
		Ser edificio=(Ser) new Fabrica(Color.ROJO);
		edificio.recibirDanio(10);
		Assert.assertFalse(edificio.estaMuerto());
		edificio.recibirDanio(1300);
		Assert.assertTrue(edificio.estaMuerto());
	}
	@Test 
	public void crearGoliat(){
		Fabrica fabrica=(Fabrica) new Fabrica(Color.ROJO);
		fabrica.crearGolliat();
		for(int i=0 ; i<6; i++){
			Assert.assertTrue(fabrica.unidadesCreadas().isEmpty());
			fabrica.pasarTurno();
		}
		Assert.assertFalse(fabrica.unidadesCreadas().isEmpty());
		Golliat golliat = (Golliat) fabrica.unidadesCreadas().remove(0);
		Assert.assertTrue(fabrica.unidadesCreadas().isEmpty());
		Assert.assertTrue(golliat.color()==Color.ROJO);
	}

}
