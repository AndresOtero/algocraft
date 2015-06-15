package algo3.algocraft.unidades;

import java.util.ArrayList;

import algo3.algocraft.Color;
import algo3.algocraft.Movimiento;
import algo3.algocraft.Posicion;

public class AltoTemplarioProxy extends AltoTemplario implements AltoTemplarioInteface{

	public AltoTemplarioProxy(Color colorJugador,Posicion pos) {
		super(colorJugador, pos);
		this.posicion=pos;
		vida = 40;
		tiempoDeConstruccion=0;
		energia=0;
	}
	@Override
	public boolean tormenta(ArrayList<Unidad> atacados){
		return false;
	} 
	@Override
	public ArrayList<AltoTemplarioInteface> alucinacion() {
		return null;
	}
	@Override
	public void aumentarEnergia() {
	}
	@Override
	public boolean ataqueRadio(ArrayList<Unidad> atacados) {
		return false;
	}
	



}
