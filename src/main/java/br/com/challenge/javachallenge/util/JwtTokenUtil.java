package br.com.challenge.javachallenge.util;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import br.com.challenge.javachallenge.model.dto.UserDto;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenUtil {

	static final String CLAIM_KEY_USERNAME = "sub";
	static final String CLAIM_KEY_EMAIL = "email";
	static final String CLAIM_KEY_CREATED = "created";

	@Value("${jwt.secret}")
	private String secret;

	@Value("${jwt.expiration}")
	private Long expiration;

	private String gerarToken(Map<String, Object> claims) {
		return Jwts.builder().setClaims(claims).setExpiration(gerarDataExpiracao())
				.signWith(SignatureAlgorithm.HS512, secret).compact();
	}

	private Date gerarDataExpiracao() {
		return new Date(System.currentTimeMillis() + expiration * 1000);
	}

	public String obterToken(UserDto userDto) {
		Map<String, Object> claims = new HashMap<>();
		claims.put(CLAIM_KEY_USERNAME, userDto.getName());
		claims.put(CLAIM_KEY_EMAIL, userDto.getEmail());
		claims.put(CLAIM_KEY_CREATED, LocalDateTime.now());
		return gerarToken(claims);
	}

	// public String getUsernameFromToken(String token) {
	// String username;
	//
	// try {
	// Claims claims = getClaimsFromToken(token);
	// username = claims.getSubject();
	// } catch (Exception e) {
	// username = null;
	// }
	//
	// return username;
	// }
	//
	// public Date getExpirationDateFromToken(String token) {
	// Date expiration;
	//
	// try {
	// Claims claims = getClaimsFromToken(token);
	// expiration = claims.getExpiration();
	// } catch (Exception e) {
	// expiration = null;
	// }
	//
	// return expiration;
	// }

}
