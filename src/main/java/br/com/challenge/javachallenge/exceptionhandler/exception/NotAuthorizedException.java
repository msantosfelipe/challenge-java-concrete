package br.com.challenge.javachallenge.exceptionhandler.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
public class NotAuthorizedException extends RuntimeException {

	private static final long serialVersionUID = -542641488090159964L;

	public NotAuthorizedException() {
		super("Não autorizado");
	}

	public NotAuthorizedException(String message) {
		super(message);
	}

}
