package algo3.algocraft;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;

import algo3.algocraft.edificios.*;
import algo3.algocraft.unidades.AltoTemplario;

public class FactoryEdificiosProtossTest {

	@Test
	public void crearNexoTest() {
		AbstractFactoryEdificios factory =new FactoryEdificiosProtoss(Color.ROJO);
		Mineral mineral = new Mineral();
		Jugador jugador = new Jugador("Andys", Color.ROJO, null);
		jugador.agregarGasVespeno(1000);
		jugador.agregarMineral(1000);
		factory.fabricarRecolectableMinerales(mineral, jugador);
		for(int i=4;i>1;i--){
			Assert.assertTrue(factory.pasarTurno().isEmpty());
		}
		ArrayList<Edificio> lista=factory.pasarTurno();
		Assert.assertFalse(lista.isEmpty());
		NexoMineral nexo = (NexoMineral) lista.remove(0);
		Assert.assertTrue(lista.isEmpty());
		Assert.assertTrue(nexo.color()==Color.ROJO);
	}
	@Test
	public void crearAsimilidorTest() {
		AbstractFactoryEdificios factory =new FactoryEdificiosProtoss(Color.ROJO);
		VolcanGasVespeno gas = new VolcanGasVespeno();
		Jugador jugador = new Jugador("Andys", Color.ROJO, null);
		jugador.agregarGasVespeno(1000);
		jugador.agregarMineral(1000);
		factory.fabricarRecolectableGas(gas, jugador);
		for(int i=6;i>1;i--){
			Assert.assertTrue(factory.pasarTurno().isEmpty());
		}
		ArrayList<Edificio> lista=factory.pasarTurno();
		Assert.assertFalse(lista.isEmpty());
		Asimilador asimilador = (Asimilador) lista.remove(0);
		Assert.assertTrue(lista.isEmpty());
		Assert.assertTrue(asimilador.color()==Color.ROJO);
	}
	@Test
	public void crearDepositoTest() {
		AbstractFactoryEdificios factory =new FactoryEdificiosProtoss(Color.ROJO);
		Jugador jugador = new Jugador("Andys", Color.ROJO, null);
		jugador.agregarGasVespeno(1000);
		jugador.agregarMineral(1000);
		factory.fabricarSumaPoblacion(jugador);
		for(int i=5;i>1;i--){
			Assert.assertTrue(factory.pasarTurno().isEmpty());
		}
		ArrayList<Edificio> lista=factory.pasarTurno();
		Assert.assertFalse(lista.isEmpty());
		Pilon pilon = (Pilon) lista.remove(0);
		Assert.assertTrue(lista.isEmpty());
		Assert.assertTrue(pilon.color()==Color.ROJO);
	}
	@Test
	public void crearAccesoTest() {
		AbstractFactoryEdificios factory =new FactoryEdificiosProtoss(Color.ROJO);
		Jugador jugador = new Jugador("Andys", Color.ROJO, null);
		jugador.agregarGasVespeno(1000);
		jugador.agregarMineral(1000);
		factory.fabricarCreadorSoldados(jugador);
		for(int i=8;i>1;i--){
			Assert.assertTrue(factory.pasarTurno().isEmpty());
		}
		ArrayList<Edificio> lista=factory.pasarTurno();
		Assert.assertFalse(lista.isEmpty());
		Acceso acceso = (Acceso) lista.remove(0);
		Assert.assertTrue(lista.isEmpty());
		Assert.assertTrue(acceso.color()==Color.ROJO);
	}
	@Test
	public void crearPueroEstelarProtossTest() {
		AbstractFactoryEdificios factory =new FactoryEdificiosProtoss(Color.ROJO);
		Jugador jugador = new Jugador("Andys", Color.ROJO, null);
		jugador.agregarGasVespeno(1000);
		jugador.agregarMineral(1000);
		factory.fabricarCreadorAereos(jugador);
		for(int i=10;i>1;i--){
			Assert.assertTrue(factory.pasarTurno().isEmpty());
		}
		ArrayList<Edificio> lista=factory.pasarTurno();
		Assert.assertFalse(lista.isEmpty());
		PuertoEstelarProtoss puerto = (PuertoEstelarProtoss) lista.remove(0);
		Assert.assertTrue(lista.isEmpty());
		Assert.assertTrue(puerto.color()==Color.ROJO);
	}
	@Test
	public void crearArchivosTest() {
		AbstractFactoryEdificios factory =new FactoryEdificiosProtoss(Color.ROJO);
		Jugador jugador = new Jugador("Andys", Color.ROJO, null);
		jugador.agregarGasVespeno(1000);
		jugador.agregarMineral(1000);
		factory.fabricarCreadorTerrestres(jugador);
		for(int i=9;i>1;i--){
			Assert.assertTrue(factory.pasarTurno().isEmpty());
		}
		ArrayList<Edificio> lista=factory.pasarTurno();
		Assert.assertFalse(lista.isEmpty());
		ArchivosTemplarios archivo = (ArchivosTemplarios) lista.remove(0);
		Assert.assertTrue(lista.isEmpty());
		Assert.assertTrue(archivo.color()==Color.ROJO);
	}
	
	
	

}
