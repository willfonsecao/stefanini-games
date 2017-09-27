package br.com.stefanini.games.stefaninigamesapi.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "ETAPA")
public class Etapa implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name ="ID_CAMPEONATO")
	private Campeonato campeonato;
	
	@Column(name = "NOME")
	private String nome;
	
	@Column(name = "DATA")
	private Date data;
	
	@OneToMany(mappedBy="etapa")
	private Set<JogoEtapa> jogosEtapas = new HashSet<>();
	
	public Set<JogoEtapa> getJogosEtapas() {
		return jogosEtapas;
	}

	public void setJogosEtapas(Set<JogoEtapa> jogosEtapas) {
		this.jogosEtapas = jogosEtapas;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Campeonato getCampeonato() {
		return campeonato;
	}

	public void setCampeonato(Campeonato campeonato) {
		this.campeonato = campeonato;
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

}
