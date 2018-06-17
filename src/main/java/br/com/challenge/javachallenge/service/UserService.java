package br.com.challenge.javachallenge.service;

import br.com.challenge.javachallenge.dto.LoginDto;
import br.com.challenge.javachallenge.dto.UserDto;
import br.com.challenge.javachallenge.model.User;

public interface UserService {

	User register(UserDto userDto);

	User login(LoginDto loginDto);

}
