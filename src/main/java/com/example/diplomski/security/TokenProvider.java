package com.example.diplomski.security;

import com.example.diplomski.config.AppProperties;
import com.example.diplomski.exceptions.InvalidAccessTokenException;
import com.example.diplomski.model.User;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.sql.Date;
import java.time.Instant;
import java.util.UUID;

@Service
public class TokenProvider {
    private final AppProperties.Auth authProperties;

    public TokenProvider(AppProperties appProperties) {
        this.authProperties = appProperties.getAuth();
    }

    public String createAccessToken(Authentication authentication) {
        User user = (User) authentication.getPrincipal();

        Instant now = Instant.now();
        Instant expiresAt = now.plusSeconds(authProperties.getTokenExpirationSeconds());

        return Jwts.builder()
                .setSubject(user.getId().toString())
                .setIssuedAt(Date.from(now))
                .setExpiration(Date.from(expiresAt))
                .signWith(getKey())
                .compact();
    }

    public UUID getUserIdFromToken(String token) {
        JwtParser parser = Jwts.parserBuilder().setSigningKey(getKey()).build();
        Claims claims = parser.parseClaimsJws(token).getBody();

        return UUID.fromString(claims.getSubject());
    }

    public Claims readClaims(String token) {
        try {
            JwtParser parser = Jwts.parserBuilder().setSigningKey(getKey()).build();
            return parser.parseClaimsJws(token).getBody();
        } catch (IllegalArgumentException e) {
            throw new InvalidAccessTokenException("Access token is not provided.");
        } catch (ExpiredJwtException e) {
            throw new InvalidAccessTokenException("Access token has expired.");
        } catch (UnsupportedJwtException e) {
            throw new InvalidAccessTokenException("Access token format is unsupported.");
        } catch (MalformedJwtException e) {
            throw new InvalidAccessTokenException("Access token is malformed.");
        } catch (SignatureException e) {
            throw new InvalidAccessTokenException("Access token signature is invalid.");
        }
    }

    public void validateToken(String token) {
        try {
            readClaims(token);
        } catch (ExpiredJwtException e) {
            throw new InvalidAccessTokenException("Access token has expired.");
        }
    }

    private Key getKey() {
        byte[] keyBytes = authProperties.getTokenSecret().getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }

}
