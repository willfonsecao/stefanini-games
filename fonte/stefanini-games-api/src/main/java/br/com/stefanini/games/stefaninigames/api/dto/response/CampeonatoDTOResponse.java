package br.com.stefanini.games.stefaninigames.api.dto.response;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import br.com.stefanini.games.stefaninigamesapi.model.Campeonato;
import br.com.stefanini.games.stefaninigamesapi.model.CategoriaCampeonato;

public class CampeonatoDTOResponse implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public CampeonatoDTOResponse() {}
	
	public CampeonatoDTOResponse(Campeonato campeonato) {

		this.id = campeonato.getId();
		this.dataInicio = campeonato.getDataInicio();
		this.dataFim = campeonato.getDataFim();
		this.dataInicioInscricoes = campeonato.getDataInicioInscricoes();
		this.dataFimInscricoes = campeonato.getDataFimInscricoes();
		atribuirFinalistas(campeonato);
		this.categoria = campeonato.getCategoria();
		this.premioCampeao = campeonato.getPremioCampeao();
		this.premioSegundoColocado = campeonato.getPremioSegundoColocado();
		this.premioTerceiroColocado = campeonato.getPremioTerceiroColocado();
		this.edicao = campeonato.getEdicao();
		this.maxInscritos = campeonato.getMaxInscritos();
		this.isJogosGerados = campeonato.isJogosGerados();
	}

	private void atribuirFinalistas(Campeonato campeonato) {
		if(campeonato.getCampeao() != null){
			this.campeao = new TimeDTOResponse(campeonato.getCampeao());
		}
		if(campeonato.getSegundoColocado() != null){
			this.segundoColocado = new TimeDTOResponse(campeonato.getSegundoColocado());
		}
		if(campeonato.getTerceiroColocado() != null){
			this.terceiroColocado = new TimeDTOResponse(campeonato.getTerceiroColocado());
		}
	}

	private Long id;

	private Date dataInicio;

	private Date dataFim;

	private Date dataInicioInscricoes;

	private Date dataFimInscricoes;

	private TimeDTOResponse campeao;

	private TimeDTOResponse segundoColocado;

	private TimeDTOResponse terceiroColocado;

	@JsonManagedReference
	private CategoriaCampeonato categoria;

	private String premioCampeao;

	private String premioSegundoColocado;

	private String premioTerceiroColocado;

	private String edicao;
	
	private Long maxInscritos;
	
	private boolean isGerarJogos;
	
	private boolean isJogosGerados;

	private boolean isVagasPreenchidas;
	
	public boolean isJogosGerados() {
		return isJogosGerados;
	}

	public void setJogosGerados(boolean isJogosGerados) {
		this.isJogosGerados = isJogosGerados;
	}

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

	public TimeDTOResponse getCampeao() {
		return campeao;
	}

	public void setCampeao(TimeDTOResponse campeao) {
		this.campeao = campeao;
	}

	public TimeDTOResponse getSegundoColocado() {
		return segundoColocado;
	}

	public void setSegundoColocado(TimeDTOResponse segundoColocado) {
		this.segundoColocado = segundoColocado;
	}

	public TimeDTOResponse getTerceiroColocado() {
		return terceiroColocado;
	}

	public void setTerceiroColocado(TimeDTOResponse terceiroColocado) {
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
