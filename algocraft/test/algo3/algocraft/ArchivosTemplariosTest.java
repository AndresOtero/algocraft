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
		Ser edificio=(Ser) new ArchivosTemplarios(Color.ROJO, null);
		Assert.assertTrue(Color.ROJO==edificio.color());
		Assert.assertEquals( 9,edificio.tiempoDeConstruccion());
	}
	@Test
	public void recibirdanio(){
		Ser edificio=(Ser) new ArchivosTemplarios(Color.AMARILLO, null);
		edificio.recibirDanio(10);
		Assert.assertFalse(edificio.estaMuerto());
		edificio.recibirDanio(1000);
		Assert.assertTrue(edificio.estaMuerto());
	}
	@Test 
	public void crearTemplario(){
		Jugador j1 = new Jugador("pedrito", Color.AMARILLO, TipoRaza.PROTOSS);
		j1.agregarGasVespeno(1000);
		j1.agregarMineral(1000);
		ArchivosTemplarios archivos=(ArchivosTemplarios) new ArchivosTemplarios(Color.AMARILLO, null);
		archivos.crearAltoTemplario(j1);
		for(int i=7;i>1;i--){
			archivos.pasarTurno();
			Assert.assertTrue(archivos.unidadesCreadas().isEmpty());
		}
		archivos.pasarTurno();
		Assert.assertFalse(archivos.unidadesCreadas().isEmpty());
		AltoTemplario templario = (AltoTemplario) archivos.unidadesCreadas().remove(0);
		Assert.assertTrue(archivos.unidadesCreadas().isEmpty());
		Assert.assertTrue(templario.color()==Color.AMARILLO);
	}
	


}
