package algo3.algocraft;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.junit.Assert;
import org.junit.Test;

import algo3.algocraft.edificios.*;
import algo3.algocraft.unidades.AltoTemplario;

public class FactoryEdificiosProtossTest {

	@Test
	public void crearNexoTest() {
		Jugador jugador = new Jugador("Andys", Color.ROJO, null);
		Jugador jugador2 = new Jugador("gasti", Color.ROJO, null);
		ArrayList<Jugador> jugadores = new ArrayList<Jugador>();
		jugador.agregarGasVespeno(1000);
		jugador.agregarMineral(1000);
		Mapa mapa = Mapa.getInstance(15, 15, jugadores);
		AbstractFactoryEdificios factory =new FactoryEdificiosProtoss(jugador,mapa);
		Posicion pos=new Posicion(1,1);
		Mineral mineral = new Mineral();
		factory.fabricarRecolectableMinerales(mineral,pos);
		for(int i=4;i>1;i--){
			(factory.pasarTurno());
		}
		HashMap<Edificio, Posicion> map=factory.pasarTurno();
		Assert.assertFalse(map.isEmpty());
		Iterator<Edificio> iter =map.keySet().iterator();
		NexoMineral nexo = (NexoMineral) iter.next();
		Assert.assertSame(pos, map.get(nexo));
		map.remove(nexo);
		Assert.assertTrue(map.isEmpty());
		Assert.assertTrue(nexo.color()==Color.ROJO);
	}
	@Test
	public void crearAsimilidorTest() {
		Jugador jugador = new Jugador("Andys", Color.ROJO, null);
		jugador.agregarGasVespeno(1000);
		jugador.agregarMineral(1000);
		AbstractFactoryEdificios factory =new FactoryEdificiosProtoss(jugador);
		Posicion pos=new Posicion(1,1);		
		VolcanGasVespeno gas = new VolcanGasVespeno();
		factory.fabricarRecolectableGas(gas, pos);
		for(int i=6;i>1;i--){
			Assert.assertTrue(factory.pasarTurno().isEmpty());
		}
		HashMap<Edificio, Posicion> map=factory.pasarTurno();
		Assert.assertFalse(map.isEmpty());
		Iterator<Edificio> iter =map.keySet().iterator();
		Asimilador asimilador = (Asimilador) iter.next();
		Assert.assertSame(pos, map.get(asimilador));
		map.remove(asimilador);
		Assert.assertTrue(map.isEmpty());
		Assert.assertTrue(asimilador.color()==Color.ROJO);
	}
	@Test
	public void crearDepositoTest() {
		Jugador jugador = new Jugador("Andys", Color.ROJO, null);
		jugador.agregarGasVespeno(1000);
		jugador.agregarMineral(1000);
		Posicion pos=new Posicion(1,1);		
		AbstractFactoryEdificios factory =new FactoryEdificiosProtoss(jugador);
		factory.fabricarSumaPoblacion(pos);
		for(int i=5;i>1;i--){
			Assert.assertTrue(factory.pasarTurno().isEmpty());
		}
		HashMap<Edificio, Posicion> map=factory.pasarTurno();
		Assert.assertFalse(map.isEmpty());
		Iterator<Edificio> iter =map.keySet().iterator();
		Pilon pilon = (Pilon) iter.next();
		Assert.assertSame(pos, map.get(pilon));
		map.remove(pilon);
		Assert.assertTrue(map.isEmpty());
		Assert.assertTrue(pilon.color()==Color.ROJO);
	}
	@Test
	public void crearAccesoTest() {
		Jugador jugador = new Jugador("Andys", Color.ROJO, null);
		jugador.agregarGasVespeno(1000);
		jugador.agregarMineral(1000);
		Posicion pos=new Posicion(1,1);		
		AbstractFactoryEdificios factory =new FactoryEdificiosProtoss(jugador);
		factory.fabricarCreadorSoldados(pos);
		for(int i=8;i>1;i--){
			Assert.assertTrue(factory.pasarTurno().isEmpty());
		}
		HashMap<Edificio, Posicion> map=factory.pasarTurno();
		Assert.assertFalse(map.isEmpty());
		Iterator<Edificio> iter =map.keySet().iterator();
		Acceso acceso = (Acceso) iter.next();
		Assert.assertSame(pos, map.get(acceso));
		map.remove(acceso);
		Assert.assertTrue(map.isEmpty());
		Assert.assertTrue(acceso.color()==Color.ROJO);
	}
	@Test
	public void crearPueroEstelarProtossTest() {
		Jugador jugador = new Jugador("Andys", Color.ROJO, null);
		jugador.agregarGasVespeno(1000);
		jugador.agregarMineral(1000);
		Posicion pos=new Posicion(1,1);		
		AbstractFactoryEdificios factory =new FactoryEdificiosProtoss(jugador);
		factory.fabricarCreadorAereos(pos);
		for(int i=10;i>1;i--){
			Assert.assertTrue(factory.pasarTurno().isEmpty());
		}
		HashMap<Edificio, Posicion> map=factory.pasarTurno();
		Assert.assertFalse(map.isEmpty());
		Iterator<Edificio> iter =map.keySet().iterator();
		PuertoEstelarProtoss puerto = (PuertoEstelarProtoss) iter.next();
		Assert.assertSame(pos, map.get(puerto));
		map.remove(puerto);
		Assert.assertTrue(map.isEmpty());
		Assert.assertTrue(puerto.color()==Color.ROJO);
	}
	@Test
	public void crearArchivosTest() {
		Jugador jugador = new Jugador("Andys", Color.ROJO, null);
		jugador.agregarGasVespeno(1000);
		jugador.agregarMineral(1000);
		Posicion pos=new Posicion(1,1);		
		AbstractFactoryEdificios factory =new FactoryEdificiosProtoss(jugador);
		factory.fabricarCreadorTerrestres(pos);
		for(int i=9;i>1;i--){
			Assert.assertTrue(factory.pasarTurno().isEmpty());
		}
		HashMap<Edificio, Posicion> map=factory.pasarTurno();
		Assert.assertFalse(map.isEmpty());
		Iterator<Edificio> iter =map.keySet().iterator();
		ArchivosTemplarios archivo = (ArchivosTemplarios) iter.next();
		Assert.assertSame(pos, map.get(archivo));
		map.remove(archivo);
		Assert.assertTrue(map.isEmpty());
		Assert.assertTrue(archivo.color()==Color.ROJO);
	}
	
	
	

}
