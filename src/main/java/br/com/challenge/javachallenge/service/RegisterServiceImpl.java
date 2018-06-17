package br.com.challenge.javachallenge.service;

import java.time.LocalDateTime;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.challenge.javachallenge.dto.UserDto;
import br.com.challenge.javachallenge.model.User;
import br.com.challenge.javachallenge.repository.UserRepository;
import br.com.challenge.javachallenge.service.exception.DuplicateUserException;
import br.com.challenge.javachallenge.util.PasswordUtil;

@Service
public class RegisterServiceImpl implements RegisterService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	PhoneService phoneService;

	@Override
	@Transactional
	public User register(UserDto userDto) {

		// TODO buscar por email já existente

		verifyDuplicateUser(userDto);

		User user = new User();
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(PasswordUtil.generateBCrypt(userDto.getPassword()));
		user.setCreated(LocalDateTime.now());

		user.setPhones(phoneService.saveList(userDto.getPhones()));

		User userCreated = userRepository.save(user);

		return userCreated;
	}

	private void verifyDuplicateUser(UserDto userDto) {
		User user = userRepository.findByEmail(userDto.getEmail());
		
		if (user != null) {
			throw new DuplicateUserException();
		}
	}

}
