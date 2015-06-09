package algo3.algocraft;

import java.util.ArrayList;

import org.junit.Test;

public class PartidaMuyBasicaTest {
	@Test
public void hayGanadorTest(){
	Juego juego = Juego.getInstance();
	juego.crearJugador("Donofrio",Color.ROJO,TipoRaza.TERRAN);
	juego.crearJugador("Angelici",Color.AZUL,TipoRaza.TERRAN);
	juego.iniciarJuego();
	
	
	
	}
	

}
