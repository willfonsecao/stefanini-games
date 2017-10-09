package br.com.stefanini.games.stefaninigames.api.dto.response;

import java.io.Serializable;

import br.com.stefanini.games.stefaninigamesapi.model.JogoEtapa;

public class JogosEtapaDTOResponse implements Serializable{

	private static final long serialVersionUID = 1L;
	
	public JogosEtapaDTOResponse() {}

	public JogosEtapaDTOResponse(JogoEtapa jogoEtapa) {
		this.id = jogoEtapa.getId();
		this.jogo = new JogoDTOResponse(jogoEtapa.getJogo());
	}
	
	private Long id;
	private JogoDTOResponse jogo;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public JogoDTOResponse getJogo() {
		return jogo;
	}

	public void setJogo(JogoDTOResponse jogo) {
		this.jogo = jogo;
	}
	
}
