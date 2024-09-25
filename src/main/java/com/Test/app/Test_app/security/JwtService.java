package com.Test.app.Test_app.security;

import com.Test.app.Test_app.constants.ConstantStrings;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import java.time.Instant;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;

@Service
public class JwtService {
    private final SecretKey SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256); // Use a strong key

    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return Jwts
                .builder()
                .claims(claims)
                .subject(userDetails.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + ConstantStrings.ACCESS_TOKEN_VALID_SEC))
                .signWith(SECRET_KEY)
                .compact();
    }

//    private SecretKey getSignInKey() {
//        byte[] keyBytes = Base64.getDecoder().decode(SECRET_KEY);
//        return Keys.hmacShaKeyFor(keyBytes);
//    }

    public long getExpirationTime() {
        return ConstantStrings.ACCESS_TOKEN_VALID_SEC;
    }
    public boolean isTokenValid(String token) {
        Claims claims=getClaims(token);
        return claims.getExpiration().after(Date.from(Instant.now()));
    }

    public String extractUsername(String token) {
        Claims claims = getClaims(token);
        return claims.getSubject();
    }

    private Claims getClaims(String token) {
        return Jwts
                .parser()
                .verifyWith(SECRET_KEY)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }


}
