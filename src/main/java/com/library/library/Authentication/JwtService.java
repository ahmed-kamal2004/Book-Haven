package com.library.library.Authentication;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.function.Function;

@Service
public class JwtService {
    private final String SECRET_KEY = "dkfnfdnkglnsfwr9r8409roifhofdisufherotjrfiofjer3498uty489hf893hfe98fh489h8fe9rh3498erh8f8934herh89fhr8989fh3489hfuerbufer";
    private final int ExperationTime = 15 * 24 * 60 * 60; // 15 days

    public String extractUsername(String token){
        return this.extractClaim(token,Claims::getSubject);
    }

    public String generateToken(UserDetails user){
        return Jwts.builder().subject(user.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + this.ExperationTime))
                .signWith(this.getSignKey())
                .compact();
    }

    public boolean isValidToken(String token,UserDetails user){
        return extractUsername(token).equals(user.getUsername()) && expirationDate(token).after(new Date(System.currentTimeMillis()));
    }
    private <T> T extractClaim(String token , Function<Claims,T> extractor){
        Claims claims = this.extractClaims(token);
        return extractor.apply(claims);
    }
    private Claims extractClaims(String token){
        return Jwts
                .parser()
                .verifyWith(this.getSignKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    private Date expirationDate(String token){
        return extractClaim(token,Claims::getExpiration);
    }

    private SecretKey getSignKey(){
        byte [] keyBytes = Decoders.BASE64URL.decode(this.SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
