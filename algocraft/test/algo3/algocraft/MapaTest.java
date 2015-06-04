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
	public void testMapaAereo() {
		ArrayList<Jugador> jugadores = new ArrayList<Jugador>();
		ArrayList<Ser> UnidadesRojas = new ArrayList<Ser>();
		Mapa mapa = Mapa.getInstance(jugadores);
		Unidad unidad1=(Unidad) new Scout(Color.ROJO);
		Unidad unidad2=(Unidad) new Scout(Color.ROJO);
		Unidad unidad3=(Unidad) new Scout(Color.ROJO);
		Unidad unidad4=(Unidad) new Scout(Color.ROJO);
		mapa.ponerAereo(new Posicion(15,15), unidad1);
		mapa.ponerTerrestre(new Posicion(16,15), unidad2);
		mapa.ponerAereo(new Posicion(17,15), unidad3);
		mapa.ponerTerrestre(new Posicion(18,15), unidad4);
		UnidadesRojas.add(unidad1);
		UnidadesRojas.add(unidad2);
		UnidadesRojas.add(unidad3);
		UnidadesRojas.add(unidad4);
		ArrayList<Ser> UnidadesMapa = mapa.seresDeJugador(Color.ROJO);
		for (int i =0;i<(UnidadesRojas).size();i++){
			Assert.assertTrue(UnidadesMapa.get(i) == UnidadesRojas.get(i));
		}
		mapa.moverAerea(unidad1, 15, 15, 20, 20);
		mapa.moverAerea(unidad1, 20, 20, 16, 15); //ocupada terrestre todo ok
	}
	public void testMapaRecursos(){
		ArrayList<Jugador> jugadores = new ArrayList<Jugador>();
		Mapa mapa = Mapa.getInstance(jugadores);
		Celda celda = mapa.ContenidoFilaColumna(1, 1);
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
