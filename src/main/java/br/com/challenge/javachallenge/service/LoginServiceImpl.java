package br.com.challenge.javachallenge.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.challenge.javachallenge.dto.LoginDto;
import br.com.challenge.javachallenge.model.User;
import br.com.challenge.javachallenge.repository.UserRepository;

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	UserRepository userRepository;

	@Override
	public User login(LoginDto loginDto) {
		User user = userRepository.findByEmail(loginDto.getEmail());
		
		if (user == null) {
			// TODO exception de usuario não existe
		} else {
			if (user.getPassword() != loginDto.getPassword()) {
				// TODO exception de senha inválida
			}
		}

		return user;
	}

}
