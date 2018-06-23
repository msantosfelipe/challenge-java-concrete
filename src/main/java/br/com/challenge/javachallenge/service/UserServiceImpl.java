package br.com.challenge.javachallenge.service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.challenge.javachallenge.exceptionhandler.exception.DuplicateUserException;
import br.com.challenge.javachallenge.exceptionhandler.exception.InvalidPasswordException;
import br.com.challenge.javachallenge.exceptionhandler.exception.InvalidSessionException;
import br.com.challenge.javachallenge.exceptionhandler.exception.NotAuthorizedException;
import br.com.challenge.javachallenge.exceptionhandler.exception.UserDoesNotExistException;
import br.com.challenge.javachallenge.model.User;
import br.com.challenge.javachallenge.model.dto.LoginDto;
import br.com.challenge.javachallenge.model.dto.UserDto;
import br.com.challenge.javachallenge.model.enums.ProfileEnum;
import br.com.challenge.javachallenge.repository.UserRepository;
import br.com.challenge.javachallenge.security.utils.JwtTokenUtil;
import br.com.challenge.javachallenge.security.utils.PasswordUtil;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PhoneService phoneService;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Override
	@Transactional
	public User register(UserDto userDto) {

		verifyDuplicateUser(userDto);

		User user = new User();
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(PasswordUtil.generateBCrypt(userDto.getPassword()));
		user.setCreated(LocalDateTime.now());
		user.setPhones(phoneService.saveList(userDto.getPhones()));
		user.setProfileEnum(ProfileEnum.ROLE_ADMIN);

		user.setToken(jwtTokenUtil.obterToken(userDto));

		User userCreated = userRepository.save(user);
		return userCreated;
	}

	@Override
	@Transactional
	public User login(LoginDto loginDto) {
		User user = userRepository.findByEmail(loginDto.getEmail());

		if (user == null) {
			throw new UserDoesNotExistException();
		}

		if (!PasswordUtil.validPassword(loginDto.getPassword(), user.getPassword())) {
			throw new InvalidPasswordException();
		}

		user.setLast_login(LocalDateTime.now());
		user.setModified(LocalDateTime.now());
		userRepository.save(user);

		return user;

	}

	@Override
	public Optional<User> findByEmail(String email) {
		return Optional.ofNullable(userRepository.findByEmail(email));
	}

	@Override
	public User returnUser(Long id, String token) {
		Optional<User> optionalUser = userRepository.findById(id);

		if (optionalUser.isPresent()) {
			User user = optionalUser.get();
			if (user.getToken().equals(token)) {
				if (verifyInvalidLastLogin(user)) {
					throw new InvalidSessionException();
				}
			} else {
				throw new NotAuthorizedException();
			}
		} else {
			throw new UserDoesNotExistException();
		}

		return optionalUser.get();
	}

	private boolean verifyInvalidLastLogin(User user) {
		Long lastLogin = user.getLast_login().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
		Long timeSinceLastLogin = System.currentTimeMillis() - lastLogin;

		return timeSinceLastLogin >= 1800000;
	}

	private void verifyDuplicateUser(UserDto userDto) {
		User user = userRepository.findByEmail(userDto.getEmail());

		if (user != null) {
			throw new DuplicateUserException();
		}
	}

}
