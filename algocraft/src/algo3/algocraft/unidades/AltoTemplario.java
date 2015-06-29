package algo3.algocraft.unidades;

import java.util.ArrayList;

import algo3.algocraft.*;

public class AltoTemplario extends UnidadMagica implements AltoTemplarioInteface {
	private int transporte = 2;

	public AltoTemplario(Color colorJugador,Posicion pos) {
		this.posicion=pos;
		vida = 40;
		escudo = 40;
		suministro = 2;
		tiempoDeConstruccion = 7;
		vision = 7;
		this.color = colorJugador;
		movimiento = Movimiento.Terrestre;
		energia = energiaInicial;
		this.id = 20;
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

	public ArrayList<AltoTemplarioInteface> alucinacion() {
		ArrayList<AltoTemplarioInteface> listaDeProxys=new ArrayList<AltoTemplarioInteface>();
		for(int i=0;i<2;i++){
			AltoTemplarioProxy proxy=new AltoTemplarioProxy(this.color,this.posicion);
			listaDeProxys.add(proxy);
		}
		return listaDeProxys;		
	}
	@Override
	public int devolverID() {
		return this.id;
		}
	
	@Override
	
	public AtaqueMagico[] devolverAtaques(){
		AtaqueMagico[] ataques = {AtaqueMagico.TORMENTA,AtaqueMagico.ALUCINACION};
		return ataques;
	
	}
}
