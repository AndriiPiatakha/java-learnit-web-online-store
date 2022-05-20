package com.itbulls.learnit.onlinestore.web.owasp.cf.solution.encryption;

import java.security.NoSuchAlgorithmException;

public class BCryptDemo {
	public static void main(String[] args) throws NoSuchAlgorithmException {
		String originalPassword = "testtestA*!";
		String generatedSecuredPasswordHash = BCrypt.generatePassword(originalPassword, BCrypt.gensalt(12));
		System.out.println(generatedSecuredPasswordHash);

		boolean matched = BCrypt.verifyPassword(originalPassword, generatedSecuredPasswordHash);
		System.out.println(matched);
		
		matched = BCrypt.verifyPassword("anotherPassword", generatedSecuredPasswordHash);
		System.out.println(matched);
	}
}
