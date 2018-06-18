package br.com.challenge.javachallenge.model.dto;

import java.util.List;

import javax.validation.constraints.NotEmpty;

import br.com.challenge.javachallenge.model.Phone;

public class UserDto {

	private String name;

	private String email;

	private String password;

	private List<Phone> phones;

	@NotEmpty(message="The name must not be null")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@NotEmpty(message="The email must not be null")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@NotEmpty(message="The password must not be null")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Phone> getPhones() {
		return phones;
	}

	public void setPhones(List<Phone> phones) {
		this.phones = phones;
	}

}
