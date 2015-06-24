package algo3.algocraft.controlador;

import java.util.HashMap;

import algo3.algocraft.Celda;
import algo3.algocraft.Color;
import algo3.algocraft.Juego;
import algo3.algocraft.Posicion;
import algo3.algocraft.Ser;
import algo3.algocraft.TipoRaza;


public class Controlador {
	private double altoPantalla;
	private double anchoPantalla;
	private Juego juego;
	double anchoCuadrado;
	double altoCuadrado;
	
	
	public Controlador(double ancho, double alto){
		altoPantalla = alto;
		anchoPantalla = ancho;
		juego = Juego.getInstance();
		juego.crearJugador("Vader", Color.ROJO, TipoRaza.TERRAN);
		juego.crearJugador("Fede", Color.AMARILLO, TipoRaza.TERRAN);
		juego.iniciarJuego();
		juego.crearCreadorSoldados(3, 3);
		for(int a = 0; a<60;a++)
			juego.pasarTurno();
		
		juego.crearMarine(3, 3);
		
		for(int a = 0; a< 20;a++){
			juego.pasarTurno();
		}
		juego.crearMarine(3, 3);
		
		for(int a = 0; a< 20;a++){
			juego.pasarTurno();
		}
		
	}
	
	private int filaAnterior = -1;
	private int columnaAnterior = -1;
	
	public String hicieronClick(int x, int y){
		int fila = (int) (x / (anchoCuadrado));
		int columna = -(int) (y / (altoCuadrado))+14;
		
		Ser unSer = juego.queHayEnCeldaTerrestre(fila, columna);
		

		
		if(filaAnterior == -1  && unSer != null && filaAnterior != fila){
			filaAnterior = fila;
			columnaAnterior = columna;
		}else{
			if(filaAnterior != -1 && filaAnterior != fila){
				boolean resultado = juego.moverPosicionTerrestre(filaAnterior, columnaAnterior, fila, columna);
				filaAnterior = -1;
				columnaAnterior = -1;
				System.out.println(resultado);
			}
		}
		
		if(unSer != null)
			return Codificador.obtenerElemento(unSer.devolverID(), 4).nombre();
		return "Pasto";
	}
	
	
	
	public HashMap<Posicion, Elemento> GrillaADibujar(){
		HashMap<Posicion, Elemento> grillaResuelta =  Codificador.grillaResuelta(juego.grillaColorUnidadTerrestre());
		HashMap<Posicion, Elemento> grillaFinal = new HashMap<Posicion, Elemento>();
		double largo = (double)Math.sqrt(grillaResuelta.keySet().size());
		anchoCuadrado = anchoPantalla / largo;
		altoCuadrado = altoPantalla / largo;
		for(Posicion pos : grillaResuelta.keySet()){
			grillaFinal.put(new Posicion((int)anchoCuadrado*pos.abscisa(),(int)altoCuadrado*pos.ordenada()), grillaResuelta.get(pos));
		}
		return grillaFinal;
	}
	
	
	public ColorDibujable ColorActual(){
		return Codificador.obtenerColor(juego.ColorActual());
	}
	
	
	
	
}
