package br.com.challenge.javachallenge.model.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public class LoginDto {

	private String email;

	private String password;

	@NotEmpty(message = "The email must not be null")
	@Email(message = "Invalid email")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@NotEmpty(message = "The password must not be null")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
