package br.com.challenge.javachallenge.security.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import br.com.challenge.javachallenge.model.dto.UserDto;
import io.jsonwebtoken.Claims;
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

	public String obterToken(UserDto userDto) {
		Map<String, Object> claims = new HashMap<>();
		claims.put(CLAIM_KEY_USERNAME, userDto.getName());
		claims.put(CLAIM_KEY_EMAIL, userDto.getEmail());
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		String formatDateTime = LocalDateTime.now().format(formatter);
		claims.put(CLAIM_KEY_CREATED, formatDateTime);
		return gerarToken(claims);
	}

	public String getUsernameFromToken(String token) {
		String username;

		try {
			Claims claims = getClaimsFromToken(token);
			username = claims.getSubject();
		} catch (Exception e) {
			username = null;
		}

		return username;
	}

	public Date getExpirationDateFromToken(String token) {
		Date expiration;

		try {
			Claims claims = getClaimsFromToken(token);
			expiration = claims.getExpiration();
		} catch (Exception e) {
			expiration = null;
		}

		return expiration;
	}

	public boolean tokenValido(String token) {
		return !tokenExpirado(token);
	}

	public Claims getClaimsFromToken(String token) {
		Claims claims;
		try {
			claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();

		} catch (Exception e) {
			claims = null;
		}
		return claims;
	}

	public boolean tokenExpirado(String token) {
		Date dataExpiracao = this.getExpirationDateFromToken(token);
		if (dataExpiracao == null) {
			return false;
		}
		return dataExpiracao.before(new Date());
	}

	private Date gerarDataExpiracao() {
		return new Date(System.currentTimeMillis() + expiration * 1000);
	}

}
