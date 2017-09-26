package br.com.stefanini.games.stefaninigames.api.dto.response;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ErrorDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private List<String> erros = new ArrayList<>();
	
	public void adicionarError(String error){
		this.erros.add(error);
	}
	
	public void adicionarError(List<String> error) {
		this.erros.addAll(error);
	}
	
	public List<String> getErros() {
		return erros;
	}

}