package com.example.urlshortener.security.filter;

import com.example.urlshortener.exception.entity.ExceptionResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Component
@AllArgsConstructor
public class JwtFilter extends GenericFilterBean {
    private JwtProvider provider;
    private ObjectMapper objectMapper;

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException {
        try {
            String jwt = provider.parse((HttpServletRequest) servletRequest);
            if (jwt != null && provider.validate(jwt)) {
                Authentication auth = provider.getAuthentication(jwt);
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
            filterChain.doFilter(servletRequest, servletResponse);

        } catch (Exception ex) {
            ExceptionResponse response = new ExceptionResponse()
                    .setStatus(HttpStatus.UNAUTHORIZED.value())
                    .setDateTime(LocalDateTime.now())
                    .setErrors(List.of(ex.getMessage()));
            servletResponse.setContentType("application/json");
            servletResponse.getWriter().write(objectMapper.writeValueAsString(response));
        }
    }
}
