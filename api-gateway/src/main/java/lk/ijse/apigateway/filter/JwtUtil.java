package lk.ijse.apigateway.filter;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import java.security.Key;

public class JwtUtil {

    private static final String SECRET = "YourSecretKeyMustBeAtLeast256BitsLong!"; // Match with user-service

    public static boolean validateToken(String token) {
        try {
            Key key = Keys.hmacShaKeyFor(SECRET.getBytes());
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            System.out.println("Token validation failed: " + e.getMessage());
            return false;
        }
    }
}
