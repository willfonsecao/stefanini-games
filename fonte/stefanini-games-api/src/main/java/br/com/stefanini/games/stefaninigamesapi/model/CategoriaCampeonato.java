package br.com.stefanini.games.stefaninigamesapi.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "CATEGORIA_CAMPEONATO")
public class CategoriaCampeonato implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="NOME")
	private String nome;
	
	@Column(name="LOGO")
	private byte[] logo;
	
	@OneToMany(mappedBy = "categoria" )
	private Set<Campeonato> campeonatos = new HashSet<>();
	
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
