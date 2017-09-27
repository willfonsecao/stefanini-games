package br.com.stefanini.games.stefaninigames.api.dto.response;

import java.util.HashSet;
import java.util.Set;

import br.com.stefanini.games.stefaninigamesapi.model.Campeonato;
import br.com.stefanini.games.stefaninigamesapi.model.CategoriaCampeonato;

public class CategoriaCampeonatoDTOResponse {

	private Long id;
	private String nome;
	private byte[] logo;
	private Set<Campeonato> campeonatos = new HashSet<>();
	
	public CategoriaCampeonatoDTOResponse() {}
	
	public CategoriaCampeonatoDTOResponse(CategoriaCampeonato categoria) {
		this.id = categoria.getId();
		this.nome = categoria.getNome();
		this.logo = categoria.getLogo();
		this.campeonatos = categoria.getCampeonatos();
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
	public byte[] getLogo() {
		return logo;
	}
	public void setLogo(byte[] logo) {
		this.logo = logo;
	}
	public Set<Campeonato> getCampeonatos() {
		return campeonatos;
	}
	public void setCampeonatos(Set<Campeonato> campeonatos) {
		this.campeonatos = campeonatos;
	}
	
}
