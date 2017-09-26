package br.com.stefanini.games.stefaninigamesapi.enumarated;

public enum SimNaoEnum {

	S("Sim"), N("Não");

	private String valor;

	private SimNaoEnum(String valor) {
		this.valor = valor;
	}

	public String getChave() {
		return name();
	}

	public String getValor() {
		return valor;
	}

}
