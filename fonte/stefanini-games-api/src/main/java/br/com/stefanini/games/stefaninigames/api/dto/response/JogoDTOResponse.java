package br.com.stefanini.games.stefaninigames.api.dto.response;

import java.io.Serializable;
import java.util.Date;

import br.com.stefanini.games.stefaninigamesapi.model.Jogo;

public class JogoDTOResponse implements Serializable{

	private static final long serialVersionUID = 1L;
	
	public JogoDTOResponse() {}

	public JogoDTOResponse(Jogo jogo) {
		this.id = jogo.getId();
		this.time1 = new TimeDTOResponse(jogo.getTime1());
		this.time2 = new TimeDTOResponse(jogo.getTime2());
		if(jogo.getVencedor() != null){
			this.vencedor = new TimeDTOResponse(jogo.getVencedor());
		}
		if(jogo.getPerdedor() != null){
			this.perdedor = new TimeDTOResponse(jogo.getPerdedor());
		}
		this.data = jogo.getData();
		this.placarTime1 = jogo.getPlacarTime1();
		this.placarTime2 = jogo.getPlacarTime2();
		this.pontosTime1 = jogo.getPontosTime1();
		this.pontosTime2 = jogo.getPontosTime2();
		
	}

	
	private Long id;
	private TimeDTOResponse time1;
	private TimeDTOResponse time2;
	private TimeDTOResponse vencedor;
	private TimeDTOResponse perdedor;
	private Date data;
	private Integer placarTime1;
	private Integer placarTime2;
	private Integer pontosTime1;
	private Integer pontosTime2;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TimeDTOResponse getTime1() {
		return time1;
	}

	public void setTime1(TimeDTOResponse time1) {
		this.time1 = time1;
	}

	public TimeDTOResponse getTime2() {
		return time2;
	}

	public void setTime2(TimeDTOResponse time2) {
		this.time2 = time2;
	}

	public TimeDTOResponse getVencedor() {
		return vencedor;
	}

	public void setVencedor(TimeDTOResponse vencedor) {
		this.vencedor = vencedor;
	}

	public TimeDTOResponse getPerdedor() {
		return perdedor;
	}

	public void setPerdedor(TimeDTOResponse perdedor) {
		this.perdedor = perdedor;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
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
