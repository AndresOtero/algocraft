package algo3.algocraft.unidades;

import java.util.ArrayList;

import algo3.algocraft.AtaqueMagico;


public abstract class UnidadMagica extends Unidad {
	protected int energia;
	protected int energiaInicial = 50;


	public abstract void aumentarEnergia();

	public abstract boolean ataqueRadio(ArrayList<Unidad> atacados);
	
	public void recibirEmp(){
		this.energia = 0;
		
	}

	public abstract AtaqueMagico[] devolverAtaques();

}