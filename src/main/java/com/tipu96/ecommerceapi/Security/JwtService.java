package com.tipu96.ecommerceapi.Security;

import com.tipu96.ecommerceapi.Models.User;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.spec.SecretKeySpec;
import java.util.Date;
@Service
public class JwtService {

    private static final SignatureAlgorithm ALGORITHM = SignatureAlgorithm.HS256;

    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.expiration}")
    private long expirationMillis;

    public String generateToken(User user) {
        Date now = new Date();
        Date expiration = new Date(now.getTime() + expirationMillis);

        JwtBuilder builder = Jwts.builder()
                .setSubject(user.getEmail())
                .claim("isAdmin", user.isAdmin())
                .claim("userId", user.getId())
                .setIssuedAt(now)
                .setExpiration(expiration)
                .signWith(SignatureAlgorithm.HS256, new SecretKeySpec(secretKey.getBytes(), ALGORITHM.getJcaName()));
        return builder.compact();
    }

    public boolean validateToken(String token) {
        try {
            Jws<Claims> claimsJws = Jwts.parser()
                    .setSigningKey(new SecretKeySpec(secretKey.getBytes(), ALGORITHM.getJcaName()))
                    .parseClaimsJws(token);

            Claims claims = claimsJws.getBody();
            String subject = claims.getSubject();

            return subject != null && !claims.getExpiration().before(new Date());
        } catch (JwtException e) {
            return false;
        }
    }

    public String getRoleFromToken(String token) {
        Jws<Claims> claimsJws = Jwts.parser()
                .setSigningKey(new SecretKeySpec(secretKey.getBytes(), ALGORITHM.getJcaName()))
                .parseClaimsJws(token);
        Claims claims = claimsJws.getBody();
        return claims.get("isAdmin", Boolean.class) ? "ROLE_ADMIN" : "ROLE_USER";
    }

    public String getUserIdFromToken(String token) {
        Jws<Claims> claimsJws = Jwts.parser()
                .setSigningKey(new SecretKeySpec(secretKey.getBytes(), ALGORITHM.getJcaName()))
                .parseClaimsJws(token);
        Claims claims = claimsJws.getBody();
        return (String) claims.get("userId");
    }
}