package br.com.challenge.javachallenge.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.challenge.javachallenge.dto.LoginDto;
import br.com.challenge.javachallenge.model.User;
import br.com.challenge.javachallenge.service.LoginService;

@RestController
@RequestMapping("/api/v1/login")
@CrossOrigin(origins="*")
public class LoginController {

	@Autowired
	private LoginService loginService;
	
	@PostMapping
	public ResponseEntity<User> login(
			@RequestBody LoginDto loginDto, HttpServletResponse response) {
		User userCreated = loginService.login(loginDto);
		return ResponseEntity.ok(userCreated);
	}

}
