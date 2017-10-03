package br.com.stefanini.games.stefaninigamesapi.exception;

import org.springframework.http.HttpStatus;

public interface ResponseStatus {

    HttpStatus getHttpStatus();
}
