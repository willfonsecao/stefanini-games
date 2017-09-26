package br.com.stefanini.games.stefaninigamesapi.exception;

import org.springframework.http.HttpStatus;

public abstract class GenericException extends Exception implements ResponseStatus {

	private static final long serialVersionUID = 7385089830832377347L;

	public GenericException(Exception e) {
		super(e);
	}

	public GenericException() {
		super();
	}

	public GenericException(String message) {
		super(message);
	}

	public HttpStatus getHttpStatus() {
		return HttpStatus.INTERNAL_SERVER_ERROR;
	}
}
