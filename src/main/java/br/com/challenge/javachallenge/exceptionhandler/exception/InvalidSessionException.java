package br.com.challenge.javachallenge.exceptionhandler.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class InvalidSessionException extends RuntimeException {

	private static final long serialVersionUID = -4008940427128162563L;

	public InvalidSessionException() {
		super("Sessão inválida");
	}

	public InvalidSessionException(String message) {
		super(message);
	}
}
