package algo3.algocraft;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

import algo3.algocraft.edificios.*;
import algo3.algocraft.unidades.AltoTemplario;

public class FactoryEdificiosProtossTest {

	@Test
	public void crearNexoTest() {
		AbstractFactoryEdificios factory =new FactoryEdificiosProtoss(Color.ROJO);
		Mineral mineral = new Mineral(new Posicion(0,0));
		NexoMineral nexo=(NexoMineral) factory.fabricarRecolectableMinerales(mineral);
		Assert.assertTrue(nexo.color()==Color.ROJO);
	}
	@Test
	public void crearAsimilidorTest() {
		AbstractFactoryEdificios factory =new FactoryEdificiosProtoss(Color.ROJO);
		VolcanGasVespeno gas = new VolcanGasVespeno(new Posicion(0,0));
		Asimilador asimilador=(Asimilador) factory.fabricarRecolectableGas(gas);
		Assert.assertTrue(asimilador.color()==Color.ROJO);
	}
	@Test
	public void crearDepositoTest() {
		AbstractFactoryEdificios factory =new FactoryEdificiosProtoss(Color.ROJO);
		Pilon pilon=(Pilon) factory.fabricarSumaPoblacion();
		Assert.assertTrue(pilon.color()==Color.ROJO);
	}
	@Test
	public void crearAccesoTest() {
		AbstractFactoryEdificios factory =new FactoryEdificiosProtoss(Color.ROJO);
		Acceso acceso=(Acceso) factory.fabricarCreadorSoldados();
		Assert.assertTrue(acceso.color()==Color.ROJO);
	}
	@Test
	public void crearPueroEstelarProtossTest() {
		AbstractFactoryEdificios factory =new FactoryEdificiosProtoss(Color.ROJO);
		PuertoEstelarProtoss puerto=(PuertoEstelarProtoss) factory.fabricarCreadorAereos();
		Assert.assertTrue(puerto.color()==Color.ROJO);
	}
	@Test
	public void crearArchivosTest() {
		AbstractFactoryEdificios factory =new FactoryEdificiosProtoss(Color.ROJO);
		ArchivosTemplarios archivo=(ArchivosTemplarios) factory.fabricarCreadorTerrestres();
		Assert.assertTrue(archivo.color()==Color.ROJO);
	}
	
	
	

}
