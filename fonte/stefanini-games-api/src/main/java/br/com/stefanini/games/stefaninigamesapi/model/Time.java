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
@Table(name = "TIME")
public class Time implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="PONTUACAO")
	private Integer pontuacao;
	
	@OneToMany(mappedBy="time")
	private Set<Usuario> usuarios = new HashSet<>();
	
	@OneToMany(mappedBy="time1")
	private Set<Jogo> jogosTime1 = new HashSet<>();

	@OneToMany(mappedBy="time2")
	private Set<Jogo> jogosTime2 = new HashSet<>();

	@OneToMany(mappedBy="vencedor")
	private Set<Jogo> jogosVencidos = new HashSet<>();
	
	@OneToMany(mappedBy="perdedor")
	private Set<Jogo> jogosPerdidos = new HashSet<>();
	
	public Set<Jogo> getJogosTime1() {
		return jogosTime1;
	}

	public void setJogosTime1(Set<Jogo> jogosTime1) {
		this.jogosTime1 = jogosTime1;
	}

	public Set<Jogo> getJogosTime2() {
		return jogosTime2;
	}

	public void setJogosTime2(Set<Jogo> jogosTime2) {
		this.jogosTime2 = jogosTime2;
	}

	public Set<Jogo> getJogosVencidos() {
		return jogosVencidos;
	}

	public void setJogosVencidos(Set<Jogo> jogosVencidos) {
		this.jogosVencidos = jogosVencidos;
	}

	public Set<Jogo> getJogosPerdidos() {
		return jogosPerdidos;
	}

	public void setJogosPerdidos(Set<Jogo> jogosPerdidos) {
		this.jogosPerdidos = jogosPerdidos;
	}

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

	public Set<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(Set<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
	
}
