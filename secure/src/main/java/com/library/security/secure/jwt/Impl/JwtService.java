package com.library.security.secure.jwt.Impl;

import com.library.security.secure.jwt.IJwtService;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.List;


@Service
public class JwtService implements IJwtService {

    @Value("${secure.config.days}")
    private int expirationDays;
    @Value("${secure.config.secretkey}")
    private String secretKey;

    @Override
    public String generateToken(String email, List<String> roles) {
        return  Jwts
                .builder()
                .subject(email)
                .claim("role",roles)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + this.expireDate(this.expirationDays)))
                .signWith(this.getSignKey())
                .compact();
    }

    
    /////////////////////////////////////////////////////////////////

    private SecretKey getSignKey() {
        byte[] keyBytes = Decoders.BASE64URL.decode(this.secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    private int expireDate(int day){
        return day * 24 * 60 * 60 * 1000;
    }
    
    
}
