package br.com.challenge.javachallenge.exceptionhandler.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class DuplicateUserException extends RuntimeException {

	private static final long serialVersionUID = 5859720370959561155L;

	public DuplicateUserException() {
		super("E-mail já existente");
	}

	public DuplicateUserException(String message) {
		super(message);
	}
}
