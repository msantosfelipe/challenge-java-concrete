package br.com.challenge.javachallenge.exceptionhandler.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class UserDoesNotExistException extends RuntimeException {

	private static final long serialVersionUID = 3716209111181297286L;

	public UserDoesNotExistException() {
		super("Usuário e/ou senha inválidos");
	}

	public UserDoesNotExistException(String message) {
		super(message);
	}

}
