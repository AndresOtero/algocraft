package algo3.algocraft;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

import algo3.algocraft.edificios.Acceso;
import algo3.algocraft.edificios.ArchivosTemplarios;
import algo3.algocraft.unidades.AltoTemplario;
import algo3.algocraft.unidades.Dragon;
import algo3.algocraft.unidades.Zealot;

public class ArchivosTemplariosTest {

	@Test
	public void setTest() {
		Ser edificio=(Ser) new ArchivosTemplarios(Color.ROJO);
		Assert.assertTrue(Color.ROJO==edificio.color());
		Assert.assertEquals( 6,edificio.tiempoDeConstruccion());
		Assert.assertEquals( 125,edificio.costoMineral());
		Assert.assertEquals( 50,edificio.costoGas());
	}
	@Test
	public void recibirdanio(){
		Ser edificio=(Ser) new ArchivosTemplarios(Color.AMARILLO);
		edificio.recibirDanio(10);
		Assert.assertFalse(edificio.estaMuerto());
		edificio.recibirDanio(1000);
		Assert.assertTrue(edificio.estaMuerto());
	}
	@Test 
	public void crearTemplario(){
		ArchivosTemplarios archivos=(ArchivosTemplarios) new ArchivosTemplarios(Color.AMARILLO);
		archivos.crearAltoTemplario();
		for(int i=4;i>1;i--){
			archivos.pasarTurno();
			Assert.assertTrue(archivos.unidadesEnCola().isEmpty());
		}
		archivos.pasarTurno();
		Assert.assertFalse(archivos.unidadesEnCola().isEmpty());
		AltoTemplario templario = (AltoTemplario) archivos.unidadesEnCola().remove(0);
		Assert.assertTrue(archivos.unidadesEnCola().isEmpty());
		Assert.assertTrue(templario.color()==Color.AMARILLO);
	}
	


}
