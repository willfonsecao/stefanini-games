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
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "CAMPEONATO")
public class Campeonato implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "DATA_INICIO")
	private Date dataInicio;

	@Column(name = "DATA_FIM")
	private Date dataFim;

	@Column(name = "DATA_INICIO_INSCRICAO")
	private Date dataInicioInscricoes;

	@Column(name = "DATA_FIM_INSCRICAO")
	private Date dataFimInscricoes;
	
	@OneToOne
	@JoinColumn(name = "ID_CAMPEAO")
	private Time campeao;

	@OneToOne
	@JoinColumn(name = "ID_SEGUNDO_COLOCADO")
	private Time segundoColocado;
	
	@OneToOne
	@JoinColumn(name = "ID_TERCEIRO_COLOCADO")
	private Time terceiroColocado;
	
	@ManyToOne
	@JoinColumn(name = "ID_CATEGORIA_CAMPEONATO")
	private CategoriaCampeonato categoria;
	
	@Column(name = "PREMIO_CAMPEAO")
	private String premioCampeao;

	@Column(name = "PREMIO_SEGUNDO_COLOCADO")
	private String premioSegundoColocado;

	@Column(name = "PREMIO_TERCEIRO_COLOCADO")
	private String premioTerceiroColocado;
	
	@OneToMany(mappedBy = "id" )
	private Set<Time> times = new HashSet<>();
	
	public Set<Time> getTimes() {
		return times;
	}

	public void setTimes(Set<Time> times) {
		this.times = times;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataFim() {
		return dataFim;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}

	public Date getDataInicioInscricoes() {
		return dataInicioInscricoes;
	}

	public void setDataInicioInscricoes(Date dataInicioInscricoes) {
		this.dataInicioInscricoes = dataInicioInscricoes;
	}
	
	public Date getDataFimInscricoes() {
		return dataFimInscricoes;
	}

	public void setDataFimInscricoes(Date dataFimInscricoes) {
		this.dataFimInscricoes = dataFimInscricoes;
	}

	public Time getCampeao() {
		return campeao;
	}

	public void setCampeao(Time campeao) {
		this.campeao = campeao;
	}

	public Time getSegundoColocado() {
		return segundoColocado;
	}

	public void setSegundoColocado(Time segundoColocado) {
		this.segundoColocado = segundoColocado;
	}

	public Time getTerceiroColocado() {
		return terceiroColocado;
	}

	public void setTerceiroColocado(Time terceiroColocado) {
		this.terceiroColocado = terceiroColocado;
	}

	public CategoriaCampeonato getCategoria() {
		return categoria;
	}

	public void setCategoria(CategoriaCampeonato categoria) {
		this.categoria = categoria;
	}

	public String getPremioCampeao() {
		return premioCampeao;
	}

	public void setPremioCampeao(String premioCampeao) {
		this.premioCampeao = premioCampeao;
	}

	public String getPremioSegundoColocado() {
		return premioSegundoColocado;
	}

	public void setPremioSegundoColocado(String premioSegundoColocado) {
		this.premioSegundoColocado = premioSegundoColocado;
	}

	public String getPremioTerceiroColocado() {
		return premioTerceiroColocado;
	}

	public void setPremioTerceiroColocado(String premioTerceiroColocado) {
		this.premioTerceiroColocado = premioTerceiroColocado;
	}

}
