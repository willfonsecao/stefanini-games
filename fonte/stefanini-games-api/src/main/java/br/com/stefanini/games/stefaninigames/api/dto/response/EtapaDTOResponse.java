package br.com.stefanini.games.stefaninigames.api.dto.response;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import br.com.stefanini.games.stefaninigamesapi.model.Etapa;

public class EtapaDTOResponse implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public EtapaDTOResponse() {}

	public EtapaDTOResponse(Etapa etapa) {
		this.id = etapa.getId();
		this.nome = etapa.getNome();
		this.data = etapa.getData();
		this.jogosEtapas = etapa.getJogosEtapas().stream().map((jogoEtapa)-> new JogosEtapaDTOResponse(jogoEtapa)).collect(Collectors.toSet());
	}
	
	private Long id;
	private String nome;
	private Date data;
	private Set<JogosEtapaDTOResponse> jogosEtapas = new HashSet<>();

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
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}

	public Set<JogosEtapaDTOResponse> getJogosEtapas() {
		return jogosEtapas;
	}

	public void setJogosEtapas(Set<JogosEtapaDTOResponse> jogosEtapas) {
		this.jogosEtapas = jogosEtapas;
	}

}
