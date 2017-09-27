package br.com.stefanini.games.stefaninigamesapi.enumarated;

public enum Meses {
	
	JANEIRO(1, "Janeiro"),
	FEVEREIRO(2, "Fevereiro"),
	MARCO(3, "Março"),
	ABRIL(4, "Abril"),
	MAIO(5, "Maio"),
	JUNHO(6, "Junho"),
	JULHO(7, "Julho"),
	AGOSTO(8, "Agosto"),
	SETEMBRO(9, "Setembro"),
	OUTUBRO(10, "Outubro"),
	NOVEMBRO(11, "Novembro"),
	DEZEMBRO(11, "Dezembro");
	
	private int id;
	private String descricao;
	
	private Meses(int id, String descricao) {
		this.id = id;
		this.descricao = descricao;
	}
	
	public static String getMes(int numeroMes){
		for (Meses mes : values()) {
			if(numeroMes == mes.getId()){
				return mes.getDescricao();
			}
		}
		return "-";
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
