package algo3.algocraft.controlador;

import java.awt.Font;
import java.util.ArrayList;
import java.util.HashMap;

import javax.management.RuntimeErrorException;

import algo3.algocraft.AtaqueMagico;
import algo3.algocraft.Celda;
import algo3.algocraft.Color;
import algo3.algocraft.Juego;
import algo3.algocraft.Posicion;
import algo3.algocraft.Ser;
import algo3.algocraft.TipoRaza;
import algo3.algocraft.exceptions.ElEdificioNoPuedeCrearEstaUnidad;
import algo3.algocraft.exceptions.NoEsPosibleMoverException;
import algo3.algocraft.unidades.Unidad;

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
	private String apretoCrear = "";
	private HashMap<String, Posicion> botones = new HashMap<String, Posicion>();
	String ganador = "";
	private String edificioCrear = "";
	private boolean apretoAtacar = false;
	private String mensaje = "";
	private int contMensaje = 0;
	private boolean anteriorTerrestre = true;
	private boolean apretoElevar = false;
	private boolean apretoRadiacion = false;
	private boolean apretoMisil = false;

	private void cargarBotones() {
		botones = new HashMap<String, Posicion>();
		botones.put("Mover", new Posicion((int) anchoPantalla / 100,
				(int) (altoPantalla - altoPantalla / 5)));
		botones.put("PasarTurno", new Posicion((int) altoMenu,
				(int) (altoPantalla - altoPantalla / 5)));
		botones.put("Atacar", new Posicion(
				(int) (2 * (anchoPantalla / 100) + anchoCuadrado * 2),
				(int) (altoPantalla - altoPantalla / 5)));
		botones.put(
				"Cancelar",
				new Posicion((int) (botones.get("PasarTurno").x()
						+ anchoCuadrado * 2 + altoPantalla / 100), botones.get(
						"PasarTurno").y()));
		botones.put("Elevar",
				new Posicion((int) (botones.get("Cancelar").x() + anchoCuadrado
						* 2 + altoPantalla / 100), botones.get("Cancelar").y()));
		botones.put("Subir",
				new Posicion((int) (botones.get("Elevar").x() + anchoCuadrado
						* 2 + altoPantalla / 100), botones.get("Elevar").y()));
	}

	public Posicion posicionMenu() {
		return new Posicion(0,
				(int) (altoPantalla - altoMenu - altoPantalla / 50));
	}

	public Posicion medidasMenu() {
		return new Posicion((int) altoMenu, (int) altoPantalla);
	}

	public Controlador(double ancho, double alto) {

		altoPantalla = alto;
		anchoPantalla = ancho;
		altoMapa = altoPantalla - altoMenu;
		juego = Juego.getInstance();
		juego.crearJugador("Vader", Color.ROJO, TipoRaza.TERRAN);
		juego.crearJugador("Fede", Color.AMARILLO, TipoRaza.PROTOSS);
		juego.iniciarJuego();
		juego.pasarTurno();
		juego.crearCreadorSoldados(5, 7);
		for (int a = 0; a < 30; a++)
			juego.pasarTurno();
		juego.crearCreadorTerrestres(8, 8);
		for (int a = 0; a < 30; a++)
			juego.pasarTurno();
		juego.crearCreadorAereos(9, 9);
		for (int a = 0; a < 30; a++)
			juego.pasarTurno();
		juego.crearNaveCiencia(9, 9);
		for (int a = 0; a < 30; a++)
			juego.pasarTurno();
		juego.pasarTurno();
		juego.crearCreadorSoldados(12, 12);
		for (int a = 0; a < 30; a++)
			juego.pasarTurno();
		juego.crearZealot(12, 12);
		for (int a = 0; a < 30; a++)
			juego.pasarTurno();
	}

	private void reiniciarBotones() {
		edificioCrear = "";
		apretoAtacar = false;
		columnaAnterior = 50;
		filaAnterior = 50;
		apretoMisil = false;
		serActual = null;
		apretoRadiacion = false;
		mensaje = "";
	}

	public void hicieronClick(int x, int y) {
		if (y > altoPantalla - altoMapa) { // esta en el mapa

			int fila = (int) (x / (anchoCuadrado));
			int columna = -(int) ((y - altoMenu) / (altoCuadrado)) + 15;
			boolean terrestre;
			if(fila < 15){
				serActual = juego.queHayEnCeldaTerrestre(fila, columna);
				terrestre = true;
			}else{
				fila = fila-16;
				serActual = juego.queHayEnCeldaAerea(fila, columna);
				terrestre = false;
			}
			if(apretoRadiacion){//Radiacion
				if(serActual != null){
					juego.ataqueRadiacion(filaAnterior , columnaAnterior, fila, columna);
				}else{
					crearError("No hay una unidad en esa posicion");
				}
				reiniciarBotones();
			}
			if(apretoMisil){//Misil
				ArrayList<Unidad> algo = juego.ataqueMagicoEnRadio(filaAnterior, columnaAnterior, fila, columna);
				reiniciarBotones();
			}
			//quizo atacar
			if(serActual != null && apretoAtacar){
				apretoAtacar = false;
				atacar(fila,columna);
			}else if (serActual != null) {//quizo seleccionar
				apretoMover = false;
				filaAnterior = fila;
				columnaAnterior = columna;
			} else if (apretoMover) {//quizo mover
				if((terrestre && !anteriorTerrestre)||(!terrestre && anteriorTerrestre)){
					crearError("No se puede Mover");
				}else{
					mover(fila, columna);
				}
				apretoMover = false;
			} else if (edificioCrear != "") {
				if (edificioCrear == "Barraca") {
					juego.crearCreadorSoldados(fila, columna);
				}
				if (edificioCrear == "Fabrica") {
					juego.crearCreadorTerrestres(fila, columna);
				}
				if (edificioCrear == "Puerto Estelar") {
					juego.crearCreadorAereos(fila, columna);
				}
				if (edificioCrear == "Deposito Suministro") {
					juego.crearSumaPoblacion(fila, columna);
				}
				if (edificioCrear == "Refineria") {
					juego.crearRecolectableGas(fila, columna);
				}
				if (edificioCrear == "CentroMineral") {
					juego.crearRecolectableMinerales(fila, columna);
				}

				edificioCrear = "";
			}
			if (apretoCrear != "") {
				try{
				if (apretoCrear == "Marine") {
					juego.crearMarine(fila, columna);
				}
				else if (apretoCrear == "Zealot") {
					juego.crearZealot(fila, columna);
				}
				else if (apretoCrear == "Golliat") {
					juego.crearGolliat(fila, columna);
				}
				else if (apretoCrear == "Espectro") {
					juego.crearEspectro(fila, columna);
				}else if(apretoCrear == "NaveCiencia"){
					juego.crearNaveCiencia(fila, columna);
				}else if(apretoCrear == "NaveTransporteProtoss"){
					juego.crearNaveTransporteProtoss(fila, columna);
				}else if(apretoCrear == "NaveTransporteTerran"){
					juego.crearNaveTransporteTerran(fila, columna);
				}else if(apretoCrear == "Dragon"){
					juego.crearDragon(fila, columna);
				}else if(apretoCrear == "Scout"){
					juego.crearScout(fila, columna);
				}else if(apretoCrear == "AltoTemplario"){
					juego.crearAltoTemplario(fila, columna);
				}
				}catch(ElEdificioNoPuedeCrearEstaUnidad e){
					mensaje  = "No se puede crear esa unidad en ese edificio";
					contMensaje = 100;
				}
				apretoCrear = "";
			}
			if(serActual == null){
				cargarBotones();
			}
			anteriorTerrestre  = terrestre;
		} else { // hizo click en un menu

			Posicion pasarTurno = botones.get("PasarTurno");
			if (x >= pasarTurno.x() && x <= pasarTurno.x() + anchoCuadrado
					&& y >= altoPantalla - pasarTurno.y() - altoCuadrado
					&& y <= altoPantalla - pasarTurno.y()){// pasarTurno
				juego.pasarTurno();
				botones = new HashMap<String, Posicion>();
				cargarBotones();
				return;
			}
			Posicion cancelarPosicion = botones.get("Cancelar");
			if (x >= cancelarPosicion.x() && x <= cancelarPosicion.x() + anchoCuadrado
					&& y >= altoPantalla - cancelarPosicion.y() - altoCuadrado
					&& y <= altoPantalla - cancelarPosicion.y()){// Cancelar
				reiniciarBotones();
				return;
			}
			Posicion moverPosicion = botones.get("Mover");
			if (x >= moverPosicion.x()
					&& x <= moverPosicion.x() + anchoCuadrado
					&& y >= altoPantalla - moverPosicion.y() - altoCuadrado
					&& y <= altoPantalla - moverPosicion.y()) {// mover
				apretoMover = true;
			}
			Posicion elevarPosicion = botones.get("Elevar");
			if (x >= elevarPosicion.x()
					&& x <= elevarPosicion.x() + anchoCuadrado
					&& y >= altoPantalla - elevarPosicion.y() - altoCuadrado
							&& y <= altoPantalla - elevarPosicion.y()) {// elevar
				try{
					if(anteriorTerrestre){
						juego.elevar(filaAnterior, columnaAnterior);
					}else{
						juego.descender(filaAnterior, columnaAnterior);
					}
				}catch(NoEsPosibleMoverException e){
					crearError("No se puede mover");
				}
				anteriorTerrestre = !anteriorTerrestre;
			}
			Posicion atacarPosicion = botones.get("Atacar");
			if (x >= atacarPosicion.x()
					&& x <= atacarPosicion.x() + anchoCuadrado
					&& y >= altoPantalla - atacarPosicion.y() - altoCuadrado
					&& y <= altoPantalla - atacarPosicion.y()) {// atacar
				apretoAtacar = true;
			}
			Posicion misilPosicion = botones.get("EMP");
			if (misilPosicion != null && x >= misilPosicion.x()
					&& x <= misilPosicion.x() + anchoCuadrado
					&& y >= altoPantalla - misilPosicion.y() - altoCuadrado
					&& y <= altoPantalla - misilPosicion.y()) {// Misil
				apretoMisil = true;
			}
			Posicion radiacionPosicion = botones.get("RADIACION");//Radiacion
			if (radiacionPosicion != null && x >= radiacionPosicion.x()
					&& x <= radiacionPosicion.x() + anchoCuadrado
					&& y >= altoPantalla - radiacionPosicion.y() - altoCuadrado
					&& y <= altoPantalla - radiacionPosicion.y()) {// 
				apretoRadiacion = true;
			}
			Posicion barracaPosicion = botones.get("Barraca");
			if (barracaPosicion == null)
				barracaPosicion = botones.get("Acesso");
			if (x >= barracaPosicion.x()
					&& x <= barracaPosicion.x() + anchoCuadrado
					&& y >= altoPantalla - barracaPosicion.y() - altoCuadrado
					&& y <= altoPantalla - barracaPosicion.y()) {// crearBarraca
				edificioCrear = "Barraca";
			}
			Posicion fabricaPosicion = botones.get("Fabrica");
			if (fabricaPosicion == null)
				fabricaPosicion = botones.get("Archivos Templarios");
			if (x >= fabricaPosicion.x()
					&& x <= fabricaPosicion.x() + anchoCuadrado
					&& y >= altoPantalla - fabricaPosicion.y() - altoCuadrado
					&& y <= altoPantalla - fabricaPosicion.y()) {// crearBarraca
				edificioCrear = "Fabrica";
			}
			Posicion puertoEstelarPosicion = botones.get("Puerto Estelar");
			if (x >= puertoEstelarPosicion.x()
					&& x <= puertoEstelarPosicion.x() + anchoCuadrado
					&& y >= altoPantalla - puertoEstelarPosicion.y()
							- altoCuadrado
					&& y <= altoPantalla - puertoEstelarPosicion.y()) {// crearBarraca
				edificioCrear = "Puerto Estelar";
			}
			Posicion depositoPosicion = botones.get("Deposito Suministro");
			if (depositoPosicion == null)
				depositoPosicion = botones.get("Pilon");
			if (x >= depositoPosicion.x()
					&& x <= depositoPosicion.x() + anchoCuadrado
					&& y >= altoPantalla - depositoPosicion.y() - altoCuadrado
					&& y <= altoPantalla - depositoPosicion.y()) {// crearBarraca
				edificioCrear = "Deposito Suministro";
			}
			Posicion refineriaPosicion = botones.get("Refineria");
			if (refineriaPosicion == null)
				refineriaPosicion = botones.get("Asimilador");
			if (x >= refineriaPosicion.x()
					&& x <= refineriaPosicion.x() + anchoCuadrado
					&& y >= altoPantalla - refineriaPosicion.y() - altoCuadrado
					&& y <= altoPantalla - refineriaPosicion.y()) {// crearBarraca
				edificioCrear = "Refineria";
			}
			Posicion centroPosicion = botones.get("Centro de Mineral");
			if (centroPosicion == null)
				centroPosicion = botones.get("Nexo Mineral");
			if (x >= centroPosicion.x()
					&& x <= centroPosicion.x() + anchoCuadrado
					&& y >= altoPantalla - centroPosicion.y() - altoCuadrado
					&& y <= altoPantalla - centroPosicion.y()) {// crearBarraca
				edificioCrear = "CentroMineral";
			}

			// UNIDADES
			Posicion marinePosicion = botones.get("Marine");
			if (marinePosicion != null && x >= marinePosicion.x()
					&& x <= marinePosicion.x() + anchoCuadrado
					&& y >= altoPantalla - marinePosicion.y() - altoCuadrado
					&& y <= altoPantalla - marinePosicion.y()) {
				apretoCrear = "Marine";
			}
			Posicion zealotPosicion = botones.get("Zealot");
			if (zealotPosicion != null && x >= zealotPosicion.x()
					&& x <= zealotPosicion.x() + anchoCuadrado
					&& y >= altoPantalla - zealotPosicion.y() - altoCuadrado
					&& y <= altoPantalla - zealotPosicion.y()) {
				apretoCrear = "Zealot";
			
			}
			Posicion golliatPosicion = botones.get("Golliat");
			if (golliatPosicion != null && x >= golliatPosicion.x()
					&& x <= golliatPosicion.x() + anchoCuadrado
					&& y >= altoPantalla - golliatPosicion.y() - altoCuadrado
					&& y <= altoPantalla - golliatPosicion.y()) {
				apretoCrear = "Golliat";
			
			}
			Posicion espectroPosicion = botones.get("Espectro");
			if (espectroPosicion != null && x >= espectroPosicion.x()
					&& x <= espectroPosicion.x() + anchoCuadrado
					&& y >= altoPantalla - espectroPosicion.y() - altoCuadrado
					&& y <= altoPantalla - espectroPosicion.y()) {
				apretoCrear = "Espectro";
			
			}
			Posicion naveCienciaPosicion = botones.get("Nave Ciencia");
			if (naveCienciaPosicion != null && x >= naveCienciaPosicion.x()
					&& x <= naveCienciaPosicion.x() + anchoCuadrado
					&& y >= altoPantalla - naveCienciaPosicion.y() - altoCuadrado
					&& y <= altoPantalla - naveCienciaPosicion.y()) {
				apretoCrear = "NaveCiencia";
			
			}
			Posicion naveTransportePosicion = botones.get("Nave Transporte Terran");
			if (naveTransportePosicion != null && x >= naveTransportePosicion.x()
					&& x <= naveTransportePosicion.x() + anchoCuadrado
					&& y >= altoPantalla - naveTransportePosicion.y() - altoCuadrado
					&& y <= altoPantalla - naveTransportePosicion.y()) {
				apretoCrear = "NaveTransporteTerran";
			
			}
			naveTransportePosicion = botones.get("Nave Transporte Protoss");
			if (naveTransportePosicion != null && x >= naveTransportePosicion.x()
					&& x <= naveTransportePosicion.x() + anchoCuadrado
					&& y >= altoPantalla - naveTransportePosicion.y() - altoCuadrado
					&& y <= altoPantalla - naveTransportePosicion.y()) {
				apretoCrear = "NaveTransporteProtoss";
			
			}
			Posicion dragonPosicion = botones.get("Dragon");
			if (dragonPosicion != null && x >= dragonPosicion.x()
					&& x <= dragonPosicion.x() + anchoCuadrado
					&& y >= altoPantalla - dragonPosicion.y() - altoCuadrado
					&& y <= altoPantalla - dragonPosicion.y()) {
				apretoCrear = "Dragon";
			
			}
			Posicion scoutPosicion = botones.get("Scout");
			if (scoutPosicion != null && x >= scoutPosicion.x()
					&& x <= scoutPosicion.x() + anchoCuadrado
					&& y >= altoPantalla - scoutPosicion.y() - altoCuadrado
					&& y <= altoPantalla - scoutPosicion.y()) {
				apretoCrear = "Scout";
			
			}
			Posicion templarioPosicion = botones.get("Alto Templario");
			if (templarioPosicion != null && x >= templarioPosicion.x()
					&& x <= templarioPosicion.x() + anchoCuadrado
					&& y >= altoPantalla - templarioPosicion.y() - altoCuadrado
					&& y <= altoPantalla - templarioPosicion.y()) {
				apretoCrear = "AltoTemplario";
			
			}
		}

	}

	private void crearError(String error) {
		contMensaje = 100;
		mensaje = error;
	}

	public String queHay(int x, int y) {
		/*
		 * if (y > altoPantalla - altoMapa) { int fila = (int) (x /
		 * (anchoCuadrado)); int columna = -(int) ((y - altoMenu) /
		 * (altoCuadrado)) + 15;
		 * 
		 * Ser unSer = juego.queHayEnCeldaTerrestre(fila, columna);
		 * 
		 * if (unSer != null) return
		 * Codificador.obtenerElemento(unSer.devolverID(), 4) .nombre(); return
		 * "Pasto"; }
		 */
		return "";
	}

	public HashMap<Posicion, Elemento> menuDibujar() {
		HashMap<Posicion, Elemento> menu = new HashMap<Posicion, Elemento>();
		Elemento ele = new Elemento("Marine");
		ele.setColorDibujable(new ColorDibujable(0, 0, 0));
		menu.put(new Posicion(0, 0), ele);

		return menu;
	}

	public HashMap<Posicion, Elemento> botonesDibujar() {
		HashMap<Posicion, Elemento> menu = new HashMap<Posicion, Elemento>();

		agregarConstruibles(menu);
		agregarCreables(menu);
		agregarMagicos(menu);

		Elemento pasarTurno = new Elemento("PasarTurno");
		pasarTurno.setColorDibujable(new ColorDibujable(1, 1, 1));
		menu.put(botones.get("PasarTurno"), pasarTurno);

		if (serActual != null && serActual.vision() != 3) {
			Elemento mover = new Elemento("Mover");
			mover.setColorDibujable(new ColorDibujable(1, 1, 1));
			menu.put(botones.get("Mover"), mover);

			Elemento atacar = new Elemento("Atacar");
			atacar.setColorDibujable(new ColorDibujable(1, 1, 1));
			menu.put(botones.get("Atacar"), atacar);

			Elemento elevar = new Elemento("Elevar");
			elevar.setColorDibujable(new ColorDibujable(1, 1, 1));
			menu.put(botones.get("Elevar"), elevar);
			
			Elemento subir = new Elemento("Subir");
			subir.setColorDibujable(new ColorDibujable(1, 1, 1));
			menu.put(botones.get("Subir"), subir);

		}

		Elemento cancelar = new Elemento("Cancelar");
		cancelar.setColorDibujable(new ColorDibujable(1, 1, 1));
		menu.put(botones.get("Cancelar"), cancelar);

		return menu;
	}

	private void agregarMagicos(HashMap<Posicion, Elemento> menu) {
		AtaqueMagico[] magias;
		if (filaAnterior == 50 || serActual == null)
			return;
		if (anteriorTerrestre) {
			magias = juego.ataquesMagicosQueTieneTierra(filaAnterior,
					columnaAnterior);
		} else {
			magias = juego.ataquesMagicosQueTieneAire(filaAnterior,
					columnaAnterior);
		}
		int xInicial = (int) (botones.get("Subir").x() + anchoCuadrado * 2 + altoPantalla / 100);
		for (AtaqueMagico ataque : magias) {
			Elemento ele = new Elemento(ataque.toString());
			ele.setColorDibujable(new ColorDibujable(1, 1, 1));
			menu.put(new Posicion(xInicial, (int) botones.get("Subir").y()),
					ele);
			botones.put(ataque.toString(), new Posicion(xInicial, (int) botones
					.get("Subir").y()));
			xInicial += anchoCuadrado * 2 + 10;
		}
	}

	private void agregarConstruibles(HashMap<Posicion, Elemento> menu) {
		String[] edificios = juego.queEdificioPuedeConstruirJugadorActual();
		int xInicial = (int) altoMenu;
		int YInicial = (int) (altoPantalla - 20 - altoCuadrado);
		for (String palabra : edificios) {
			Elemento ele = new Elemento(palabra);
			ele.setColorDibujable(new ColorDibujable(1, 1, 1));
			menu.put(new Posicion(xInicial, YInicial), ele);
			botones.put(palabra, new Posicion(xInicial, YInicial));
			xInicial += anchoCuadrado * 2 + 10;
		}
	}

	private void agregarCreables(HashMap<Posicion, Elemento> menu) {
		String[] unidades = juego.queUnidadesPuedeConstruirJugadorActual();
		int xInicial = (int) altoMenu;
		int YInicial = (int) (altoPantalla - altoCuadrado * 2 - altoPantalla / 30);
		for (String palabra : unidades) {
			Elemento ele = new Elemento(palabra);
			ele.setColorDibujable(new ColorDibujable(1, 1, 1));
			menu.put(new Posicion(xInicial, YInicial), ele);
			botones.put(palabra, new Posicion(xInicial, YInicial));
			xInicial += anchoCuadrado * 2 + 10;
		}
	}

	public HashMap<Posicion, String> palabrasDibujar() {
		HashMap<Posicion, String> palabras = new HashMap<Posicion, String>();
		if (serActual != null) {
			palabras.put(new Posicion((int) anchoPantalla / 100,
					(int) (altoPantalla - altoPantalla / 100)), Codificador
					.obtenerElemento(serActual.devolverID(), 4).nombre());
			palabras.put(new Posicion((int) anchoPantalla / 100,
					(int) (altoPantalla - altoPantalla / 25)), "Vida: "
					+ serActual.vida());
			palabras.put(new Posicion((int) anchoPantalla / 100,
					(int) (altoPantalla - 2 * altoPantalla / 30)), "Escudo: "
					+ serActual.escudo());
			palabras.put(new Posicion((int) anchoPantalla / 100,
					(int) (altoPantalla - 2 * altoPantalla / 20)), "Color: "
					+ serActual.color());
		}
		palabras.put(new Posicion((int) (anchoPantalla - anchoPantalla / 4),
				(int) (altoPantalla - altoPantalla / 100)), "Jugador Actual: "
				+ juego.JugadorActual());
		palabras.put(new Posicion((int) (anchoPantalla - anchoPantalla / 4),
				(int) (altoPantalla - altoPantalla / 25)),
				"Gas: " + juego.gasJugadorActual());
		palabras.put(new Posicion((int) (anchoPantalla - anchoPantalla / 4),
				(int) (altoPantalla - 2 * altoPantalla / 30)), "Mineral: "
				+ juego.mineralJugadorActual());
		palabras.put(new Posicion((int) (anchoPantalla - anchoPantalla / 4),
				(int) (altoPantalla - 2 * altoPantalla / 20)),
				"Raza: " + juego.razaActual());
		if (apretoMover) {
			palabras.put(new Posicion(500, 500), "Seleccione posicion final");
		}
		if (apretoAtacar || apretoRadiacion || apretoMisil) {
			palabras.put(new Posicion(500, 500), "Seleccione posicion a atacar");
		}
		if (edificioCrear != "") {
			palabras.put(new Posicion(500, 500), "Seleccione posicion a crear");
		}
		if (apretoCrear != "") {
			palabras.put(new Posicion(500, 500),
					"Seleccione edificio donde  crear");
		}
		if (ganador != "") {
			palabras.put(new Posicion(500, 500), "Gano " + ganador);
		}
		if (contMensaje > 0) {
			palabras.put(new Posicion(500, 500), mensaje);
			contMensaje--;
		}
		return palabras;
	}

	private void mover(int fila, int columna) {
		Ser unSer = juego.queHayEnCeldaTerrestre(fila, columna);
		if (columna > 15) {
			return;
		}
		if (unSer != null && filaAnterior == 50) {
			filaAnterior = fila;
			columnaAnterior = columna;
		} else {
			if (unSer == null && filaAnterior != 50) {
				if (anteriorTerrestre) {
					juego.moverPosicionTerrestre(filaAnterior, columnaAnterior,
							fila, columna);
				} else {
					juego.moverPosicionAereo(filaAnterior, columnaAnterior,
							fila, columna);
				}
			}
			filaAnterior = 50;
			columnaAnterior = 50;

		}

	}

	private Ser atacar(int fila, int columna) {
		Ser unSer = juego.queHayEnCeldaTerrestre(fila, columna);

		if (filaAnterior == 50) {
			filaAnterior = fila;
			columnaAnterior = columna;
		} else {
			if (filaAnterior != 50) {
				juego.atacarTierra(filaAnterior, columnaAnterior, fila, columna);
			}
			filaAnterior = 50;
			columnaAnterior = 50;

		}
		return unSer;
	}

	public HashMap<Posicion, Elemento> GrillaADibujar() {
		cargarBotones();
		HashMap<Posicion, Elemento> grillaResueltaTerrestre = Codificador
				.grillaResuelta(juego.grillaColorUnidadTerrestre());
		HashMap<Posicion, Elemento> grillaResueltaAereo = Codificador
				.grillaResuelta(juego.grillaColorUnidadAerea());
		HashMap<Posicion, Elemento> grillaFinal = new HashMap<Posicion, Elemento>();
		double largo = (double) Math.sqrt(grillaResueltaTerrestre.keySet()
				.size());
		anchoCuadrado = (anchoPantalla) / largo / 2;
		altoCuadrado = (altoPantalla - altoMenu) / largo;
		for (Posicion pos : grillaResueltaTerrestre.keySet()) {
			grillaFinal.put(new Posicion((int) anchoCuadrado * pos.x(),
					(int) altoCuadrado * pos.y()), grillaResueltaTerrestre
					.get(pos));
		}
		for (Posicion pos : grillaResueltaAereo.keySet()) {
			grillaFinal
					.put(new Posicion(
							(int) ((anchoCuadrado * pos.x()) + anchoPantalla / 2),
							(int) altoCuadrado * pos.y()), grillaResueltaAereo
							.get(pos));
		}
		if (juego.hayGanador())
			ganador = juego.JugadorActual();
		return grillaFinal;
	}

	public ColorDibujable ColorActual() {
		return Codificador.obtenerColor(juego.ColorActual());
	}

}
