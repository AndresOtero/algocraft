package algo3.algocraft;

public class Celda {

	private FuenteRecurso recurso;
	private Ser serTerrestre;
	private Ser serAereo;

	public Celda() {
		this.recurso = null;
		this.serAereo = null;
		this.serTerrestre = null;
	}

	public boolean ocupadoTerrestre() {
		return (this.serTerrestre != null);
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
		if (this.serTerrestre == null ) { 
			this.serTerrestre = ser;
			return true;
		}
		return false;

	}

	public FuenteRecurso fuenteRecurso() {
		return recurso;
	}

	public boolean hayFuenteRecurso(){
		if ( recurso != null ) return true;
		return false;
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
