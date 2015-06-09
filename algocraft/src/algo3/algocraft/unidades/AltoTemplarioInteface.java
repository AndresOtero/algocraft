package algo3.algocraft.unidades;

import java.util.ArrayList;

public interface AltoTemplarioInteface extends Terrestre,Transportable {
	public boolean tormenta(ArrayList<Unidad> atacados) ;
	public ArrayList<AltoTemplarioInteface> alucinacion();
	public void aumentarEnergia() ;
	public boolean ataqueRadio(ArrayList<Unidad> atacados) ;


}
