package net.slipp.www.api;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PasswordGeneratorTest {

	@Test
	public void generate() {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String password = encoder.encode("0000");
		log.info("Password = {}", password);
	}

}
