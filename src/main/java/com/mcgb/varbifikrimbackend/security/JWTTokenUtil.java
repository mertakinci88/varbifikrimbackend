package com.mcgb.varbifikrimbackend.security;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mcgb.varbifikrimbackend.entity.User;
import com.mcgb.varbifikrimbackend.util.exception.BusinessException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JWTTokenUtil implements Serializable {

    private static final long serialVersionUID = -2550185165626007488L;
    public static final long JWT_TOKEN_EXPIRATION_TIME = 30 * 60 * 1000; // 30 dk

    @Value("${jwt.secret}")
    private String secret;

    public String getRequestTokenHeader(String bearerToken) {
        return bearerToken.substring(7);
    }

    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    public Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    public Object getUserClaimFromToken(String authHeader, String key) {
        Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(authHeader.substring(7)).getBody();
        HashMap userClaims;
        try {
            userClaims = (new ObjectMapper()).readValue(claims.get("userInfo").toString(), HashMap.class);
        } catch (JsonProcessingException e) {
            return new BusinessException(e.getMessage());
        }
        return userClaims.get(key);
    }

    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    public String generateToken(User user) throws JsonProcessingException {
        Map<String, Object> claims = new HashMap<>();
        HashMap userClaims = new HashMap();
        userClaims.put("userId", user.getId().toString());
        userClaims.put("userName", user.getUsername());
        ObjectMapper objectMapper = new ObjectMapper();
        claims.put("userInfo", objectMapper.writeValueAsString(userClaims));
        return doGenerateToken(claims, user.getUsername());
    }

    private String doGenerateToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, secret).compact();
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}
