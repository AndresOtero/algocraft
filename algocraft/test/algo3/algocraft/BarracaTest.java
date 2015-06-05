package algo3.algocraft;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

import algo3.algocraft.edificios.ArchivosTemplarios;
import algo3.algocraft.edificios.Barraca;
import algo3.algocraft.unidades.AltoTemplario;
import algo3.algocraft.unidades.Marine;

public class BarracaTest {

	@Test
	public void setTest() {
		Ser edificio=(Ser) new Barraca(Color.ROJO);
		Assert.assertTrue(Color.ROJO==edificio.color());
		Assert.assertEquals( 12,edificio.tiempoDeConstruccion());
		Assert.assertEquals( 150,edificio.costoMineral());
		Assert.assertEquals( 0,edificio.costoGas());
	}
	@Test
	public void recibirdanio(){
		Ser edificio=(Ser) new Barraca(Color.AMARILLO);
		edificio.recibirDanio(10);
		Assert.assertFalse(edificio.estaMuerto());
		edificio.recibirDanio(1000);
		Assert.assertTrue(edificio.estaMuerto());
	}
	@Test 
	public void crearMarine(){
		Barraca barraca=(Barraca) new Barraca(Color.AMARILLO);
		barraca.crearMarine();
	
		for(int i=4;i>1;i--){
			Assert.assertTrue(barraca.unidadesCreadas().isEmpty());
			barraca.pasarTurno();
		}
		Assert.assertFalse(barraca.unidadesCreadas().isEmpty());
		Marine marine = (Marine) barraca.unidadesCreadas().remove(0);
		Assert.assertTrue(barraca.unidadesCreadas().isEmpty());
		Assert.assertTrue(marine.color()==Color.AMARILLO);
	}

}
