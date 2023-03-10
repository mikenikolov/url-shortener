package com.example.urlshortener.annotation.constraint.impl;

import com.example.urlshortener.annotation.constraint.Url;
import org.springframework.beans.factory.annotation.Value;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UrlValidator implements ConstraintValidator<Url, String> {
    @Value("${url-regex}")
    private String regex;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value.matches(regex);
    }
}
