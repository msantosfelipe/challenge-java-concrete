package br.com.challenge.javachallenge.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.challenge.javachallenge.exceptionhandler.exception.NotAuthorizedException;
import br.com.challenge.javachallenge.model.Response;
import br.com.challenge.javachallenge.model.User;
import br.com.challenge.javachallenge.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(tags = "profile")
@RequestMapping(value = "/api/v1/profile")
@CrossOrigin(origins = "*")
public class ProfileController {

	private static final String AUTH_HEADER = "Authorization";

	@Autowired
	private UserService userService;

	@GetMapping(value = "/{id}")
	@ApiOperation(value = "Returns a user according to id", tags = "profile")
	public ResponseEntity<Response<User>> userProfile(@PathVariable("id") Long id, HttpServletRequest request) {

		Response<User> response = new Response<User>();

		String token = request.getHeader(AUTH_HEADER).substring(7);

		if (token == null) {
			throw new NotAuthorizedException();
		}

		User user = userService.returnUser(id, token);
		response.setData(user);

		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

}
