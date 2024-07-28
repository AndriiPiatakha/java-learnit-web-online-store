package com.itbulls.learnit.onlinestore.web.owasp.ba.solution;

import java.util.Base64;
import java.util.Date;

import javax.crypto.SecretKey;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

public class JwtUtils {

	// TODO - NEVER STORE SECRET KEYS IN THE SOURCE CODE! Use Secrets Key Vaults
	// The secret key as a Base64-encoded string representation
	private static final String SECRET_KEY_STRING = "b2g82k9ZmBrfY8fd/72Q6nMsdzH8fWgYyPw7TtD5FzI="; // Replace with your actual secure secret key

	// TODO - NEVER STORE SECRET KEYS IN THE SOURCE CODE! Use Secrets Key Vaults
	// Create a SecretKey instance for signing and verification
	private static final SecretKey SECRET_KEY = Keys.hmacShaKeyFor(Base64.getDecoder().decode(SECRET_KEY_STRING));

	/**
	 * Generates a JWT token for the given user email.
	 *
	 * @param user email the email to include in the token.
	 * @return the generated JWT token as a string.
	 */
	public static String generateToken(String userEmail) {

		return Jwts.builder().subject(userEmail).issuedAt(new Date())
				.expiration(new Date(System.currentTimeMillis() + 864_000_000)) // 10 days
				.signWith(SECRET_KEY) // Sign the token with the SecretKeySpec
				.compact();
	}


	/**
	 * Validates the JWT token.
	 *
	 * @param token the JWT token to validate.
	 * @return true if the token is valid, false otherwise.
	 */
	public static boolean isTokenValid(String token) {
		JwtParser jwtParser = Jwts.parser().verifyWith(SECRET_KEY).build();
		try {
			jwtParser.parse(token);
			return true;
		} catch (Exception e) {
			System.err.println("Could not verify JWT token integrity! Exception: " + e.getMessage());
			return false;
		}
		
	}


	public static Jws<Claims> parseToken(String token) {
		JwtParser jwtParser = Jwts.parser().verifyWith(SECRET_KEY).build();
		return jwtParser.parseSignedClaims(token);
	}
}