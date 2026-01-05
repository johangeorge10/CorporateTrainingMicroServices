package com.capstone.enrollment.security;


import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;

@Component
public class JwtUtil {

    // ⚠️ MUST MATCH User Microservice secret
	private static final String SECRET =
		    "THIS_IS_A_VERY_LONG_AND_SECURE_SECRET_KEY_FOR_JWT_256_BITS_123456";

		private final Key key = Keys.hmacShaKeyFor(SECRET.getBytes());

    public Claims validateToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
