package algo3.algocraft;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;









import algo3.algocraft.edificios.*;

public class FactoryEdificiosTerranTest {

	@Test
	public void crearCentroTest() {
		AbstractFactoryEdificios factory =new FactoryEdificiosTerran(Color.ROJO);
		Mineral mineral = new Mineral();
		factory.fabricarRecolectableMinerales(mineral);
		for(int i=4;i>1;i--){
			Assert.assertTrue(factory.pasarTurno().isEmpty());
		}
		ArrayList<Edificio> lista=factory.pasarTurno();
		Assert.assertFalse(lista.isEmpty());
		CentroDeMineral centro = (CentroDeMineral) lista.remove(0);
		Assert.assertTrue(lista.isEmpty());
		Assert.assertTrue(centro.color()==Color.ROJO);
	}
	@Test
	public void crearRefineriaTest() {
		AbstractFactoryEdificios factory =new FactoryEdificiosTerran(Color.ROJO);
		VolcanGasVespeno gas = new VolcanGasVespeno();
		factory.fabricarRecolectableGas(gas);
		for(int i=6;i>1;i--){
			Assert.assertTrue(factory.pasarTurno().isEmpty());
		}
		ArrayList<Edificio> lista=factory.pasarTurno();
		Assert.assertFalse(lista.isEmpty());
		Refineria refineria = (Refineria) lista.remove(0);
		Assert.assertTrue(lista.isEmpty());
		Assert.assertTrue(refineria.color()==Color.ROJO);
	}
	@Test
	public void crearDepositoTest() {
		AbstractFactoryEdificios factory =new FactoryEdificiosTerran(Color.ROJO);
		factory.fabricarSumaPoblacion();
		for(int i=6;i>1;i--){
			Assert.assertTrue(factory.pasarTurno().isEmpty());
		}
		ArrayList<Edificio> lista=factory.pasarTurno();
		Assert.assertFalse(lista.isEmpty());
		DepositoDeSuminisitros deposito = (DepositoDeSuminisitros) lista.remove(0);
		Assert.assertTrue(lista.isEmpty());
		Assert.assertTrue(deposito.color()==Color.ROJO);
	}
	@Test
	public void crearBarracaTest() {
		AbstractFactoryEdificios factory =new FactoryEdificiosTerran(Color.ROJO);
		factory.fabricarCreadorSoldados();
		for(int i=12;i>1;i--){
			Assert.assertTrue(factory.pasarTurno().isEmpty());
		}
		ArrayList<Edificio> lista=factory.pasarTurno();
		Assert.assertFalse(lista.isEmpty());
		Barraca barraca = (Barraca) lista.remove(0);
		Assert.assertTrue(lista.isEmpty());
		Assert.assertTrue(barraca.color()==Color.ROJO);
	}
	@Test
	public void crearPueroEstelarTerranTest() {
		AbstractFactoryEdificios factory =new FactoryEdificiosTerran(Color.ROJO);
		factory.fabricarCreadorAereos();
		for(int i=10;i>1;i--){
			Assert.assertTrue(factory.pasarTurno().isEmpty());
		}
		ArrayList<Edificio> lista=factory.pasarTurno();
		Assert.assertFalse(lista.isEmpty());
		PuertoEstelarTerran puerto = (PuertoEstelarTerran) lista.remove(0);
		Assert.assertTrue(lista.isEmpty());
		Assert.assertTrue(puerto.color()==Color.ROJO);
	}
	@Test
	public void crearFabricaTest() {
		AbstractFactoryEdificios factory =new FactoryEdificiosTerran(Color.ROJO);
		factory.fabricarCreadorTerrestres();
		for(int i=12;i>1;i--){
			Assert.assertTrue(factory.pasarTurno().isEmpty());
		}
		ArrayList<Edificio> lista=factory.pasarTurno();
		Assert.assertFalse(lista.isEmpty());
		Fabrica fabrica = (Fabrica) lista.remove(0);
		Assert.assertTrue(lista.isEmpty());
		Assert.assertTrue(fabrica.color()==Color.ROJO);
	}
	

}
