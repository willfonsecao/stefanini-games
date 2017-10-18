package br.com.stefanini.games.stefaninigames.api.dto.response;

import java.io.Serializable;

public class CampeaoDTOResponse implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public CampeaoDTOResponse() {}

	private Long vitorias;
	private Long id;
	private String nome;

	public Long getVitorias() {
		return vitorias;
	}

	public void setVitorias(Long vitorias) {
		this.vitorias = vitorias;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
