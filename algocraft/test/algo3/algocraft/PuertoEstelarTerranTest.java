package algo3.algocraft;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

import algo3.algocraft.edificios.PuertoEstelarProtoss;
import algo3.algocraft.edificios.PuertoEstelarTerran;
import algo3.algocraft.unidades.Espectro;
import algo3.algocraft.unidades.NaveCiencia;
import algo3.algocraft.unidades.NaveTransporteProtoss;
import algo3.algocraft.unidades.NaveTransporteTerran;

public class PuertoEstelarTerranTest {

	@Test
	public void setTest() {
		Ser edificio=(Ser) new PuertoEstelarTerran(Color.ROJO);
		Assert.assertTrue(Color.ROJO==edificio.color());
		Assert.assertEquals( 10,edificio.tiempoDeConstruccion());
		Assert.assertEquals( 150,edificio.costoMineral());
		Assert.assertEquals( 100,edificio.costoGas());
	}
	@Test
	public void recibirdanio(){
		Ser edificio=(Ser) new PuertoEstelarTerran(Color.ROJO);
		edificio.recibirDanio(10);
		Assert.assertFalse(edificio.estaMuerto());
		edificio.recibirDanio(1300);
		Assert.assertTrue(edificio.estaMuerto());
	}
	@Test 
	public void CrearNaveTransporteTerran(){
		PuertoEstelarTerran puertoEstelar= new PuertoEstelarTerran(Color.ROJO);
		puertoEstelar.CrearNaveTransporteTerran();
		for(int i=7;i>1;i--){
			puertoEstelar.pasarTurno();
			Assert.assertTrue(puertoEstelar.unidadesCreadas().isEmpty());
		}
		puertoEstelar.pasarTurno();
		Assert.assertFalse(puertoEstelar.unidadesCreadas().isEmpty());
		NaveTransporteTerran nave = (NaveTransporteTerran) puertoEstelar.unidadesCreadas().remove(0);
		Assert.assertTrue(puertoEstelar.unidadesCreadas().isEmpty());
		Assert.assertTrue(nave.color()==Color.ROJO);
	}
	@Test 
	public void crearEspectro(){
		PuertoEstelarTerran puertoEstelar= new PuertoEstelarTerran(Color.ROJO);
		puertoEstelar.crearEspectro();
		for(int i=8;i>1;i--){
			puertoEstelar.pasarTurno();
			Assert.assertTrue(puertoEstelar.unidadesCreadas().isEmpty());
		}
		puertoEstelar.pasarTurno();
		Assert.assertFalse(puertoEstelar.unidadesCreadas().isEmpty());
		Espectro espectro = (Espectro) puertoEstelar.unidadesCreadas().remove(0);
		Assert.assertTrue(puertoEstelar.unidadesCreadas().isEmpty());
		Assert.assertTrue(espectro.color()==Color.ROJO);
	}
	@Test 
	public void crearNaveCiencia(){
		PuertoEstelarTerran puertoEstelar= new PuertoEstelarTerran(Color.ROJO);
		puertoEstelar.crearNaveCiencia();
		for(int i=0;i<9;i++){
			puertoEstelar.pasarTurno();
			Assert.assertTrue(puertoEstelar.unidadesCreadas().isEmpty());
		}
		puertoEstelar.pasarTurno();
		Assert.assertFalse(puertoEstelar.unidadesCreadas().isEmpty());
		NaveCiencia nave = (NaveCiencia) puertoEstelar.unidadesCreadas().remove(0);
		Assert.assertTrue(puertoEstelar.unidadesCreadas().isEmpty());
		Assert.assertTrue(nave.color()==Color.ROJO);
	}


}
