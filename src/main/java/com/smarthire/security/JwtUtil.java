package com.smarthire.security;

import java.security.Key;
import java.util.Date;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {

	// Minimum 256-bit secret key (Base64 encoded)
	private static final String SECRET_KEY = "VGhpc0lzQVNlY3JldEtleUZvclNtYXJ0SGlyZUFJSmF2YQ==";

	// 1. Generate signing key
	private Key getSignKey() {
		byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
		return Keys.hmacShaKeyFor(keyBytes);
	}

	// 2. Generate token using email
	public String generateToken(String email) {
		return Jwts.builder().setSubject(email).setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // 10 hours
				.signWith(getSignKey(), SignatureAlgorithm.HS256).compact();
	}

	// 3. Extract email from token
	public String extractEmail(String token) {
		return extractClaims(token).getSubject();
	}

	// 4. Extract all claims
	private Claims extractClaims(String token) {
		return Jwts.parserBuilder().setSigningKey(getSignKey()).build().parseClaimsJws(token).getBody();
	}

	// 5. Validate token
	public boolean validateToken(String token, String email) {
		final String extractedEmail = extractEmail(token);
		return extractedEmail.equals(email) && !isTokenExpired(token);
	}

	// 6. Check expiry
	private boolean isTokenExpired(String token) {
		return extractClaims(token).getExpiration().before(new Date());
	}
}