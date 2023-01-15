package com.example.urlshortener.exception.handler;

import com.example.urlshortener.exception.RedirectException;
import com.example.urlshortener.exception.RegistrationException;
import com.example.urlshortener.exception.AuthenticationException;
import com.example.urlshortener.exception.TakenCustomUrlException;
import com.example.urlshortener.exception.entity.ExceptionResponse;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = AuthenticationException.class)
    public ResponseEntity<Object> handleAuthException(Exception ex) {
        ExceptionResponse response = new ExceptionResponse()
                .setStatus(HttpStatus.UNAUTHORIZED.value())
                .setDateTime(LocalDateTime.now())
                .setErrors(List.of(ex.getMessage()));
        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(value = {RegistrationException.class, TakenCustomUrlException.class})
    public ResponseEntity<Object> handleDefaultConflictException(Exception ex) {
        ExceptionResponse response = new ExceptionResponse()
                .setStatus(HttpStatus.CONFLICT.value())
                .setDateTime(LocalDateTime.now())
                .setErrors(List.of(ex.getMessage()));
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = RedirectException.class)
    public ResponseEntity<Object> handleRedirectException(Exception ex) {
        ExceptionResponse response = new ExceptionResponse()
                .setStatus(HttpStatus.NOT_FOUND.value())
                .setDateTime(LocalDateTime.now())
                .setErrors(List.of(ex.getMessage()));
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest request) {
        List<String> errors = ex.getBindingResult()
                .getAllErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());
        ExceptionResponse response = new ExceptionResponse()
                .setStatus(HttpStatus.BAD_REQUEST.value())
                .setDateTime(LocalDateTime.now())
                .setErrors(errors);
        return new ResponseEntity<>(response, headers, HttpStatus.BAD_REQUEST);
    }
}
