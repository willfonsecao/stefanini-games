package br.com.stefanini.games.stefaninigamesapi.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.joda.time.DateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.stefanini.games.stefaninigamesapi.enumarated.Meses;

@Entity
@Table(name = "CAMPEONATO")
@JsonIgnoreProperties(ignoreUnknown=true)
public class Campeonato implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	public Campeonato() {
		this.edicao = this.construirEdicao();
	}
	
	@Id
	@Column(name="ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "MAX_INSCRITOS")
	private Integer maxInscritos;
	
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

	@Column(name = "EDICAO")
	private String edicao;
	
	@Column(name = "JOGOS_GERADOS")
	private boolean isJogosGerados;
	
	public boolean isJogosGerados() {
		return isJogosGerados;
	}

	public void setJogosGerados(boolean isJogosGerados) {
		this.isJogosGerados = isJogosGerados;
	}
	
	public Integer getMaxInscritos() {
		return maxInscritos;
	}

	public void setMaxInscritos(Integer maxInscritos) {
		this.maxInscritos = maxInscritos;
	}

	private String construirEdicao() {
		DateTime dataInicio = new DateTime(this.getDataInicio());
		Integer mesInicio = dataInicio.getMonthOfYear();

		return Meses.getMes(mesInicio) + "/" + dataInicio.getYear();
	}
	
	public String getEdicao() {
		return edicao;
	}

	public void setEdicao(String edicao) {
		this.edicao = edicao;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((categoria == null) ? 0 : categoria.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Campeonato other = (Campeonato) obj;
		if (categoria == null) {
			if (other.categoria != null)
				return false;
		} else if (!categoria.equals(other.categoria))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
