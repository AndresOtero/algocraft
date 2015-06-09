package algo3.algocraft.unidades;

import java.util.ArrayList;

import algo3.algocraft.Color;
import algo3.algocraft.Movimiento;

public class NaveCiencia extends UnidadMagica implements Aerea {

	public NaveCiencia(Color colorJugador) {
		vision = 10;
		suministro = 2;
		vida = 200;
		this.color = colorJugador;
		energia = energiaInicial;
		this.tiempoDeConstruccion = 10;
		movimiento=Movimiento.Aereo;
	}

	@Override
	public void aumentarEnergia() {
		if (energia <= 190)
		energia = energia + 10;
	}

	private boolean emp(ArrayList<Unidad> atacados) {
		if (energia > 100) {
			energia -= 100;
			for(int i = 0; i< atacados.size(); i++){
				atacados.get(i).recibirEmp();
			}
			return true;
		}
		return false;
	}
	
	public void radiacion(Unidad radioactivo) {
		radioactivo.recibirRadiacion();

	}

	@Override
	public boolean ataqueRadio(ArrayList<Unidad> atacados){
		return emp(atacados);
	}
}
