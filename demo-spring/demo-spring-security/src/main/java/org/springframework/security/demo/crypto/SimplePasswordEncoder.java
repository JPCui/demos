package org.springframework.security.demo.crypto;

import org.apache.log4j.Logger;
import org.springframework.security.crypto.password.PasswordEncoder;

public class SimplePasswordEncoder implements PasswordEncoder {

	private static Logger logger = Logger.getLogger(SimplePasswordEncoder.class);

	@Override
	public String encode(CharSequence rawPassword) {
		logger.info("rawPassword : " + rawPassword);
		return rawPassword.toString();
	}

	@Override
	public boolean matches(CharSequence rawPassword, String encodedPassword) {
		return rawPassword.toString().equals(encodedPassword);
	}

}
