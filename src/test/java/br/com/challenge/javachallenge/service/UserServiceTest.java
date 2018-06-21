package br.com.challenge.javachallenge.service;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import br.com.challenge.javachallenge.model.Phone;
import br.com.challenge.javachallenge.model.User;
import br.com.challenge.javachallenge.model.dto.LoginDto;
import br.com.challenge.javachallenge.model.dto.UserDto;
import br.com.challenge.javachallenge.repository.PhoneRepository;
import br.com.challenge.javachallenge.repository.UserRepository;
import br.com.challenge.javachallenge.security.utils.JwtTokenUtil;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

	@InjectMocks
	@Spy
	private UserServiceImpl userService;

	@Mock
	private PhoneServiceImpl phoneService;

	@Mock
	private UserRepository userRepository;

	@Mock
	private PhoneRepository phoneRepository;

	@Mock
	private JwtTokenUtil jwtTokenUtil;

	@Before
	public void inicializar() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	// @Test
	public void register() {
		String mensagemErro = "";

		UserDto userDto = new UserDto();
		userDto.setEmail("test@test.com");
		userDto.setName("Tester");
		userDto.setPassword("test123");
		userDto.setPhones(new ArrayList<Phone>());

		try {
			userService.register(userDto);
		} catch (Exception e) {
			mensagemErro = e.getMessage();
		}

		Assert.assertEquals(mensagemErro, "");
	}

	// @Test
	public void registerDuplicateUser() {
		String mensagemErro = "";

		UserDto userDto = new UserDto();
		userDto.setEmail("test@test.com");
		userDto.setName("Tester");
		userDto.setPassword("test123");
		userDto.setPhones(new ArrayList<Phone>());

		Mockito.doReturn(returnUser()).when(userRepository).findByEmail(userDto.getEmail());

		try {
			userService.register(userDto);
		} catch (Exception e) {
			mensagemErro = e.getMessage();
		}

		Assert.assertEquals(mensagemErro, "E-mail já existente");
	}

	// @Test
	public void login() {
		String mensagemErro = "";

		LoginDto loginDto = new LoginDto();
		loginDto.setEmail("test@test.com");
		loginDto.setPassword("test123");

		Mockito.doReturn(returnUser()).when(userRepository).findByEmail(loginDto.getEmail());

		try {
			userService.login(loginDto);
		} catch (Exception e) {
			mensagemErro = e.getMessage();
		}

		Assert.assertEquals(mensagemErro, "");
	}

	// @Test
	public void loginUserDoesNotExist() {
		String mensagemErro = "";

		LoginDto loginDto = new LoginDto();
		loginDto.setEmail("test@test.com");
		loginDto.setPassword("test123");

		try {
			userService.login(loginDto);
		} catch (Exception e) {
			mensagemErro = e.getMessage();
		}

		Assert.assertEquals(mensagemErro, "Usuário e/ou senha inválidos");
	}

	@Test
	public void loginWrongPassword() {
		String mensagemErro = "";

		LoginDto loginDto = new LoginDto();
		loginDto.setEmail("test@test.com");
		loginDto.setPassword("test1234");

		Mockito.doReturn(returnUser()).when(userRepository).findByEmail(loginDto.getEmail());

		try {
			userService.login(loginDto);
		} catch (Exception e) {
			mensagemErro = e.getMessage();
		}

		Assert.assertEquals(mensagemErro, "Usuário e/ou senha inválidos");
	}

	private User returnUser() {
		User userReturned = new User();
		userReturned.setEmail("test@test.com");
		userReturned.setName("Tester");
		userReturned.setPassword("test123");
		return userReturned;
	}
}
