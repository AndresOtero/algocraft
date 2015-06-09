package algo3.algocraft;

import java.util.ArrayList;

import algo3.algocraft.unidades.Unidad;

public class Turnos {
	
	
	private Jugador[] jugadores = new Jugador[2];
	private ArrayList <Unidad> movidos = new ArrayList<Unidad>();
	private ArrayList <Unidad> atacaron = new ArrayList<Unidad>();
	
	
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
		movidos.clear();
		atacaron.clear();
	}

	public void agregarMovido(Unidad unidadAMover) {
		movidos.add(unidadAMover);
		
	}
	
	public void agregarQueAtaco( Unidad ataco){
		atacaron.add(ataco);
	}
	
	public boolean yaAtaco(Unidad unidadQueQuiereAtacar){
		return atacaron.contains( unidadQueQuiereAtacar);
	}
	
	public boolean yaSeMovio(Unidad unidadQueQuiereMoverse){
		return (movidos.contains( unidadQueQuiereMoverse) );
	}
	
	
}

