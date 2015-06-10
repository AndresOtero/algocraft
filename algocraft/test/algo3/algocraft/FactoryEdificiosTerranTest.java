package algo3.algocraft;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.junit.Assert;
import org.junit.Test;

















import algo3.algocraft.edificios.*;

public class FactoryEdificiosTerranTest {

	@Test
	public void crearCentroTest() {
		Jugador jugador = new Jugador("Andys", Color.ROJO, null);
		jugador.agregarGasVespeno(1000);
		jugador.agregarMineral(1000);
		Posicion pos=new Posicion(1,1);		
		AbstractFactoryEdificios factory =new FactoryEdificiosTerran(jugador);
		Mineral mineral = new Mineral();
		factory.fabricarRecolectableMinerales(mineral, pos);
		for(int i=4;i>1;i--){
			Assert.assertTrue(factory.pasarTurno().isEmpty());
		}
		HashMap<Edificio, Posicion> map=factory.pasarTurno();
		Assert.assertFalse(map.isEmpty());
		Iterator<Edificio> iter =map.keySet().iterator();
		CentroDeMineral centro = (CentroDeMineral) iter.next();
		Assert.assertSame(pos, map.get(centro));
		map.remove(centro);
		Assert.assertTrue(map.isEmpty());
		Assert.assertTrue(centro.color()==Color.ROJO);
	}
	@Test
	public void crearRefineriaTest() {
		Jugador jugador = new Jugador("Andys", Color.ROJO, null);
		jugador.agregarGasVespeno(1000);
		jugador.agregarMineral(1000);
		AbstractFactoryEdificios factory =new FactoryEdificiosTerran(jugador);
		Posicion pos=new Posicion(1,1);	
		VolcanGasVespeno gas = new VolcanGasVespeno();
		factory.fabricarRecolectableGas(gas, pos);
		for(int i=6;i>1;i--){
			Assert.assertTrue(factory.pasarTurno().isEmpty());
		}
		HashMap<Edificio, Posicion> map=factory.pasarTurno();
		Assert.assertFalse(map.isEmpty());
		Iterator<Edificio> iter =map.keySet().iterator();
		Refineria refineria = (Refineria) iter.next();
		Assert.assertSame(pos, map.get(refineria));
		map.remove(refineria);
		Assert.assertTrue(map.isEmpty());
	}
	@Test
	public void crearDepositoTest() {
		Jugador jugador = new Jugador("Andys", Color.ROJO, null);
		jugador.agregarGasVespeno(1000);
		jugador.agregarMineral(1000);
		AbstractFactoryEdificios factory =new FactoryEdificiosTerran(jugador);
		Posicion pos=new Posicion(1,1);	
		factory.fabricarSumaPoblacion(pos);
		for(int i=6;i>1;i--){
			Assert.assertTrue(factory.pasarTurno().isEmpty());
		}
		HashMap<Edificio, Posicion> map=factory.pasarTurno();
		Assert.assertFalse(map.isEmpty());
		Iterator<Edificio> iter =map.keySet().iterator();
		DepositoDeSuminisitros deposito = (DepositoDeSuminisitros) iter.next();
		Assert.assertSame(pos, map.get(deposito));
		map.remove(deposito);
		Assert.assertTrue(map.isEmpty());
	}
	@Test
	public void crearBarracaTest() {
		Jugador jugador = new Jugador("Andys", Color.ROJO, null);
		jugador.agregarGasVespeno(1000);
		jugador.agregarMineral(1000);
		AbstractFactoryEdificios factory =new FactoryEdificiosTerran(jugador);
		Posicion pos=new Posicion(1,1);	
		factory.fabricarCreadorSoldados(pos);
		for(int i=12;i>1;i--){
			Assert.assertTrue(factory.pasarTurno().isEmpty());
		}
		HashMap<Edificio, Posicion> map=factory.pasarTurno();
		Assert.assertFalse(map.isEmpty());
		Iterator<Edificio> iter =map.keySet().iterator();
		Barraca barraca = (Barraca) iter.next();
		Assert.assertSame(pos, map.get(barraca));
		map.remove(barraca);
		Assert.assertTrue(map.isEmpty());
	}
	@Test
	public void crearPueroEstelarTerranTest() {
		Jugador jugador = new Jugador("Andys", Color.ROJO, null);
		jugador.agregarGasVespeno(1000);
		jugador.agregarMineral(1000);
		AbstractFactoryEdificios factory =new FactoryEdificiosTerran(jugador);
		Posicion pos=new Posicion(1,1);	
		factory.fabricarCreadorAereos(pos);
		for(int i=10;i>1;i--){
			Assert.assertTrue(factory.pasarTurno().isEmpty());
		}
		HashMap<Edificio, Posicion> map=factory.pasarTurno();
		Assert.assertFalse(map.isEmpty());
		Iterator<Edificio> iter =map.keySet().iterator();
		PuertoEstelarTerran puerto = (PuertoEstelarTerran) iter.next();
		Assert.assertSame(pos, map.get(puerto));
		map.remove(puerto);
		Assert.assertTrue(map.isEmpty());
	}
	@Test
	public void crearFabricaTest() {
		Jugador jugador = new Jugador("Andys", Color.ROJO, null);
		jugador.agregarGasVespeno(1000);
		jugador.agregarMineral(1000);
		AbstractFactoryEdificios factory =new FactoryEdificiosTerran(jugador);
		Posicion pos=new Posicion(1,1);	
		factory.fabricarCreadorTerrestres(pos);
		for(int i=12;i>1;i--){
			Assert.assertTrue(factory.pasarTurno().isEmpty());
		}
		HashMap<Edificio, Posicion> map=factory.pasarTurno();
		Assert.assertFalse(map.isEmpty());
		Iterator<Edificio> iter =map.keySet().iterator();
		Fabrica fabrica = (Fabrica)  iter.next();
		Assert.assertSame(pos, map.get(fabrica));
		map.remove(fabrica);
		Assert.assertTrue(map.isEmpty());
	}

}
