package br.com.stefanini.games.stefaninigamesapi.exception;

import java.util.List;

import org.springframework.http.HttpStatus;

import br.com.stefanini.games.stefaninigames.api.dto.response.ErrorDTO;

public class GenericRuntimeException extends RuntimeException implements ResponseStatus {

	private static final long serialVersionUID = 1356262548886996480L;
	
	public GenericRuntimeException(Exception e) {
		super(e);
	}

	public GenericRuntimeException(String message, Exception e) {
		super(message, e);
	}

	public GenericRuntimeException() {
		super();
	}

	public GenericRuntimeException(String message) {
		super(message);
	}

	public HttpStatus getHttpStatus() {
		return HttpStatus.INTERNAL_SERVER_ERROR;
	}
	
	private ErrorDTO erros = new ErrorDTO();
	
	public void adicionarErro(String error){
		this.erros.adicionarError(error);
	}
	
	public void adicionarErro(List<String> error){
		this.erros.adicionarError(error);
	}

	public ErrorDTO getErros() {
		return erros;
	}
}
