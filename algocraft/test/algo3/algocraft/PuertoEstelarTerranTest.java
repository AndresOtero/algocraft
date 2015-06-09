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
		Jugador j1 = new Jugador("pedrito", Color.AMARILLO, TipoRaza.PROTOSS);
		j1.agregarGasVespeno(1000);
		j1.agregarMineral(1000);
		PuertoEstelarTerran puertoEstelar= new PuertoEstelarTerran(Color.ROJO);
		puertoEstelar.crearNaveTransporteTerran(j1);
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
		Jugador j1 = new Jugador("pedrito", Color.AMARILLO, TipoRaza.PROTOSS);
		j1.agregarGasVespeno(1000);
		j1.agregarMineral(1000);
		PuertoEstelarTerran puertoEstelar= new PuertoEstelarTerran(Color.ROJO);
		puertoEstelar.crearEspectro(j1);
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
		Jugador j1 = new Jugador("pedrito", Color.AMARILLO, TipoRaza.PROTOSS);
		j1.agregarGasVespeno(1000);
		j1.agregarMineral(1000);
		PuertoEstelarTerran puertoEstelar= new PuertoEstelarTerran(Color.ROJO);
		puertoEstelar.crearNaveCiencia(j1);
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
