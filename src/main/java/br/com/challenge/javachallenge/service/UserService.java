package br.com.challenge.javachallenge.service;

import java.util.Optional;

import br.com.challenge.javachallenge.model.User;
import br.com.challenge.javachallenge.model.dto.LoginDto;
import br.com.challenge.javachallenge.model.dto.UserDto;

public interface UserService {

	public User register(UserDto userDto);

	public User login(LoginDto loginDto);

	public Optional<User> findByEmail(String email);
	
	public User returnUser(Long id, String token);

}
