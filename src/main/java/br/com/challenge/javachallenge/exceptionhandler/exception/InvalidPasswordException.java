package br.com.challenge.javachallenge.exceptionhandler.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
public class InvalidPasswordException extends RuntimeException {

	private static final long serialVersionUID = -5818541947543670889L;

	public InvalidPasswordException() {
		super("Usuário e/ou senha inválidos");
	}

	public InvalidPasswordException(String message) {
		super(message);
	}

}
