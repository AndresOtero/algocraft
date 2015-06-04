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
		for(int i=4;i>1;i--){
			puertoEstelar.pasarTurno();
			Assert.assertTrue(puertoEstelar.unidadesEnCola().isEmpty());
		}
		puertoEstelar.pasarTurno();
		Assert.assertFalse(puertoEstelar.unidadesEnCola().isEmpty());
		NaveTransporteTerran nave = (NaveTransporteTerran) puertoEstelar.unidadesEnCola().remove(0);
		Assert.assertTrue(puertoEstelar.unidadesEnCola().isEmpty());
		Assert.assertTrue(nave.color()==Color.AMARILLO);
	}
	@Test 
	public void crearEspectro(){
		PuertoEstelarTerran puertoEstelar= new PuertoEstelarTerran(Color.ROJO);
		puertoEstelar.crearEspectro();
		for(int i=4;i>1;i--){
			puertoEstelar.pasarTurno();
			Assert.assertTrue(puertoEstelar.unidadesEnCola().isEmpty());
		}
		puertoEstelar.pasarTurno();
		Assert.assertFalse(puertoEstelar.unidadesEnCola().isEmpty());
		Espectro espectro = (Espectro) puertoEstelar.unidadesEnCola().remove(0);
		Assert.assertTrue(puertoEstelar.unidadesEnCola().isEmpty());
		Assert.assertTrue(espectro.color()==Color.AMARILLO);
	}
	@Test 
	public void crearNaveCiencia(){
		PuertoEstelarTerran puertoEstelar= new PuertoEstelarTerran(Color.ROJO);
		puertoEstelar.crearNaveCiencia();
		for(int i=4;i>1;i--){
			puertoEstelar.pasarTurno();
			Assert.assertTrue(puertoEstelar.unidadesEnCola().isEmpty());
		}
		puertoEstelar.pasarTurno();
		Assert.assertFalse(puertoEstelar.unidadesEnCola().isEmpty());
		NaveCiencia nave = (NaveCiencia) puertoEstelar.unidadesEnCola().remove(0);
		Assert.assertTrue(puertoEstelar.unidadesEnCola().isEmpty());
		Assert.assertTrue(nave.color()==Color.AMARILLO);
	}


}
