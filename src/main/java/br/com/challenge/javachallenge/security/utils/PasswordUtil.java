package br.com.challenge.javachallenge.security.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordUtil {

	/**
	 * Generate a hash using BCrypt.
	 *
	 * @param password
	 * @return String
	 */
	public static String generateBCrypt(String password) {
		if (password == null) {
			return password;
		}

		BCryptPasswordEncoder bCryptEncoder = new BCryptPasswordEncoder();
		return bCryptEncoder.encode(password);
	}

	/**
	 * Verifies if a password is valid.
	 *
	 * @param password
	 * @param passwordEncoded
	 * @return boolean
	 */
	public static boolean validPassword(String password, String passwordEncoded) {
		BCryptPasswordEncoder bCryptEncoder = new BCryptPasswordEncoder();
		return bCryptEncoder.matches(password, passwordEncoded);
	}
}
