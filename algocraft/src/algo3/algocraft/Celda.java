package algo3.algocraft;

public class Celda {

	private FuenteRecurso recurso;
	private Ser serTerrestre;
	private Ser serAereo;
	private boolean visible;

	public Celda() {
		this.recurso = null;
		this.serAereo = null;
		this.serTerrestre = null;
	}

	public boolean esVisible() {
		return visible;
	}

	public void cambiarVisibilidad(boolean nuevaVisibilidad) {
		visible = nuevaVisibilidad;
	}

	public boolean ocupadoTerrestre() {
		return (this.serTerrestre != null && this.recurso != null);
	}

	public boolean ocupadoAerea() {
		return (this.serAereo != null);
	}

	public Ser serEnLaCeldaTerrestre() {
		return this.serTerrestre;
	}

	public Ser serEnLaCeldaAerea() {
		return this.serAereo;
	}

	public boolean agregarSerAereo(Ser ser) {
		if (this.serAereo == null) {
			this.serAereo = ser;
			return true;
		}
		return false;
	}

	public boolean agregarSerTerrestre(Ser ser) {
		if (this.serTerrestre == null) { /*
										 * IMPORTANTE : PODRIAMOS ESTAR
										 * AGREGANDO UN SER Y UN RECURSO EN LA
										 * MISMA CELDA TENDRIAMOS QUE VALIDAR
										 * QUE SOLO ( SOLO ) PUEDE AGREGARSE UN
										 * SER EDIFICIO REFINERIA O MINERALERO
										 * EN UN LUGAR DONDE YA HAYA UN RECURSO
										 * > Jc
										 */
			this.serTerrestre = ser;
			return true;
		}
		return false;

	}

	public FuenteRecurso fuenteRecurso() {
		return recurso;
	}

	public void agregarFuenteRecurso(FuenteRecurso fuenteRecurso) {
		recurso = fuenteRecurso;
	}

	public void desocuparTerrestre() {
		serTerrestre = null;
	}

	public void desocuparAerea() {
		serAereo = null;
	}
}
