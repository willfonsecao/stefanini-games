package br.com.stefanini.games.stefaninigamesapi.exception.rest;

import java.util.List;

import org.springframework.http.HttpStatus;

import br.com.stefanini.games.stefaninigamesapi.exception.GenericRuntimeException;

public class UnprocessableEntityException extends GenericRuntimeException{

	private static final long serialVersionUID = 1L;
	
	public UnprocessableEntityException(String erro) {
		super(erro);
		this.adicionarErro(erro);
	}

	public UnprocessableEntityException() {
		super("");
	}

	public UnprocessableEntityException(List<String> erros) {
		this.adicionarErro(erros);
	}

	@Override
	public HttpStatus getHttpStatus() {
		return HttpStatus.UNPROCESSABLE_ENTITY;
	}
	
}