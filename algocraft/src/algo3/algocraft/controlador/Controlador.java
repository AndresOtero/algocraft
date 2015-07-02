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
import algo3.algocraft.exceptions.EdificiosAnterioresNoCreadosException;
import algo3.algocraft.exceptions.ElEdificioNoPuedeCrearEstaUnidad;
import algo3.algocraft.exceptions.LaCeldaTerrestreEstaOcupada;
import algo3.algocraft.exceptions.NoEsPosibleMoverException;
import algo3.algocraft.exceptions.NoHayRecursoEnEsaPosicionException;
import algo3.algocraft.exceptions.NoHayRecursosException;
import algo3.algocraft.exceptions.NoSePuedeBajarException;
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
	private boolean apretoRadiacion = false;
	private boolean apretoMisil = false;
	private double altoBotonMenu;
	private boolean apretoSubir = false;
	private double anchoBotonMenu;
	private boolean mostrandoAcciones = false;
	private int largoMapa;

	private void cargarBotones() {
		botones = new HashMap<String, Posicion>();
		botones.put("Mover", new Posicion((int) anchoPantalla / 100,
				(int) (altoPantalla - altoPantalla / 5)));
		botones.put("PasarTurno", new Posicion((int) altoMenu,
				(int) (altoPantalla - altoPantalla / 5)));
		botones.put("Atacar", new Posicion(
				(int) (2 * (anchoPantalla / 100) + anchoBotonMenu),
				(int) (altoPantalla - altoPantalla / 5)));
		botones.put(
				"Cancelar",
				new Posicion((int) (botones.get("PasarTurno").x()
						+ anchoBotonMenu + altoPantalla / 100), botones.get(
						"PasarTurno").y()));
		botones.put("Elevar", new Posicion((int) (botones.get("Cancelar").x()
				+ anchoBotonMenu + altoPantalla / 100), botones.get("Cancelar")
				.y()));
		botones.put("Subir", new Posicion((int) (botones.get("Elevar").x()
				+ anchoBotonMenu + altoPantalla / 100), botones.get("Elevar")
				.y()));
		botones.put("Bajar", new Posicion((int) (botones.get("Subir").x()
				+ anchoBotonMenu + altoPantalla / 100), botones.get("Subir")
				.y()));
	}

	public Posicion posicionMenu() {
		return new Posicion(0,
				(int) (altoPantalla - altoMenu));
	}

	public Posicion medidasMenu() {
		return new Posicion((int) altoMenu, (int) altoPantalla);
	}

	public Controlador(double ancho, double alto) {

		altoPantalla = alto;
		anchoPantalla = ancho;
		altoMapa = altoPantalla - altoMenu;
		altoBotonMenu = (altoMenu - altoPantalla / 15) / 3;
		anchoBotonMenu = anchoPantalla / 16;
		juego = Juego.getInstance();
		juego.crearJugador("Vader", Color.ROJO, TipoRaza.TERRAN);
		juego.crearJugador("Fede", Color.AMARILLO, TipoRaza.PROTOSS);
		juego.iniciarJuego();
	}

	private void reiniciarBotones() {
		edificioCrear = "";
		apretoAtacar = false;
		columnaAnterior = 50;
		filaAnterior = 50;
		apretoMisil = false;
		apretoCrear = "";
		serActual = null;
		apretoMover = false;
		apretoRadiacion = false;
		mensaje = "";
		apretoSubir = false;
	}

	public void hicieronClick(int x, int y) {
		if (y > altoPantalla - altoMapa) { // esta en el mapa
			int fila = (int) (x / (anchoCuadrado));
			int columna = -(int) ((y - altoMenu) / (altoCuadrado)) + largoMapa;
			boolean terrestre;
			if (fila <= largoMapa) {
				serActual = juego.queHayEnCeldaTerrestre(fila, columna);
				terrestre = true;
			} else {
				fila = fila - 16;
				serActual = juego.queHayEnCeldaAerea(fila, columna);
				terrestre = false;
			}
			if (apretoSubir) {// Subir
				if (serActual == null) {
					crearError("No hay nave en esa posición");
					reiniciarBotones();
					return;
				}
				juego.subirAlTransporte(filaAnterior, columnaAnterior, fila,
						columna);
				reiniciarBotones();
				return;
			}
			if (apretoRadiacion) {// Radiacion
				if (serActual != null) {
					juego.ataqueRadiacion(filaAnterior, columnaAnterior, fila,
							columna);
				} else {
					crearError("No hay una unidad en esa posicion");
				}
				reiniciarBotones();
			}
			if (apretoMisil) {// Misil
				juego.ataqueMagicoEnRadio(filaAnterior, columnaAnterior, fila,
						columna);
				reiniciarBotones();
			}
			// quizo atacar
			if (serActual != null && apretoAtacar) {
				apretoAtacar = false;
				atacar(fila, columna,terrestre);
			} else if (serActual != null && edificioCrear == "" ) {// quizo seleccionar
				apretoMover = false;
				filaAnterior = fila;
				columnaAnterior = columna;
			} else if (apretoMover) {// quizo mover
				if ((terrestre && !anteriorTerrestre)
						|| (!terrestre && anteriorTerrestre)) {
					crearError("No se puede Mover");
				} else {
					mover(fila, columna);
				}
				apretoMover = false;
			} else if (edificioCrear != "") {
				try {
					if (edificioCrear == "Barraca") {
						juego.crearCreadorSoldados(fila, columna);
					}
					if (edificioCrear == "Fabrica") {
						try {
							juego.crearCreadorTerrestres(fila, columna);
						} catch (EdificiosAnterioresNoCreadosException e) {
							crearError("No se crearon los edificios anteriores");
						}
					}
					if (edificioCrear == "Puerto Estelar") {
						try {
							juego.crearCreadorAereos(fila, columna);
						} catch (EdificiosAnterioresNoCreadosException e) {
							crearError("No se crearon los edificios anteriores");
						}
					}
					if (edificioCrear == "Deposito Suministro") {
						juego.crearSumaPoblacion(fila, columna);
					}
					if (edificioCrear == "Refineria") {
						try {
							juego.crearRecolectableGas(fila, columna);
						} catch (NoHayRecursoEnEsaPosicionException e) {
							crearError("No hay un gas en esa posición");
						}
					}
					if (edificioCrear == "CentroMineral") {
						try {
							juego.crearRecolectableMinerales(fila, columna);
						} catch (NoHayRecursoEnEsaPosicionException e) {
							crearError("No hay un mineral en esa posición");
						}
					}
				} catch (NoHayRecursosException e) {
					crearError("No hay suficientes recursos");
				}catch (LaCeldaTerrestreEstaOcupada e) {
					crearError("Ya hay un edificio en esa posicion");
				}

				edificioCrear = "";
			}
			if (apretoCrear != "") {
				try {
					boolean rta = false;
					if (apretoCrear == "Marine") {
						rta = juego.crearMarine(fila, columna);
					} else if (apretoCrear == "Zealot") {
						rta = juego.crearZealot(fila, columna);
					} else if (apretoCrear == "Golliat") {
						rta = juego.crearGolliat(fila, columna);
					} else if (apretoCrear == "Espectro") {
						rta = juego.crearEspectro(fila, columna);
					} else if (apretoCrear == "NaveCiencia") {
						rta = juego.crearNaveCiencia(fila, columna);
					} else if (apretoCrear == "NaveTransporteProtoss") {
						rta = juego.crearNaveTransporteProtoss(fila, columna);
					} else if (apretoCrear == "NaveTransporteTerran") {
						rta = juego.crearNaveTransporteTerran(fila, columna);
					} else if (apretoCrear == "Dragon") {
						rta = juego.crearDragon(fila, columna);
					} else if (apretoCrear == "Scout") {
						rta = juego.crearScout(fila, columna);
					} else if (apretoCrear == "AltoTemplario") {
						rta = juego.crearAltoTemplario(fila, columna);
					}
					if (!rta) {
						crearError("No se pudo crear");
					}
				} catch (ElEdificioNoPuedeCrearEstaUnidad e) {
					crearError("No se puede crear esa unidad en ese edificio");
				} catch (NullPointerException e) {
					crearError("No seleccionó ningun edificio");
				}
				apretoCrear = "";
			}
			if (serActual == null) {
				cargarBotones();
			}
			anteriorTerrestre = terrestre;
		} else { // hizo click en un menu

			Posicion pasarTurno = botones.get("PasarTurno");
			if (x >= pasarTurno.x() && x <= pasarTurno.x() + anchoBotonMenu
					&& y >= altoPantalla - pasarTurno.y() - altoBotonMenu
					&& y <= altoPantalla - pasarTurno.y()) {// pasarTurno
				juego.pasarTurno();
				reiniciarBotones();
				botones = new HashMap<String, Posicion>();
				cargarBotones();
				return;
			}
			Posicion cancelarPosicion = botones.get("Cancelar");
			if (x >= cancelarPosicion.x()
					&& x <= cancelarPosicion.x() + anchoBotonMenu
					&& y >= altoPantalla - cancelarPosicion.y() - altoBotonMenu
					&& y <= altoPantalla - cancelarPosicion.y()) {// Cancelar
				reiniciarBotones();
				return;
			}
			Posicion moverPosicion = botones.get("Mover");
			if (mostrandoAcciones && x >= moverPosicion.x()
					&& x <= moverPosicion.x() + anchoBotonMenu
					&& y >= altoPantalla - moverPosicion.y() - altoBotonMenu
					&& y <= altoPantalla - moverPosicion.y()) {// mover
				apretoMover = true;
			}
			Posicion elevarPosicion = botones.get("Elevar");
			if (mostrandoAcciones && x >= elevarPosicion.x()
					&& x <= elevarPosicion.x() + anchoBotonMenu
					&& y >= altoPantalla - elevarPosicion.y() - altoBotonMenu
					&& y <= altoPantalla - elevarPosicion.y()) {// elevar
				try {
					if (anteriorTerrestre) {
						juego.elevar(filaAnterior, columnaAnterior);
					} else {
						juego.descender(filaAnterior, columnaAnterior);
					}
					anteriorTerrestre = !anteriorTerrestre;
				} catch (NoEsPosibleMoverException e) {
					crearError("No se puede mover");
				}
			}
			Posicion atacarPosicion = botones.get("Atacar");
			if (mostrandoAcciones && x >= atacarPosicion.x()
					&& x <= atacarPosicion.x() + anchoBotonMenu
					&& y >= altoPantalla - atacarPosicion.y() - altoBotonMenu
					&& y <= altoPantalla - atacarPosicion.y()) {// atacar
				apretoAtacar = true;
			}
			Posicion subirPosicion = botones.get("Subir");
			if (mostrandoAcciones && subirPosicion != null
					&& x >= subirPosicion.x()
					&& x <= subirPosicion.x() + anchoBotonMenu
					&& y >= altoPantalla - subirPosicion.y() - altoBotonMenu
					&& y <= altoPantalla - subirPosicion.y()) {// Subir
				apretoSubir = true;
			}
			Posicion bajarPosicion = botones.get("Bajar");
			if (mostrandoAcciones && bajarPosicion != null
					&& x >= bajarPosicion.x()
					&& x <= bajarPosicion.x() + anchoBotonMenu
					&& y >= altoPantalla - bajarPosicion.y() - altoBotonMenu
					&& y <= altoPantalla - bajarPosicion.y()) {// Bajar
				try {
					juego.bajarDelTransporte(filaAnterior, columnaAnterior);
				} catch (NoSePuedeBajarException e) {
					crearError("No se puede bajar");
				}
			}
			Posicion misilPosicion = botones.get("EMP");
			if (misilPosicion == null)
				misilPosicion = botones.get("TORMENTA");
			if (misilPosicion != null && x >= misilPosicion.x()
					&& x <= misilPosicion.x() + anchoBotonMenu
					&& y >= altoPantalla - misilPosicion.y() - altoBotonMenu
					&& y <= altoPantalla - misilPosicion.y()) {// Misil
				apretoMisil = true;
			}
			Posicion radiacionPosicion = botones.get("RADIACION");// Radiacion
			if (radiacionPosicion != null
					&& x >= radiacionPosicion.x()
					&& x <= radiacionPosicion.x() + anchoBotonMenu
					&& y >= altoPantalla - radiacionPosicion.y()
							- altoBotonMenu
					&& y <= altoPantalla - radiacionPosicion.y()) {//
				apretoRadiacion = true;
			}
			Posicion alucinacionPosicion = botones.get("ALUCINACION");// Alucinacion
			if (alucinacionPosicion != null
					&& x >= alucinacionPosicion.x()
					&& x <= alucinacionPosicion.x() + anchoBotonMenu
					&& y >= altoPantalla - alucinacionPosicion.y()
							- altoBotonMenu
					&& y <= altoPantalla - alucinacionPosicion.y()) {//
				try {
					juego.ataqueAlucinacion(filaAnterior, columnaAnterior);
				} catch (Exception e) {
					crearError("No se puede crear otra alucinacion");
				}
			}
			Posicion barracaPosicion = botones.get("Barraca");
			if (barracaPosicion == null)
				barracaPosicion = botones.get("Acesso");
			if (x >= barracaPosicion.x()
					&& x <= barracaPosicion.x() + anchoBotonMenu
					&& y >= altoPantalla - barracaPosicion.y() - altoBotonMenu
					&& y <= altoPantalla - barracaPosicion.y()) {// crearBarraca
				edificioCrear = "Barraca";
			}
			Posicion fabricaPosicion = botones.get("Fabrica");
			if (fabricaPosicion == null)
				fabricaPosicion = botones.get("Archivos Templarios");
			if (x >= fabricaPosicion.x()
					&& x <= fabricaPosicion.x() + anchoBotonMenu
					&& y >= altoPantalla - fabricaPosicion.y() - altoBotonMenu
					&& y <= altoPantalla - fabricaPosicion.y()) {// crearBarraca
				edificioCrear = "Fabrica";
			}
			Posicion puertoEstelarPosicion = botones.get("Puerto Estelar");
			if (x >= puertoEstelarPosicion.x()
					&& x <= puertoEstelarPosicion.x() + anchoBotonMenu
					&& y >= altoPantalla - puertoEstelarPosicion.y()
							- altoBotonMenu
					&& y <= altoPantalla - puertoEstelarPosicion.y()) {// crearBarraca
				edificioCrear = "Puerto Estelar";
			}
			Posicion depositoPosicion = botones.get("Deposito Suministro");
			if (depositoPosicion == null)
				depositoPosicion = botones.get("Pilon");
			if (x >= depositoPosicion.x()
					&& x <= depositoPosicion.x() + anchoBotonMenu
					&& y >= altoPantalla - depositoPosicion.y() - altoBotonMenu
					&& y <= altoPantalla - depositoPosicion.y()) {// crearBarraca
				edificioCrear = "Deposito Suministro";
			}
			Posicion refineriaPosicion = botones.get("Refineria");
			if (refineriaPosicion == null)
				refineriaPosicion = botones.get("Asimilador");
			if (x >= refineriaPosicion.x()
					&& x <= refineriaPosicion.x() + anchoBotonMenu
					&& y >= altoPantalla - refineriaPosicion.y()
							- altoBotonMenu
					&& y <= altoPantalla - refineriaPosicion.y()) {// crearBarraca
				edificioCrear = "Refineria";
			}
			Posicion centroPosicion = botones.get("Centro de Mineral");
			if (centroPosicion == null)
				centroPosicion = botones.get("Nexo Mineral");
			if (x >= centroPosicion.x()
					&& x <= centroPosicion.x() + anchoBotonMenu
					&& y >= altoPantalla - centroPosicion.y() - altoBotonMenu
					&& y <= altoPantalla - centroPosicion.y()) {// crearBarraca
				edificioCrear = "CentroMineral";
			}

			// UNIDADES
			Posicion marinePosicion = botones.get("Marine");
			if (marinePosicion != null && x >= marinePosicion.x()
					&& x <= marinePosicion.x() + anchoBotonMenu
					&& y >= altoPantalla - marinePosicion.y() - altoBotonMenu
					&& y <= altoPantalla - marinePosicion.y()) {
				apretoCrear = "Marine";
			}
			Posicion zealotPosicion = botones.get("Zealot");
			if (zealotPosicion != null && x >= zealotPosicion.x()
					&& x <= zealotPosicion.x() + anchoBotonMenu
					&& y >= altoPantalla - zealotPosicion.y() - altoBotonMenu
					&& y <= altoPantalla - zealotPosicion.y()) {
				apretoCrear = "Zealot";

			}
			Posicion golliatPosicion = botones.get("Golliat");
			if (golliatPosicion != null && x >= golliatPosicion.x()
					&& x <= golliatPosicion.x() + anchoBotonMenu
					&& y >= altoPantalla - golliatPosicion.y() - altoBotonMenu
					&& y <= altoPantalla - golliatPosicion.y()) {
				apretoCrear = "Golliat";

			}
			Posicion espectroPosicion = botones.get("Espectro");
			if (espectroPosicion != null && x >= espectroPosicion.x()
					&& x <= espectroPosicion.x() + anchoBotonMenu
					&& y >= altoPantalla - espectroPosicion.y() - altoBotonMenu
					&& y <= altoPantalla - espectroPosicion.y()) {
				apretoCrear = "Espectro";

			}
			Posicion naveCienciaPosicion = botones.get("Nave Ciencia");
			if (naveCienciaPosicion != null
					&& x >= naveCienciaPosicion.x()
					&& x <= naveCienciaPosicion.x() + anchoBotonMenu
					&& y >= altoPantalla - naveCienciaPosicion.y()
							- altoBotonMenu
					&& y <= altoPantalla - naveCienciaPosicion.y()) {
				apretoCrear = "NaveCiencia";

			}
			Posicion naveTransportePosicion = botones
					.get("Nave Transporte Terran");
			if (naveTransportePosicion != null
					&& x >= naveTransportePosicion.x()
					&& x <= naveTransportePosicion.x() + anchoBotonMenu
					&& y >= altoPantalla - naveTransportePosicion.y()
							- altoBotonMenu
					&& y <= altoPantalla - naveTransportePosicion.y()) {
				apretoCrear = "NaveTransporteTerran";

			}
			naveTransportePosicion = botones.get("Nave Transporte Protoss");
			if (naveTransportePosicion != null
					&& x >= naveTransportePosicion.x()
					&& x <= naveTransportePosicion.x() + anchoBotonMenu
					&& y >= altoPantalla - naveTransportePosicion.y()
							- altoBotonMenu
					&& y <= altoPantalla - naveTransportePosicion.y()) {
				apretoCrear = "NaveTransporteProtoss";

			}
			Posicion dragonPosicion = botones.get("Dragon");
			if (dragonPosicion != null && x >= dragonPosicion.x()
					&& x <= dragonPosicion.x() + anchoBotonMenu
					&& y >= altoPantalla - dragonPosicion.y() - altoBotonMenu
					&& y <= altoPantalla - dragonPosicion.y()) {
				apretoCrear = "Dragon";

			}
			Posicion scoutPosicion = botones.get("Scout");
			if (scoutPosicion != null && x >= scoutPosicion.x()
					&& x <= scoutPosicion.x() + anchoBotonMenu
					&& y >= altoPantalla - scoutPosicion.y() - altoBotonMenu
					&& y <= altoPantalla - scoutPosicion.y()) {
				apretoCrear = "Scout";

			}
			Posicion templarioPosicion = botones.get("Alto Templario");
			if (templarioPosicion != null
					&& x >= templarioPosicion.x()
					&& x <= templarioPosicion.x() + anchoBotonMenu
					&& y >= altoPantalla - templarioPosicion.y()
							- altoBotonMenu
					&& y <= altoPantalla - templarioPosicion.y()) {
				apretoCrear = "AltoTemplario";

			}
		}

	}

	private void crearError(String error) {
		contMensaje = 100;
		mensaje = error;
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
			mostrandoAcciones = true;
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

			Elemento bajar = new Elemento("Bajar");
			bajar.setColorDibujable(new ColorDibujable(1, 1, 1));
			menu.put(botones.get("Bajar"), bajar);

		} else {
			mostrandoAcciones = false;
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
		int xInicial = (int) (botones.get("Bajar").x() + anchoCuadrado * 2 + altoPantalla / 100);
		for (AtaqueMagico ataque : magias) {
			Elemento ele = new Elemento(ataque.toString());
			ele.setColorDibujable(new ColorDibujable(1, 1, 1));
			menu.put(new Posicion(xInicial, (int) botones.get("Subir").y()),
					ele);
			botones.put(ataque.toString(), new Posicion(xInicial, (int) botones
					.get("Bajar").y()));
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
		palabras.put(
				new Posicion((int) (anchoPantalla - anchoPantalla / 4),
						(int) (altoPantalla - 2 * altoPantalla / 15)),
				"Poblacion: " + juego.poblacionActual() + " / "
						+ juego.limitePoblacionActual());
		if (apretoMover || apretoSubir) {
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
				boolean rta;
				if (anteriorTerrestre) {
					rta = juego.moverPosicionTerrestre(filaAnterior,
							columnaAnterior, fila, columna);
				} else {
					rta = juego.moverPosicionAereo(filaAnterior,
							columnaAnterior, fila, columna);
				}
				if (!rta) {
					crearError("No se pudo mover");
				}
			}
			filaAnterior = 50;
			columnaAnterior = 50;

		}

	}

	public double altoBotonMenu() {
		return altoBotonMenu;
	}

	public double anchoBotonMenu() {
		return anchoBotonMenu;
	}

	private void atacar(int fila, int columna,boolean terrestre) {
		Ser unSer = juego.queHayEnCeldaTerrestre(fila, columna);

		if (filaAnterior == 50) {
			filaAnterior = fila;
			columnaAnterior = columna;
		} else {
			if (filaAnterior != 50) {
				if(terrestre && anteriorTerrestre){
					if (!juego.atacarTierra(filaAnterior, columnaAnterior, fila,
						columna)) {
						crearError("No se pudo realizar el ataque terrestre");
					}
				}else if(anteriorTerrestre && !terrestre){
					if (!juego.atacarAire(filaAnterior, columnaAnterior, fila,
							columna)) {
							crearError("No se pudo realizar el ataque aereo");
					}
				}
			
			}
			filaAnterior = 50;
			columnaAnterior = 50;

		}
	}

	public HashMap<PosicionDibujable, Elemento> GrillaADibujar() {
		cargarBotones();
		HashMap<Posicion, Elemento> grillaResueltaTerrestre = Codificador
				.grillaResuelta(juego.grillaColorUnidadTerrestre());
		HashMap<Posicion, Elemento> grillaResueltaAereo = Codificador
				.grillaResuelta(juego.grillaColorUnidadAerea());
		HashMap<PosicionDibujable, Elemento> grillaFinal = new HashMap<PosicionDibujable, Elemento>();
		double largo = (double) Math.sqrt(grillaResueltaTerrestre.keySet()
				.size());
		largoMapa = (int)largo - 1;
		anchoCuadrado = (anchoPantalla) / largo / 2;
		altoCuadrado = (altoPantalla - altoMenu) / largo;
		for (Posicion pos : grillaResueltaTerrestre.keySet()) {
			grillaFinal.put(new PosicionDibujable(anchoCuadrado * pos.x(),
					altoCuadrado * pos.y()), grillaResueltaTerrestre.get(pos));
		}
		for (Posicion pos : grillaResueltaAereo.keySet()) {
			grillaFinal.put(new PosicionDibujable(
					((anchoCuadrado * pos.x()) + anchoPantalla / 2),
					altoCuadrado * pos.y()), grillaResueltaAereo.get(pos));
		}
		if (juego.hayGanador())
			ganador = juego.JugadorActual();
		return grillaFinal;
	}

	public ColorDibujable ColorActual() {
		return Codificador.obtenerColor(juego.ColorActual());
	}

	public double anchoCuadrado() {
		return anchoCuadrado;
	}

	public double altoCuadrado() {
		return altoCuadrado;
	}

}