package br.com.challenge.javachallenge.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.challenge.javachallenge.model.Response;
import br.com.challenge.javachallenge.model.User;
import br.com.challenge.javachallenge.model.dto.LoginDto;
import br.com.challenge.javachallenge.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(tags = "login")
@RequestMapping(value = "/api/v1/login")
@CrossOrigin(origins = "*")
public class LoginController {

	@Autowired
	private UserService userService;

	@PostMapping
	@ApiOperation(value = "Login to the system", tags = "login")
	public ResponseEntity<Response<User>> login(@Valid @RequestBody LoginDto loginDto, BindingResult result) {
		Response<User> response = new Response<User>();

		if (result.hasErrors()) {
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}

		User user = userService.login(loginDto);
		response.setData(user);
		return ResponseEntity.ok(response);
	}

}
