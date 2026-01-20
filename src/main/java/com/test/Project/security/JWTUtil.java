package com.test.Project.security;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.util.Date;


@Component
public class JWTUtil {

    @Value("${jwt_secret}")
    private String secretKey;
    public String generateToken(String username, int id)
    {
        Date date = Date.from(ZonedDateTime.now().plusMinutes(60).toInstant());//текущая дата + 60 минут
        return JWT.create()
                .withSubject("User Details")//что именно хранится в токене
                .withClaim("username",username)
                .withClaim("id", id)
                .withIssuedAt(new Date())//время когда выдан токен
                .withIssuer("spring app")//кто выдал этот токен
                .withExpiresAt(date)// сказали токену, что действителен 60 минут
                .sign(Algorithm.HMAC256(secretKey));
    }

    public String validateToken(String token)
    {
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secretKey))
                .withSubject("User Details")
                .withIssuer("spring app")
                .build();
        DecodedJWT decodedJWT = verifier.verify(token);
        return decodedJWT.getClaim("username").asString();
    }

    public Integer validateTokenId(String token)
    {
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secretKey))
                .withSubject("User Details")
                .withIssuer("spring app")
                .build();
        DecodedJWT decodedJWT = verifier.verify(token);
        return decodedJWT.getClaim("id").asInt();
    }
}
