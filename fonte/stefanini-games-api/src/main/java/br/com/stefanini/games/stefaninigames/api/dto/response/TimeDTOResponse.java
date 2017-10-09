package br.com.stefanini.games.stefaninigames.api.dto.response;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import br.com.stefanini.games.stefaninigamesapi.model.Time;

public class TimeDTOResponse implements Serializable{

	private static final long serialVersionUID = 1L;
	
	
	public TimeDTOResponse(Time time) {
		this.id = time.getId();
		this.pontuacao = time.getPontuacao();
		this.jogadores = time.getJogadores().stream().map((jogador)-> new JogadorDTOResponse(jogador)).collect(Collectors.toSet());
	}
	
	private Long id;
	private Integer pontuacao;
	private Set<JogadorDTOResponse> jogadores = new HashSet<>();
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getPontuacao() {
		return pontuacao;
	}
	public void setPontuacao(Integer pontuacao) {
		this.pontuacao = pontuacao;
	}
	public Set<JogadorDTOResponse> getJogadores() {
		return jogadores;
	}
	public void setJogadores(Set<JogadorDTOResponse> jogadores) {
		this.jogadores = jogadores;
	}
	
}
