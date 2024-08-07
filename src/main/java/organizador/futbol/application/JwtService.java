package organizador.futbol.application;

import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import organizador.futbol.domain.User;

@Service
public class JwtService {

	private static final String SECRET_KEY = "586E3272357538782F413F4428472B4B6250655368566B597033733676397924";

    public String getToken(UserDetails user) {
        String token = buildToken(new HashMap<>(), user);
        return token;
    }

    private String buildToken(Map<String, Object> extraClaims, UserDetails user) {
        if (user instanceof User) {
            extraClaims.put("name", ((User) user).getName());
        }

        long expirationInMillis = 1000L * 60 * 60 * 24 * 365 * 10 ; // 10 años
        return Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(user.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expirationInMillis))
                .signWith(getKey(), SignatureAlgorithm.HS256)
                .compact();
    }





    private Key getKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        System.out.println("Decoded Key: " + Base64.getEncoder().encodeToString(keyBytes));
        return Keys.hmacShaKeyFor(keyBytes);
    }



    public String getUsernameFromToken(String token) {
        Claims claims = getAllClaims(token);
        if (claims != null) {
            return claims.getSubject();
        } else {
            return null;
        }
    }




    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username=getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername())&& !isTokenExpired(token));
    }

    private Claims getAllClaims(String token) {
        try {
            return Jwts
                .parserBuilder()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
        } catch (Exception e) {
            System.out.println("Error decoding token claims: " + e.getMessage());
            return null;
        }
    }


    public <T> T getClaim(String token, Function<Claims, T> claimsResolver) {
        try {
            Claims claims = getAllClaims(token);
            return claimsResolver.apply(claims);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private Date getExpiration(String token)
    {
        return getClaim(token, Claims::getExpiration);
    }

    public boolean isTokenExpired(String token)
    {
        return getExpiration(token).before(new Date());
    }

    public String renewToken(String expiredToken) {
        String username = getUsernameFromToken(expiredToken);

        Map<String, Object> extraClaims = getExtraClaimsFromToken(expiredToken);

        String newToken = buildToken(extraClaims, username);

        return newToken;
    }

    private Map<String, Object> getExtraClaimsFromToken(String token) {
        Map<String, Object> extraClaims = new HashMap<>();
        return extraClaims;
    }
    
    private String buildToken(Map<String, Object> extraClaims, String username) {
        long expirationInMillis = 1000L * 60 * 60 * 24 * 365 * 10 ; 
        return Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expirationInMillis))
                .signWith(getKey(), SignatureAlgorithm.HS256)
                .compact();
    }


    
}