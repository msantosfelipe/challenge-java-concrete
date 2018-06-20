package br.com.challenge.javachallenge.service;

import br.com.challenge.javachallenge.model.User;
import br.com.challenge.javachallenge.model.dto.LoginDto;
import br.com.challenge.javachallenge.model.dto.UserDto;

public interface UserService {

	User register(UserDto userDto);

	User login(LoginDto loginDto);

	User findByEmail(String email);
	
	User returnUser(Long id, String token);

}
