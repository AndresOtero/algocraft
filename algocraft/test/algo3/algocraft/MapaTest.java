package algo3.algocraft;

import java.util.ArrayList;

import junit.framework.Assert;

import org.junit.Test;

import algo3.algocraft.unidades.Dragon;
import algo3.algocraft.unidades.Marine;
import algo3.algocraft.unidades.Unidad;


public class MapaTest {
	@Test
	public void testMapa() {
		ArrayList<Jugador> jugadores = new ArrayList<Jugador>();
		ArrayList<Ser> UnidadesRojas = new ArrayList<Ser>();
		Mapa mapa = Mapa.getInstance(jugadores);
		Unidad unidad1=(Unidad) new Marine(Color.ROJO);
		Unidad unidad2=(Unidad) new Marine(Color.ROJO);
		Unidad unidad3=(Unidad) new Marine(Color.ROJO);
		Unidad unidad4=(Unidad) new Marine(Color.ROJO);
		mapa.ponerTerrestre(new Posicion(15,15), unidad1);
		mapa.ponerTerrestre(new Posicion(16,15), unidad2);
		mapa.ponerTerrestre(new Posicion(17,15), unidad3);
		mapa.ponerTerrestre(new Posicion(18,15), unidad4);
		UnidadesRojas.add(unidad1);
		UnidadesRojas.add(unidad2);
		UnidadesRojas.add(unidad3);
		UnidadesRojas.add(unidad4);
		ArrayList<Ser> UnidadesMapa = mapa.seresDeJugador(Color.ROJO);
		for (int i =0;i<(UnidadesRojas).size();i++){
			Assert.assertTrue(UnidadesMapa.get(i) == UnidadesRojas.get(i));
		}
		mapa.moverTerrestre(unidad1, 15, 15, 20, 20);
		//la siguiente linea deberia imprimir que esta ocupado
		//nose como probarlo con assert
		mapa.moverTerrestre(unidad1, 20, 20, 16, 15); 
		
		
		
		
		
		
		
		
	}

}
