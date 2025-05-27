package com.backend.vetApp.Config.Utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Date;
import java.util.Map;

@Getter
@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secret;

    public  String generateToken(Map<String,String> claims){
        byte[] decodedKey = Base64.getDecoder().decode(getSecret());
        SecretKey key = Keys.hmacShaKeyFor(decodedKey);
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(claims.get("id"))
                .setExpiration(new Date( System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // 10 hours
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();

    }


    public Jws<Claims> validateToken(String token) {
        try {
            return  Jwts.parserBuilder().setSigningKey(getSecret()).build().parseClaimsJws(token);
        }catch (Exception e) {
            throw new RuntimeException("Invalid JWT token", e);
        }

    }
}
