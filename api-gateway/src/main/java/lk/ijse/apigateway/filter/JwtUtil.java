package lk.ijse.apigateway.filter;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import java.security.Key;

public class JwtUtil {
    private static final Key SECRET_KEY = Keys.hmacShaKeyFor("12345678901234567890123456789012".getBytes());

    public static String validateTokenAndGetUserId(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}