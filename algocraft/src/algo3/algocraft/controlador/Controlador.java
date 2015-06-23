package algo3.algocraft.controlador;

import java.util.HashMap;

import algo3.algocraft.Color;
import algo3.algocraft.Juego;
import algo3.algocraft.Posicion;
import algo3.algocraft.TipoRaza;


public class Controlador {
	private double altoPantalla;
	private double anchoPantalla;
	private Juego juego;
	
	
	public Controlador(double ancho, double alto){
		altoPantalla = alto;
		anchoPantalla = ancho;
		juego = Juego.getInstance();
		juego.crearJugador("Vader", Color.ROJO, TipoRaza.TERRAN);
		juego.crearJugador("Fede", Color.AMARILLO, TipoRaza.PROTOSS);
		juego.iniciarJuego();
		juego.crearCreadorSoldados(3, 3);
		juego.pasarTurno();
		juego.pasarTurno();
	}
		
	
	public HashMap<Posicion, Elemento> GrillaADibujar(){
		HashMap<Posicion, Elemento> grillaResuelta =  Codificador.grillaResuelta(juego.grillaColorUnidadTerrestre());
		HashMap<Posicion, Elemento> grillaFinal = new HashMap<Posicion, Elemento>();
		double largo = (double)Math.sqrt(grillaResuelta.keySet().size());
		double anchoCuadrado = anchoPantalla / largo;
		double altoCuadrado = altoPantalla / largo;
		for(Posicion pos : grillaResuelta.keySet()){
			grillaFinal.put(new Posicion((int)anchoCuadrado*pos.abscisa(),(int)altoCuadrado*pos.ordenada()), grillaResuelta.get(pos));
		}
		return grillaFinal;
	}
	
	
	public ColorDibujable ColorActual(){
		return Codificador.obtenerColor(juego.ColorActual());
	}
	
	
	
	
}
