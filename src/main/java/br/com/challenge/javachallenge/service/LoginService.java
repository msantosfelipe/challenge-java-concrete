package br.com.challenge.javachallenge.service;

import br.com.challenge.javachallenge.dto.LoginDto;
import br.com.challenge.javachallenge.model.User;

public interface LoginService {

	User login(LoginDto loginDto);

}
