package com.vijay.spring_security_jwt.config;

import com.vijay.spring_security_jwt.entity.TokenResponse;
import com.vijay.spring_security_jwt.entity.Users;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JWTService {

    private  String secretKey = "";

    public JWTService() {
        try {
            KeyGenerator keyGen = KeyGenerator.getInstance("HmacSHA256");
            SecretKey sk = keyGen.generateKey();
            secretKey = Base64.getEncoder().encodeToString(sk.getEncoded());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public TokenResponse generateToken(Users user) {

        Map<String, Object> claims = new HashMap<>();
        Date issueTime = new Date(System.currentTimeMillis());
        Date expiryTime = new Date(System.currentTimeMillis() + 1000 * 60 * 60);

        String token =  Jwts.builder()
                        .claims()
                        .add(claims)
                        .subject(user.getUserName())
                        .issuedAt(issueTime)
                        .expiration(expiryTime)
                        .and()
                        .signWith(getKey())
                        .compact();

        return new TokenResponse(token,"Bearer", issueTime, expiryTime);

    }

    public String getUserNameFromToken(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public boolean validateToken(String token, UserDetails userDetail) {
        final String userName = getUserNameFromToken(token);

        
        return (userDetail.getUsername().equals(userName) && !isTokeExpired(token));
    }

    private boolean isTokeExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimResolver) {
        final Claims claims = extractAllClaims(token);
        return claimResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(getKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    private SecretKey getKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
