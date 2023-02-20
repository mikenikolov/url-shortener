package com.example.urlshortener.security.filter;

import com.example.urlshortener.exception.entity.ExceptionResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Component
@AllArgsConstructor
public class JwtFilter extends OncePerRequestFilter {
    private JwtProvider provider;
    private ObjectMapper objectMapper;

    @Override
    protected void doFilterInternal(HttpServletRequest req,
                                    HttpServletResponse resp,
                                    FilterChain filterChain) throws IOException {
        try {
            String jwt = provider.parse(req);
            if (jwt != null && provider.validate(jwt)) {
                Authentication auth = provider.getAuthentication(jwt);
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
            filterChain.doFilter(req, resp);

        } catch (Exception ex) {
            ExceptionResponse exceptionResponse = new ExceptionResponse()
                    .setStatus(HttpStatus.UNAUTHORIZED.value())
                    .setDateTime(LocalDateTime.now())
                    .setErrors(List.of(ex.getMessage()));
            resp.setContentType("application/json");
            resp.setStatus(HttpStatus.UNAUTHORIZED.value());
            resp.getWriter().write(objectMapper.writeValueAsString(exceptionResponse));
        }
    }
}
