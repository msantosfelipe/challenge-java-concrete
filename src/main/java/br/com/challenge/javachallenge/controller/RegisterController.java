package br.com.challenge.javachallenge.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.challenge.javachallenge.model.Response;
import br.com.challenge.javachallenge.model.User;
import br.com.challenge.javachallenge.model.dto.UserDto;
import br.com.challenge.javachallenge.repository.UserRepository;
import br.com.challenge.javachallenge.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(tags = "register")
@RequestMapping("/api/v1/register")
@CrossOrigin(origins = "*")
public class RegisterController {

	@Autowired
	private UserService userService;

	// TODO remover
	@Autowired
	private UserRepository userRepository;

	@PostMapping
	@ApiOperation(value = "Register a new user", tags = "register")
	public ResponseEntity<Response<User>> registerUser(@RequestBody UserDto userDto, BindingResult result) {
		Response<User> response = new Response<User>();

		if (result.hasErrors()) {
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}

		User userCreated = userService.register(userDto);
		response.setData(userCreated);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	// TODO remover
	@GetMapping
	public ResponseEntity<List<User>> registerUser() {
		return ResponseEntity.ok(userRepository.findAll());
	}

}
