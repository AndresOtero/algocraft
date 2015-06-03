package algo3.algocraft;

public class Turnos {
	
	private Jugador[] jugadores = new Jugador[2];
	
	private int turno;
	
	Turnos(Jugador jugador1,Jugador jugador2){
		jugadores[0] = jugador1;
		jugadores[1] = jugador2;
		turno = 0;
	}
	
	public Jugador turnoActual(){
		return jugadores[((turno%2) == 0)? 1 : 0]; //magia oscura
	}
	
	public void avanzarTurno(){
		turno++;
	}
	
}

