package br.com.stefanini.games.stefaninigamesapi.exception.rest;

import org.springframework.http.HttpStatus;

import br.com.stefanini.games.stefaninigamesapi.exception.GenericRuntimeException;

public class NotFoundException  extends GenericRuntimeException{

	private static final long serialVersionUID = 1L;
	
	public NotFoundException(String error) {
		super(error);
		this.adicionarErro(error);
	}

	@Override
	public HttpStatus getHttpStatus() {
		return HttpStatus.NOT_FOUND;
	}

}
