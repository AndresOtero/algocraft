package algo3.algocraft;

import java.util.ArrayList;

public class Turnos {
	
	
	private Jugador[] jugadores = new Jugador[2];
	
	private int turno;
	
	Turnos(ArrayList<Jugador> jugadores){
		this.jugadores[0] = jugadores.get(0);
		this.jugadores[1] = jugadores.get(1);
		turno = 0;
	}
	
	public Jugador turnoActual(){
		return jugadores[((turno%2) == 0)? 1 : 0]; //magia oscura
	}
	
	public void avanzarTurno(){
		turno++;
	}
	

	
}

