package br.com.challenge.javachallenge.exceptionhandler;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.challenge.javachallenge.service.exception.DuplicateUserException;

public class ChallengeExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler({ DuplicateUserException.class })
	public ResponseEntity<Object> handleDuplicateUserException(DuplicateUserException ex, WebRequest request) {
		String mensagem = ex.getMessage();
		List<Erro> erros = Arrays.asList(new Erro(mensagem));
		return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}

	public static class Erro {

		private String mensagem;

		public Erro(String mensagem) {
			this.mensagem = mensagem;
		}

		public String getMensagem() {
			return mensagem;
		}

	}

}
