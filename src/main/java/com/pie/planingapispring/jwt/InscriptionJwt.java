package com.pie.planingapispring.jwt;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class InscriptionJwt {

    @Value("${jwt.inscription.secret}")
    private String jwtSecret;
    @Value("${jwt.inscription.expiration.duration}")
    private long expirationMs;

    public String generateJwtToken(String email) {
        return Jwts
                .builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + this.expirationMs))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
        } catch (SignatureException e) {
            System.err.println("Invalid JWT signature: {} " + e.getMessage());
        } catch (MalformedJwtException e) {
            System.err.println("Invalid JWT token: {} " + e.getMessage());
        } catch (ExpiredJwtException e) {
            System.err.println("JWT token is expired: {} " + e.getMessage());
        } catch (UnsupportedJwtException e) {
            System.err.println("JWT token is unsupported: {} " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.err.println("JWT claims string is empty: {} " + e.getMessage());
        }
        return false;
    }

    public String getEmailFromToken(String token) {
        return Jwts
                .parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}