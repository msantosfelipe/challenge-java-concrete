package br.com.challenge.javachallenge.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.challenge.javachallenge.dto.UserDto;
import br.com.challenge.javachallenge.model.User;
import br.com.challenge.javachallenge.repository.UserRepository;
import br.com.challenge.javachallenge.service.RegisterService;

@RestController
@RequestMapping("/api/v1/register")
@CrossOrigin(origins="*")
public class RegisterController {

	@Autowired
	private RegisterService userService;
	
	@Autowired
	private UserRepository userRepository;

	@PostMapping
	public ResponseEntity<User> registerUser(
			@RequestBody UserDto userDto, HttpServletResponse response) {
		User userCreated = userService.register(userDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(userCreated);
	}
	
	@GetMapping
	public ResponseEntity<List<User>> registerUser() {
		return ResponseEntity.ok(userRepository.findAll());
	}

}
