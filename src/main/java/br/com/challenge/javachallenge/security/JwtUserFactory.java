package br.com.challenge.javachallenge.security;

import br.com.challenge.javachallenge.model.User;

public class JwtUserFactory {

	public JwtUserFactory() {

	}

	public static JwtUser create(User user) {
		return new JwtUser(user.getId(), user.getEmail(), user.getPassword());
	}

}
