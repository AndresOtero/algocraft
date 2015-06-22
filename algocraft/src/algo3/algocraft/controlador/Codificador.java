package algo3.algocraft.controlador;

import java.util.HashMap;

import algo3.algocraft.Posicion;

public class Codificador {
	public static HashMap<Posicion,String> grillaResuelta(HashMap<Posicion,int[]> grillaSinResolver){
		HashMap<Posicion,String> resuelta = new HashMap<Posicion, String>();
		for(Posicion pos : grillaSinResolver.keySet()){
			String elemento = null;
			switch (grillaSinResolver.get(pos)[0]) {
			case 0:
				elemento = "CentroMineral";
			case 1:
				elemento = "Barraca";
			case 2:
				elemento = "DepositoSuministro";
			case 3:
				elemento = "Refineria";
			case 4:
				elemento = "Fabrica";
			case 5:
				elemento = "PuertoEstelarTerran";
			case 6:
				elemento = "NexoMineral";
			case 7:
				elemento = "Pilon";
			case 8:
				elemento = "Asimilador";
			case 9:
				elemento = "Acesso";
			case 10:
				elemento = "PuertoEstelarProtoss";
			case 11:
				elemento = "ArchivoTemplario";
			case 12:
				elemento = "Marine";
			case 13:
				elemento = "Golliat";
			case 14:
				elemento = "Espectro";
			case 15:
				elemento = "NaveCiencia";
			case 16:
				elemento = "NaveTransporteTerran";
			case 17:
				elemento = "Zealot";
			case 18:
				elemento = "Dragon";
			case 19:
				elemento = "Scout";
			case 20:
				elemento = "AltoTemplario";
			case 21:
				elemento = "NaveTransporteProtoss";
			case 22:
				elemento = "Mineral";
			case 23:
				elemento = "Gas";
			case 24:
				elemento = "EdificioEnConstruccion";
			case 25:
				elemento = "Pasto";
			case 26:
				elemento = "Aire";
				
			}
			switch (grillaSinResolver.get(pos)[1]) {
			case 0 :
				elemento += "Rojo";
			case 1 :
				elemento += "Azul";
			case 2 :
				elemento += "Amarillo";
			case 3 :
				elemento += "Verde";
			case 4 :
				elemento += "Recurso";
			}
			resuelta.put(pos, elemento);
		}
		return resuelta;
			

	}

}
