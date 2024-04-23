package com.example.springredditclone.security;

import com.example.springredditclone.exceptions.SpringRedditException;
import com.example.springredditclone.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.annotation.PostConstruct;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.security.*;
import java.security.cert.CertificateException;
import java.util.Date;
import java.util.function.Function;


@Service
public class JwtProvider {

    private String secret = "qwertythisisasecretkeyqwerty";

    public String generateToken(String username){
        Date now = new Date();
        Date expiryDate = new Date(now.getTime()+80000);

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512,secret)
                .compact();
    }

    public String getUsernameFromToken(String token){
        Claims claims = Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }

    public boolean validateToken(String token){
        try{
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return true;

        } catch (Exception e) {
            return false ;
        }
    }




}


