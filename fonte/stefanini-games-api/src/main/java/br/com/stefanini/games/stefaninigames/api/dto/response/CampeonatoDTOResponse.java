package br.com.stefanini.games.stefaninigames.api.dto.response;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import br.com.stefanini.games.stefaninigamesapi.model.Campeonato;
import br.com.stefanini.games.stefaninigamesapi.model.CategoriaCampeonato;
import br.com.stefanini.games.stefaninigamesapi.model.Time;

public class CampeonatoDTOResponse implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public CampeonatoDTOResponse() {}
	
	public CampeonatoDTOResponse(Campeonato campeonato) {

		this.id = campeonato.getId();
		this.dataInicio = campeonato.getDataInicio();
		this.dataFim = campeonato.getDataFim();
		this.dataInicioInscricoes = campeonato.getDataInicioInscricoes();
		this.dataFimInscricoes = campeonato.getDataFimInscricoes();
		this.campeao = campeonato.getCampeao();
		this.segundoColocado = campeonato.getSegundoColocado();
		this.terceiroColocado = campeonato.getTerceiroColocado();
		this.categoria = campeonato.getCategoria();
		this.premioCampeao = campeonato.getPremioCampeao();
		this.premioSegundoColocado = campeonato.getPremioSegundoColocado();
		this.premioTerceiroColocado = campeonato.getPremioTerceiroColocado();
		this.edicao = campeonato.getEdicao();
		this.maxInscritos = campeonato.getMaxInscritos();
	}

	private Long id;

	private Date dataInicio;

	private Date dataFim;

	private Date dataInicioInscricoes;

	private Date dataFimInscricoes;

	private Time campeao;

	private Time segundoColocado;

	private Time terceiroColocado;

	@JsonManagedReference
	private CategoriaCampeonato categoria;

	private String premioCampeao;

	private String premioSegundoColocado;

	private String premioTerceiroColocado;

	private String edicao;
	
	private Long maxInscritos;
	
	private boolean isGerarJogos;

	private boolean isVagasPreenchidas;
	
	public boolean isVagasPreenchidas() {
		return isVagasPreenchidas;
	}

	public void setVagasPreenchidas(boolean isVagasPreenchidas) {
		this.isVagasPreenchidas = isVagasPreenchidas;
	}

	public boolean isGerarJogos() {
		return isGerarJogos;
	}

	public void setGerarJogos(boolean isGerarJogos) {
		this.isGerarJogos = isGerarJogos;
	}

	public Long getMaxInscritos() {
		return maxInscritos;
	}

	public void setMaxInscritos(Long maxInscritos) {
		this.maxInscritos = maxInscritos;
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

	public String getEdicao() {
		return edicao;
	}

	public void setEdicao(String edicao) {
		this.edicao = edicao;
	}

}
