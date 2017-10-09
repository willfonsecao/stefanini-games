package br.com.stefanini.games.stefaninigames.api.dto.response;

import java.io.Serializable;

import br.com.stefanini.games.stefaninigamesapi.model.Jogador;
import br.com.stefanini.games.stefaninigamesapi.model.Usuario;

public class JogadorDTOResponse implements Serializable{

	private static final long serialVersionUID = 1L;
	
	public JogadorDTOResponse() {}
	
	public JogadorDTOResponse(Jogador jogador) {
		this.id = jogador.getId();
		this.usuario = jogador.getUsuario();
	}

	private Long id;
	private Usuario usuario;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	
}
