package com.example.to_do_liste.security;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {
    @Value("${jwt.secret}")             //variable in .properties festgelegt
    private String SECRET_KEY;
    @Value("${jwt.expiration}")         // expiration date in .properties festgelegt
    private long jwtExpirationMs;

    private Key key;

    @PostConstruct
    public void init() {
        this.key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8)); // den Secret_Key verschlüsseln
    }


    public String generateToken(String email) {
        return Jwts.builder()
                .setSubject(email)                      //identifiziert den benutzer eindeutig
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationMs))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public String extractUserEmail(String token) {
        return Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();                          //identifiziert den Nutzer anhand der email
    }
}
