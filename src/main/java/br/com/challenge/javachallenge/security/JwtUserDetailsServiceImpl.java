package br.com.challenge.javachallenge.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import br.com.challenge.javachallenge.model.User;
import br.com.challenge.javachallenge.service.UserService;

public class JwtUserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	UserService userService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userService.findByEmail(username);

		if (user != null) {
			return JwtUserFactory.create(user);
		}

		throw new UsernameNotFoundException("Email not found");
	}

}
