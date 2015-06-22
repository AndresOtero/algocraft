package algo3.algocraft.controlador;

import java.util.HashMap;

import algo3.algocraft.Juego;
import algo3.algocraft.Posicion;


public class Controlador {
	private double altoPantalla;
	private double anchoPantalla;
	private Juego juego;
	
	
	public Controlador(double ancho, double alto){
		altoPantalla = alto;
		anchoPantalla = ancho;
		juego = Juego.getInstance();
	}
	
	public HashMap<Posicion, String> GrillaADibujar(){
		HashMap<Posicion, String> grillaResuelta =  Codificador.grillaResuelta(juego.grillaColorUnidadTerrestre());
		HashMap<Posicion, String> grillaFinal = new HashMap<Posicion, String>();
		double largo = (double)Math.sqrt(grillaResuelta.keySet().size());
		double anchoCuadrado = anchoPantalla / largo;
		double altoCuadrado = altoPantalla / largo;
		for(Posicion pos : grillaResuelta.keySet()){
			grillaFinal.put(new Posicion((int)anchoCuadrado*pos.abscisa(),(int)altoCuadrado*pos.ordenada()), grillaResuelta.get(pos));
		}
		return grillaFinal;
	}
	
	public void prueba(){
	
	}
	
}
