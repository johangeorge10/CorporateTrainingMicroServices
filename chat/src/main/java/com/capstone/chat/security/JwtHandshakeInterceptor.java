package com.capstone.chat.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import java.util.Map;

public class JwtHandshakeInterceptor implements HandshakeInterceptor {

    // ⚠️ Use the same secret key as your user-service
    private final String secretKey = "THIS_IS_A_VERY_LONG_AND_SECURE_SECRET_KEY_FOR_JWT_256_BITS_123456";

    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response,
                                   WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {

        if (request instanceof ServletServerHttpRequest servletRequest) {
            String path = servletRequest.getServletRequest().getRequestURI();

            // ✅ Allow SockJS /ws/info probe (no JWT sent yet)
            if (path.contains("/ws/info")) {
                return true;
            }

            // ✅ Validate JWT for real WebSocket handshake
            String authHeader = servletRequest.getServletRequest().getHeader("Authorization");
            System.out.println(authHeader);
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                response.setStatusCode(HttpStatus.FORBIDDEN);
                return false;
            }

            String token = authHeader.substring(7);
            try {
                Claims claims = Jwts.parser()
                        .setSigningKey(secretKey.getBytes())
                        .parseClaimsJws(token)
                        .getBody();

                String userId = claims.getSubject();   // UUID from your token
                attributes.put("userId", userId);      // store it for WebSocket session
                return true;

            } catch (JwtException e) {
                System.out.println("❌ Invalid JWT: " + e.getMessage());
                response.setStatusCode(HttpStatus.FORBIDDEN);
                return false;
            }
        }

        return false;
    }

    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response,
                               WebSocketHandler wsHandler, Exception exception) {
        // no-op
    }
}