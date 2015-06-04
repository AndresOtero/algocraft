package algo3.algocraft;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;





import algo3.algocraft.edificios.*;

public class FactoryEdificiosTerranTest {

	@Test
	public void crearCentroTest() {
		AbstractFactoryEdificios factory =new FactoryEdificiosTerran(Color.ROJO);
		Mineral mineral = new Mineral(new Posicion(0,0));
		CentroDeMineral centro=(CentroDeMineral) factory.fabricarRecolectableMinerales(mineral);
		Assert.assertTrue(centro.color()==Color.ROJO);
	}
	@Test
	public void crearRefineriaTest() {
		AbstractFactoryEdificios factory =new FactoryEdificiosTerran(Color.ROJO);
		VolcanGasVespeno gas = new VolcanGasVespeno(new Posicion(0,0));
		Refineria refineria =(Refineria) factory.fabricarRecolectableGas(gas);
		Assert.assertTrue(refineria.color()==Color.ROJO);
	}
	@Test
	public void crearDepositoTest() {
		AbstractFactoryEdificios factory =new FactoryEdificiosTerran(Color.ROJO);
		DepositoDeSuminisitros deposito=(DepositoDeSuminisitros) factory.fabricarSumaPoblacion();
		Assert.assertTrue(deposito.color()==Color.ROJO);
	}
	@Test
	public void crearBarracaTest() {
		AbstractFactoryEdificios factory =new FactoryEdificiosTerran(Color.ROJO);
		Barraca barraca=(Barraca) factory.fabricarCreadorSoldados();
		Assert.assertTrue(barraca.color()==Color.ROJO);
	}
	@Test
	public void crearPueroEstelarTerranTest() {
		AbstractFactoryEdificios factory =new FactoryEdificiosTerran(Color.ROJO);
		PuertoEstelarTerran puerto=(PuertoEstelarTerran) factory.fabricarCreadorAereos();
		Assert.assertTrue(puerto.color()==Color.ROJO);
	}
	@Test
	public void crearFabricaTest() {
		AbstractFactoryEdificios factory =new FactoryEdificiosTerran(Color.ROJO);
		Fabrica fabrica =(Fabrica) factory.fabricarCreadorTerrestres();
		Assert.assertTrue(fabrica.color()==Color.ROJO);
	}
	

}
