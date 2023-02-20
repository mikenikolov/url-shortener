package com.example.urlshortener.annotation.constraint;

import com.example.urlshortener.annotation.CustomUrl;
import org.springframework.beans.factory.annotation.Value;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CustomUrlValidator implements ConstraintValidator<CustomUrl, String> {
    @Value("${custom-url-regex}")
    private String regex;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value.matches(regex);
    }
}
