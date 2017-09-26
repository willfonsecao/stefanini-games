package br.com.stefanini.games.stefaninigamesapi.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.stefanini.games.stefaninigamesapi.enumarated.SimNaoEnum;

@Entity
@Table(name = "JOGO")
public class Jogo implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name ="ID_TIME_1")
	private Time time1;

	@ManyToOne
	@JoinColumn(name ="ID_TIME_2")
	private Time time2;

	@ManyToOne
	@JoinColumn(name = "ID_VENCEDOR")
	private Time vencedor;
	
	@ManyToOne
	@JoinColumn(name = "ID_PERDEDOR")
	private Time perdedor;

	@Column(name = "DATA")
	private Date data;

	@Enumerated(EnumType.STRING)
	@Column(name = "EMPATE", length = 1)
	private SimNaoEnum isEmpate;
	

	@Column(name = "PLACAR_TIME_1")
	private Integer placarTime1;

	@Column(name = "PLACAR_TIME_2")
	private Integer placarTime2;

	@Column(name = "PONTOS_TIME_1")
	private Integer pontosTime1;
	
	@Column(name = "PONTOS_TIME_2")
	private Integer pontosTime2;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Time getTime1() {
		return time1;
	}

	public void setTime1(Time time1) {
		this.time1 = time1;
	}

	public Time getTime2() {
		return time2;
	}

	public void setTime2(Time time2) {
		this.time2 = time2;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public SimNaoEnum getIsEmpate() {
		return isEmpate;
	}

	public void setIsEmpate(SimNaoEnum isEmpate) {
		this.isEmpate = isEmpate;
	}

	public Time getVencedor() {
		return vencedor;
	}

	public void setVencedor(Time vencedor) {
		this.vencedor = vencedor;
	}

	public Time getPerdedor() {
		return perdedor;
	}

	public void setPerdedor(Time perdedor) {
		this.perdedor = perdedor;
	}

	public Integer getPlacarTime1() {
		return placarTime1;
	}

	public void setPlacarTime1(Integer placarTime1) {
		this.placarTime1 = placarTime1;
	}

	public Integer getPlacarTime2() {
		return placarTime2;
	}

	public void setPlacarTime2(Integer placarTime2) {
		this.placarTime2 = placarTime2;
	}

	public Integer getPontosTime1() {
		return pontosTime1;
	}

	public void setPontosTime1(Integer pontosTime1) {
		this.pontosTime1 = pontosTime1;
	}

	public Integer getPontosTime2() {
		return pontosTime2;
	}

	public void setPontosTime2(Integer pontosTime2) {
		this.pontosTime2 = pontosTime2;
	}
	
}
