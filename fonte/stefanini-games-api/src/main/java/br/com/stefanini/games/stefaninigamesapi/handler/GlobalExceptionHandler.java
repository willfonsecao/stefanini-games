package br.com.stefanini.games.stefaninigamesapi.handler;

import org.apache.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.stefanini.games.stefaninigamesapi.exception.GenericException;
import br.com.stefanini.games.stefaninigamesapi.exception.GenericRuntimeException;
import br.com.stefanini.games.stefaninigamesapi.exception.ResponseStatus;
import br.com.stefanini.games.stefaninigamesapi.exception.rest.NotFoundException;
import br.com.stefanini.games.stefaninigamesapi.exception.rest.UnprocessableEntityException;

@EnableWebMvc 
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
	
	private static final Logger log = Logger.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler({GenericException.class, GenericRuntimeException.class})
    public ResponseEntity<Object> handleKnownExceptions(Exception e, WebRequest request) {
        log.error(e.getMessage(), e);
        return handleExceptionInternal(e, e.getMessage(), new HttpHeaders(), ((ResponseStatus) e).getHttpStatus(), request);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Object> handleIllegalArgumentException(Exception e, WebRequest request) {
        log.error(e.getMessage(), e);
        return handleExceptionInternal(e, e.getMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler({UnprocessableEntityException.class, NotFoundException.class})
    public ResponseEntity<Object> handleUnprocessableEntityException(Exception e, WebRequest request) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        
		return handleExceptionInternal(e, ((GenericRuntimeException)e).getErros(), headers, ((ResponseStatus) e).getHttpStatus(), request);
    }
    
    @Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
    	return super.handleExceptionInternal(ex, body, headers, status, request);
	}
}