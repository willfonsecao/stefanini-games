package br.com.stefanini.games.stefaninigamesapi.enumarated;

public enum EtapasEnum {
	
	ETAPA_1(1, "1ª Etapa"),
	ETAPA_2(2, "2ª Etapa"),
	OITAVAS(3, "Oitavas"),
	QUARTAS(4, "Quartas"),
	SEMI_FINAL(5, "Semi-Final"),
	FINAL(6, "Final"),
	TERCEIRO_LUGAR(7, "Terceiro Lugar");
	
	private int id;
	private String descricao;
	
	private EtapasEnum(int id, String descricao) {
		this.id = id;
		this.descricao = descricao;
	}
	
	public static EtapasEnum getEtapa(int codigoEtapa){
		for (EtapasEnum etapa : values()) {
			if(codigoEtapa == etapa.getId()){
				return etapa;
			}
		}
		return null;
	}

	public static EtapasEnum getEtapa(String nomeEtapa){
		for (EtapasEnum etapa : values()) {
			if(nomeEtapa == etapa.getDescricao()){
				return etapa;
			}
		}
		return null;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	

}
