package com.example.urlshortener.security.filter;

import com.example.urlshortener.entity.dto.res.JwtResponseDto;
import com.example.urlshortener.exception.JwtTokenException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.security.Key;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Component
public class JwtProvider {
    @Value("${validity-time}")
    private long validityTime;
    private Key key;
    private final UserDetailsService userDetailsService;

    public JwtProvider(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @PostConstruct
    public void init() {
        key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    }

    public String generate(String username) {
        Date date = new Date();
        return Jwts.builder()
                .setSubject(username)
                .setExpiration(new Date(date.getTime() + validityTime))
                .setIssuedAt(date)
                .signWith(key)
                .compact();
    }

    public boolean validate(String jwt) throws JwtTokenException {
        try {
            Jws<Claims> claims = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(jwt);
            return !claims.getBody().getExpiration().before(new Date());
        } catch (JwtException | IllegalArgumentException ex) {
            throw new JwtTokenException("JWT is invalid or expired");
        }
    }

    public String parse(HttpServletRequest request) {
        String jwt = request.getHeader("Authorization");
        if (jwt != null && jwt.contains("Bearer ")) {
            return jwt.substring(7);
        }
        return null;
    }

    public String getUsername(String jwt) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(jwt)
                .getBody()
                .getSubject();
    }

    public JwtResponseDto generateResponse(String jwt) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(jwt)
                .getBody();
        LocalDateTime issuedAt =
                LocalDateTime.ofInstant(claims.getIssuedAt().toInstant(), ZoneId.systemDefault());
        LocalDateTime expiration =
                LocalDateTime.ofInstant(claims.getExpiration().toInstant(), ZoneId.systemDefault());
        return new JwtResponseDto()
                .setOwner(claims.getSubject())
                .setIssuedDate(issuedAt)
                .setExpirationDate(expiration)
                .setJwtToken(jwt);
    }

    public Authentication getAuthentication(String jwt) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(getUsername(jwt));
        return new UsernamePasswordAuthenticationToken(userDetails, "",
                userDetails.getAuthorities());
    }
}
