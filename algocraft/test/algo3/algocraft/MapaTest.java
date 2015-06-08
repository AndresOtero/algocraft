package algo3.algocraft;

import java.util.ArrayList;

import junit.framework.Assert;

import org.junit.Test;

import algo3.algocraft.edificios.EdificioDeRecurso;
import algo3.algocraft.edificios.Refineria;
import algo3.algocraft.unidades.Dragon;
import algo3.algocraft.unidades.Marine;
import algo3.algocraft.unidades.Scout;
import algo3.algocraft.unidades.Unidad;


public class MapaTest {
	@Test
	public void testMapaTerrestres() {
		ArrayList<Jugador> jugadores = new ArrayList<Jugador>();
		ArrayList<Ser> UnidadesRojas = new ArrayList<Ser>();
		Mapa mapa = Mapa.getInstance(50,50,jugadores);
		ArrayList<Ser> UnidadesMapa = mapa.seresDeJugador(Color.ROJO);
		Unidad unidad1=(Unidad) new Marine(Color.ROJO);
		Unidad unidad2=(Unidad) new Marine(Color.ROJO);
		Unidad unidad3=(Unidad) new Marine(Color.ROJO);
		Unidad unidad4=(Unidad) new Marine(Color.ROJO);
		Unidad unidad5=(Unidad) new Marine(Color.ROJO);
		mapa.ponerTerrestre(new Posicion(15,15), unidad1);
		mapa.ponerTerrestre(new Posicion(16,15), unidad2);
		mapa.ponerTerrestre(new Posicion(17,15), unidad3);
		mapa.ponerTerrestre(new Posicion(18,15), unidad4);
		mapa.ponerTerrestre(new Posicion(14,15), unidad5);
		UnidadesRojas.add(unidad1);
		UnidadesRojas.add(unidad2);
		UnidadesRojas.add(unidad3);
		UnidadesRojas.add(unidad4);
		UnidadesRojas.add(unidad5);
		UnidadesMapa = mapa.seresDeJugador(Color.ROJO);
		for (int i =0;i<(UnidadesRojas).size();i++){
			Assert.assertTrue(UnidadesMapa.get(i) == UnidadesRojas.get(i));
		}
		Assert.assertTrue( mapa.estaVaciaTerrestre(new Posicion(20,20)));
		Assert.assertFalse( mapa.estaVaciaTerrestre(new Posicion(15,15)));
		mapa.moverTerrestre(new Posicion(15,15),new Posicion(20,20));
		Assert.assertTrue( mapa.estaVaciaTerrestre(new Posicion(15,15)));
		Assert.assertFalse( mapa.estaVaciaTerrestre(new Posicion(20,20)));
		Assert.assertSame(unidad1, mapa.ContenidoPosicion(new Posicion(20,20)).serEnLaCeldaTerrestre());
		mapa.moverTerrestre(new Posicion(20,20), new Posicion (15,15)); 
		Assert.assertTrue( mapa.estaVaciaTerrestre(new Posicion(20,20)));
		Assert.assertFalse( mapa.estaVaciaTerrestre(new Posicion(15,15)));
		Assert.assertSame(unidad1, mapa.ContenidoPosicion(new Posicion(15,15)).serEnLaCeldaTerrestre());
		mapa.moverTerrestre(new Posicion(17,15), new Posicion (15,16)); //muevo la unidad 3
		mapa.moverTerrestre(new Posicion(18,15), new Posicion (15,14)); //muevo la unidad 4. Ahora la unidad 1 no se podria mover
		mapa.moverTerrestre(new Posicion(15,15),new Posicion(20,20));
		Assert.assertTrue( mapa.estaVaciaTerrestre(new Posicion(20,20)));
		Assert.assertFalse( mapa.estaVaciaTerrestre(new Posicion(15,15)));
		Assert.assertSame(unidad1, mapa.ContenidoPosicion(new Posicion(15,15)).serEnLaCeldaTerrestre());
		

	}
	@Test
	public void testMapaAereo() {
		ArrayList<Jugador> jugadores = new ArrayList<Jugador>();
		ArrayList<Ser> UnidadesRojas = new ArrayList<Ser>();
		Mapa mapa = Mapa.getInstance(50,49,jugadores);
		Unidad unidad1=(Unidad) new Scout(Color.ROJO);
		Unidad unidad2=(Unidad) new Scout(Color.ROJO);
		Unidad unidad3=(Unidad) new Scout(Color.ROJO);
		Unidad unidad4=(Unidad) new Scout(Color.ROJO);
		mapa.ponerAereo(new Posicion(10,10), unidad1);
		mapa.ponerTerrestre(new Posicion(10,10), unidad2);
		mapa.ponerAereo(new Posicion(10,5), unidad3);
		mapa.ponerTerrestre(new Posicion(10,4), unidad4);
		Assert.assertTrue( mapa.estaVaciaAereo(new Posicion(10,4)));
		mapa.moverAerea(new Posicion(10,10),new Posicion(10,4));
		Assert.assertTrue( mapa.estaVaciaAereo(new Posicion(10,10)));
		Assert.assertFalse( mapa.estaVaciaAereo(new Posicion(10,4)));
		Assert.assertSame(unidad1, mapa.ContenidoPosicion(new Posicion(10,4)).serEnLaCeldaAerea());
		Assert.assertSame(unidad4, mapa.ContenidoPosicion(new Posicion(10,4)).serEnLaCeldaTerrestre());
		mapa.moverAerea(new Posicion(10,4),new Posicion(10,10));
		Assert.assertTrue( mapa.estaVaciaAereo(new Posicion(10,4)));
		Assert.assertFalse( mapa.estaVaciaAereo(new Posicion(10,10)));
		Assert.assertSame(unidad1, mapa.ContenidoPosicion(new Posicion(10,10)).serEnLaCeldaAerea());
		Assert.assertSame(unidad2, mapa.ContenidoPosicion(new Posicion(10,10)).serEnLaCeldaTerrestre());

	}
	@Test
	public void testMapaRecursos(){
		ArrayList<Jugador> jugadores = new ArrayList<Jugador>();
		Mapa mapa = Mapa.getInstance(50,50,jugadores);
		Celda celda = mapa.ContenidoPosicion(new Posicion(1, 1));
		VolcanGasVespeno volcan = (VolcanGasVespeno) celda.fuenteRecurso();
		Assert.assertTrue(volcan != null);
		EdificioDeRecurso ref = new Refineria(volcan,Color.ROJO);
		Posicion pos = new Posicion(1,1);
		mapa.ponerEdificioGas(pos, ref);
		ArrayList<EdificioDeRecurso> edificiosDeGas = mapa.edificioDeGas(Color.ROJO);
		for (int i =0;i<(edificiosDeGas).size();i++){
			Assert.assertTrue(edificiosDeGas.get(i) == edificiosDeGas.get(i));
		}		
	}
}
