package algo3.algocraft;

import java.util.ArrayList;

public class Posicion {

	private int x;
	private int y;

	public Posicion(int x, int y) {
		this.x = x;
		this.y = y;
	}
	public int x(){
		return x;
	}
	public int y(){
		return y;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Posicion other = (Posicion) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}

	public int distancia(Posicion destino) {
		return (Math.abs(x-destino.x)+Math.abs(y-destino.y));
	}

}
