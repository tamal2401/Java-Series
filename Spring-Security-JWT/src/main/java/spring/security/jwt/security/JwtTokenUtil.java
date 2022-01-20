package spring.security.jwt.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

//import io.jsonwebtoken.security.Keys;

@Component
public class JwtTokenUtil {

    public static final long EXPIRY_LIMIT = 1000L * 60;

    public static final String KEY = "q3t6w9z$C&F)J@NcQfTjWnZr4u7x!A%D*G-KaPdSgUkXp2s5v8y/B?E(H+MbQeTh";

    public String getUserNameFromToken(String token){
        return getClaimsFromToken(token, Claims::getSubject);
    }

    public Date getExpiryDate(String token){
        return getClaimsFromToken(token, Claims::getExpiration);
    }

    private <T> T getClaimsFromToken(String token, Function<Claims, T> claimsResolver) {
        Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(KEY)
                .parseClaimsJws(token)
                .getBody();
    }

    public boolean isExpiredToken(String token){
        Date expiryDate = getExpiryDate(token);
        return !expiryDate.after(new Date());
    }

    public String generateToken(UserDetails details){
        Map<String, Object> claims = new HashMap<>();
        return doGenerateToken(claims, details.getUsername());
    }

    public String doGenerateToken(Map<String, Object> claims, String username) {
        Date expiryDate = new Date(System.currentTimeMillis() + EXPIRY_LIMIT);
//        Key key = Keys.hmacShaKeyFor(KEY.getBytes(StandardCharsets.UTF_8));
//        Key key = Keys.secretKeyFor(SignatureAlgorithm.HS512);
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date())
                .setExpiration(expiryDate)
                .setSubject(username)
                .signWith(SignatureAlgorithm.HS512, KEY)
                .compact();
    }

    public boolean validateToken(String token, UserDetails details){
        final String userName = getUserNameFromToken(token);
        return (userName.equals(details.getUsername()) && !isExpiredToken(token));
    }
}
