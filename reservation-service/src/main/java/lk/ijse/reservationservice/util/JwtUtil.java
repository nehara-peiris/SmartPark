package lk.ijse.reservationservice.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import java.security.Key;

public class JwtUtil {
    private static final String SECRET = "12345678901234567890123456789012";

    private static final Key SECRET_KEY = Keys.hmacShaKeyFor(SECRET.getBytes());

    public static Claims extractClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public static String extractUserId(String token) {
        return extractClaims(token).getSubject();
    }

    public static String extractUserRole(String token) {
        return extractClaims(token).get("role", String.class);
    }
}
