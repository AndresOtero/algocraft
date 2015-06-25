package algo3.algocraft.controlador;

import java.util.HashMap;

import javax.management.RuntimeErrorException;

import algo3.algocraft.Celda;
import algo3.algocraft.Color;
import algo3.algocraft.Juego;
import algo3.algocraft.Posicion;
import algo3.algocraft.Ser;
import algo3.algocraft.TipoRaza;


public class Controlador {
	private double altoPantalla;
	private double anchoPantalla;
	private double altoMenu = 200;
	private double altoMapa;
	private Juego juego;
	double anchoCuadrado;
	double altoCuadrado;
	private int filaAnterior = 50;
	private int columnaAnterior = 50;
	private Ser serActual = null;
	private boolean apretoMover = false;
	private HashMap<String, Posicion> botones = new HashMap<String, Posicion>();
	
	private void cargarBotones(){
		botones.put("Mover", new Posicion(10,840));
		botones.put("PasarTurno", new Posicion(210,870));
	}
	
	
	public Controlador(double ancho, double alto){
		altoPantalla = alto;
		anchoPantalla = ancho;
		cargarBotones();
		altoMapa  = altoPantalla - altoMenu;
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
	
	private String edificioCrear = "";
	public void hicieronClick(int x, int y){
		if(y > altoPantalla - altoMapa){ // esta en el mapa
			
			int fila = (int) (x / (anchoCuadrado));
			int columna = -(int) ((y-altoMenu) / (altoCuadrado))+15;
			
			//Ser unSer = mover(fila, columna);
			serActual = juego.queHayEnCeldaTerrestre(fila, columna);
			if(serActual != null){
				apretoMover = false;
				filaAnterior = fila;
				columnaAnterior = columna;
			}else if(apretoMover){
				apretoMover = false;
				mover(fila,columna);
			}else if(edificioCrear != ""){
				if(edificioCrear == "Barraca"){
					juego.crearCreadorSoldados(fila,columna);
				}
				if(edificioCrear == "Fabrica"){
					juego.crearCreadorTerrestres(fila,columna);
				}
				if(edificioCrear == "Puerto Estelar"){
					juego.crearCreadorAereos(fila,columna);
				}if(edificioCrear == "Deposito Suministro"){
					juego.crearSumaPoblacion(fila, columna);
				}if(edificioCrear == "Refineria"){
					juego.crearRecolectableGas(fila, columna);
				}
				
				edificioCrear = "";
			}
		}else{ // hizo click en un menu
			Posicion pasarTurno = botones.get("PasarTurno");
			if( x >= pasarTurno.x() && x <= pasarTurno.x() + anchoCuadrado && y >=altoPantalla -pasarTurno.y() - altoCuadrado && y <= altoPantalla -pasarTurno.y()  )//pasarTurno
				juego.pasarTurno();
			Posicion moverPosicion = botones.get("Mover");
			if( x >= moverPosicion.x() && x <= moverPosicion.x() + anchoCuadrado && y >=altoPantalla -moverPosicion.y() - altoCuadrado && y <= altoPantalla -moverPosicion.y()){//mover
				apretoMover = true;
			}
			Posicion barracaPosicion = botones.get("Barraca");
			if( x >= barracaPosicion.x() && x <= barracaPosicion.x() + anchoCuadrado && y >=altoPantalla -barracaPosicion.y() - altoCuadrado && y <= altoPantalla -barracaPosicion.y()){//crearBarraca
				edificioCrear = "Barraca";
			}
			Posicion fabricaPosicion = botones.get("Fabrica");
			if( x >= fabricaPosicion.x() && x <= fabricaPosicion.x() + anchoCuadrado && y >=altoPantalla -fabricaPosicion.y() - altoCuadrado && y <= altoPantalla -fabricaPosicion.y()){//crearBarraca
				edificioCrear = "Fabrica";
			}
			Posicion puertoEstelarPosicion = botones.get("Puerto Estelar");
			if( x >= puertoEstelarPosicion.x() && x <= puertoEstelarPosicion.x() + anchoCuadrado && y >=altoPantalla -puertoEstelarPosicion.y() - altoCuadrado && y <= altoPantalla -puertoEstelarPosicion.y()){//crearBarraca
				edificioCrear = "Puerto Estelar";
			}
			Posicion depositoPosicion = botones.get("Deposito Suministro");
			if( x >= depositoPosicion.x() && x <= depositoPosicion.x() + anchoCuadrado && y >=altoPantalla -depositoPosicion.y() - altoCuadrado && y <= altoPantalla -depositoPosicion.y()){//crearBarraca
				edificioCrear = "Deposito Suministro";
			}
			Posicion refineriaPosicion = botones.get("Refineria");
			if( x >= refineriaPosicion.x() && x <= refineriaPosicion.x() + anchoCuadrado && y >=altoPantalla -refineriaPosicion.y() - altoCuadrado && y <= altoPantalla -refineriaPosicion.y()){//crearBarraca
				edificioCrear = "Refineria";
			}
		}
		
		
		//return Codificador.obtenerElemento(unSer.devolverID(), 4).nombre();
		
	}
	public String queHay(int x, int y){
		if(y > altoPantalla - altoMapa){
		int fila = (int) (x / (anchoCuadrado));
		int columna = -(int) ((y-altoMenu) / (altoCuadrado))+15;
		
		Ser unSer = juego.queHayEnCeldaTerrestre(fila, columna);
		
		
		
		if(unSer != null)
			return Codificador.obtenerElemento(unSer.devolverID(), 4).nombre();
		return "Pasto";
		}
		return "";
	}

	
	public HashMap<Posicion, Elemento> menuDibujar(){
		HashMap<Posicion, Elemento> menu = new HashMap<Posicion, Elemento>();
		Elemento ele = new Elemento("Marine");
		ele.setColorDibujable(new ColorDibujable(0, 0, 0));
		menu.put(new Posicion(0,0), ele);
		
		
		return menu;
	}
	
	public HashMap<Posicion, Elemento> botonesDibujar(){
		HashMap<Posicion, Elemento> menu = new HashMap<Posicion, Elemento>();
		
		agregarConstruibles(menu);
		
		Elemento pasarTurno = new Elemento("PasarTurno");
		pasarTurno.setColorDibujable(new ColorDibujable(1, 1, 1));
		menu.put(botones.get("PasarTurno"), pasarTurno);
		
		if(serActual != null){
			Elemento mover = new Elemento("Mover");
			mover.setColorDibujable(new ColorDibujable(1, 1, 1));
			menu.put(botones.get("Mover"), mover);
		}
		
		return menu;
	}


	private void agregarConstruibles(HashMap<Posicion, Elemento> menu) {
		String[] edificios = juego.queEdificioPuedeConstruirJugadorActual();
		int xInicial = 200;
		int YInicial = (int)(altoPantalla - 10 - altoCuadrado);
		for(String palabra : edificios){
			Elemento ele = new Elemento(palabra);
			ele.setColorDibujable(new ColorDibujable(1, 1, 1));
			menu.put(new Posicion(xInicial,YInicial), ele);
			botones.put(palabra, new Posicion(xInicial,YInicial));
			xInicial += anchoCuadrado + 10;
		}
	}
	
	
	
	public HashMap<Posicion, String> palabrasDibujar(){
		HashMap<Posicion, String> palabras = new HashMap<Posicion, String>();
		if(serActual != null){
			palabras.put(new Posicion(10,1000),Codificador.obtenerElemento(serActual.devolverID(), 4).nombre());
			palabras.put(new Posicion(10,980), "Vida: " + serActual.vida());
			palabras.put(new Posicion(10,950), "Escudo: " + serActual.escudo());
			palabras.put(new Posicion(10,920), "Color: " + serActual.color());
		}
		palabras.put(new Posicion(500,920), "Jugador Actual: " + juego.JugadorActual());
		palabras.put(new Posicion(500,900), "Gas: " + juego.gasJugadorActual());
		palabras.put(new Posicion(500,880), "Mineral: " + juego.mineralJugadorActual());
		if(apretoMover){
			palabras.put(new Posicion(500,500), "Seleccione posicion final" );
		}
		if(edificioCrear != ""){
			palabras.put(new Posicion(500,500), "Seleccione posicion a crear" );
		}
		return palabras;
	}
	
	private Ser mover(int fila, int columna) {
		Ser unSer = juego.queHayEnCeldaTerrestre(fila, columna);
		
		if(unSer != null && filaAnterior == 50){
			filaAnterior = fila;
			columnaAnterior = columna;
		}else{
			if(unSer == null && filaAnterior != 50){
				System.out.println("Fila Anterior: " + filaAnterior +", Columna Anterior: "+columnaAnterior + ", Fila: " + fila + ", Columna: "+ columna);
				juego.moverPosicionTerrestre(filaAnterior, columnaAnterior, fila, columna);
			}
			filaAnterior = 50;
			columnaAnterior = 50;
			
		}
		return unSer;
	}
	
	
	
	public HashMap<Posicion, Elemento> GrillaADibujar(){
		HashMap<Posicion, Elemento> grillaResuelta =  Codificador.grillaResuelta(juego.grillaColorUnidadTerrestre());
		HashMap<Posicion, Elemento> grillaFinal = new HashMap<Posicion, Elemento>();
		double largo = (double)Math.sqrt(grillaResuelta.keySet().size());
		anchoCuadrado = (anchoPantalla) / largo;
		altoCuadrado = (altoPantalla-200) / largo;
		for(Posicion pos : grillaResuelta.keySet()){
			grillaFinal.put(new Posicion((int)anchoCuadrado*pos.x(),(int)altoCuadrado*pos.y()), grillaResuelta.get(pos));
		}
		return grillaFinal;
	}
	
	
	public ColorDibujable ColorActual(){
		return Codificador.obtenerColor(juego.ColorActual());
	}
	
	
	
	
}
