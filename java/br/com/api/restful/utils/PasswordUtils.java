package br.com.api.restful.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordUtils {

	public static String generatePasswordBCrypt(String password) {
		if (password == null) {
			return password;
		}
		BCryptPasswordEncoder bcryptPasswordEncoder = new BCryptPasswordEncoder();
		return bcryptPasswordEncoder.encode(password);
	}
	
	public static boolean validPassword(String newPassword, String EncodedPasswordOld) {
		BCryptPasswordEncoder bcryptPasswordEncoder = new BCryptPasswordEncoder();
		return bcryptPasswordEncoder.matches(newPassword, EncodedPasswordOld);
	}

}
