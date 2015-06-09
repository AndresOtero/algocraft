package algo3.algocraft.unidades;

import java.util.ArrayList;

import algo3.algocraft.*;

public class AltoTemplario extends UnidadMagica implements Terrestre,
		Transportable {
	private int transporte = 2;

	public AltoTemplario(Color colorJugador) {
		vida = 40;
		escudo = 40;
		suministro = 2;
		tiempoDeConstruccion = 7;
		vision = 7;
		this.color = colorJugador;
		movimiento = Movimiento.Terrestre;
		energia = energiaInicial;
	}

	public boolean tormenta(ArrayList<Unidad> atacados) {
		if (energia > 75) {
			energia -= 75;
			for (int i = 0; i < atacados.size(); i++) {
				atacados.get(i).recibirTormenta();
			}
			return true;
		}

		return false;
	}

	public void alucinacion() {

	}

	@Override
	public void aumentarEnergia() {
		if (energia <= 190)
			energia = energia + 15;
	}

	@Override
	public boolean ataqueRadio(ArrayList<Unidad> atacados) {
		return tormenta(atacados);

	}

	@Override
	public int transporte() {
		return transporte;
	}
}
