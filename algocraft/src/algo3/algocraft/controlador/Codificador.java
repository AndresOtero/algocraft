package algo3.algocraft.controlador;

import java.util.HashMap;

import algo3.algocraft.Color;
import algo3.algocraft.Posicion;

public class Codificador {
	public static HashMap<Posicion,Elemento> grillaResuelta(HashMap<Posicion,int[]> grillaSinResolver){
		HashMap<Posicion,Elemento> resuelta = new HashMap<Posicion, Elemento>();
		for(Posicion pos : grillaSinResolver.keySet()){
			Elemento elemento = null;
			int ID = grillaSinResolver.get(pos)[1];
			int IDColor = grillaSinResolver.get(pos)[0];
			elemento = obtenerElemento(ID, IDColor);
			
			resuelta.put(pos, elemento);
		}
		return resuelta;
			

	}

	public  static Elemento obtenerElemento(int ID, int IDColor) {
		Elemento elemento = null;
		switch (ID) {
		case 0:
			elemento = new Elemento("CentroMineral");
			break;
		case 1:
			elemento = new Elemento("Barraca");
			break;
		case 2:
			elemento = new Elemento("DepositoSuministro");
			break;
		case 3:
			elemento = new Elemento("Refineria");
			break;
		case 4:
			elemento = new Elemento("Fabrica");
			break;
		case 5:
			elemento = new Elemento("PuertoEstelarTerran");
			break;
		case 6:
			elemento = new Elemento("NexoMineral");
			break;
		case 7:
			elemento = new Elemento("Pilon");
			break;
		case 8:
			elemento = new Elemento("Asimilador");
			break;
		case 9:
			elemento = new Elemento("Acceso");
			break;
		case 10:
			elemento = new Elemento("PuertoEstelarProtoss");
			break;
		case 11:
			elemento = new Elemento("ArchivoTemplario");
			break;
		case 12:
			elemento = new Elemento("Marine");
			break;
		case 13:
			elemento = new Elemento("Golliat");
			break;
		case 14:
			elemento = new Elemento("Espectro");
			break;
		case 15:
			elemento = new Elemento("NaveCiencia");
			break;
		case 16:
			elemento = new Elemento("NaveTransporteTerran");
			break;
		case 17:
			elemento = new Elemento("Zealot");
			break;
		case 18:
			elemento = new Elemento("Dragon");
			break;
		case 19:
			elemento = new Elemento("Scout");
			break;
		case 20:
			elemento = new Elemento("AltoTemplario");
			break;
		case 21:
			elemento = new Elemento("NaveTransporteProtoss");
			break;
		case 22:
			elemento = new Elemento("Mineral");
			break;
		case 23:
			elemento = new Elemento("Gas");
			break;
		case 24:
			elemento = new Elemento("EdificioEnConstruccion");
			break;
		case 25:
			elemento = new Elemento("Pasto");
			break;
		case 26:
			elemento = new Elemento("Pasto");
			break;

			
		}
		switch (IDColor) {
		case 0 :
			elemento.setColorDibujable(obtenerColor(Color.ROJO));
		case 1 :
			elemento.setColorDibujable(obtenerColor(Color.AZUL));
		case 2 :
			elemento.setColorDibujable(obtenerColor(Color.VERDE));
		case 3 :
			elemento.setColorDibujable(obtenerColor(Color.AMARILLO));
		//case 4 :
			//elemento.setColorDibujable(new ColorDibujable(1, 1, 1));
		}
		return elemento;
	}
	
	public static ColorDibujable obtenerColor(Color color){
		ColorDibujable colorDibujar = null;
		switch (color) {
		case ROJO:
			colorDibujar = new ColorDibujable(1, 0, 0);
			break;
		case AMARILLO:
			colorDibujar = new ColorDibujable(1, 0, 1);
			break;
		case VERDE:
			colorDibujar = new ColorDibujable(0, 0, 1);
			break;
		case AZUL:
			colorDibujar = new ColorDibujable(0, 1, 0);
			break;
		}
		
		return colorDibujar;
	}

}
