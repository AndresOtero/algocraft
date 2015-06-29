package algo3.algocraft.controlador;

public enum Id {
	CentroMineral(0) ,Barraca(1) ,DepositoSuministro(2),Refineria(3),Fabrica(4),PuertoEstelarTerran(5),
	NexoMineral(6),Pilon(7),Asimilador(8),Acceso(9),PuertoEstelarProtoss(10),ArchivoTemplario(11),
	Marine(12),Golliat(13),	Espectro(14),NaveCiencia(15),NaveTransporteTerran(16),Zealot(17),Dragon(18),
	Scout(19),AltoTemplario(20),NaveTransporteProtoss(21),Mineral(22),Gas(23),EdificioEnConstruccion(24),
	Pasto1(25),Pasto2(26),Negro(27);
	private int numero;
	private Id(int id){
		numero=id;
	};
	public int numero(){
		return numero;
	}
}
