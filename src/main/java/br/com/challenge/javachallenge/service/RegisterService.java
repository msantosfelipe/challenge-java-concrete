package br.com.challenge.javachallenge.service;

import br.com.challenge.javachallenge.dto.UserDto;
import br.com.challenge.javachallenge.model.User;

public interface RegisterService {

	User register(UserDto userDto);

}
