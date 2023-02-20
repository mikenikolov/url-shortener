package com.example.urlshortener.annotation;

import com.example.urlshortener.annotation.constraint.CustomUrlValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = CustomUrlValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface CustomUrl {
    String message() default "Custom url contains not valid symbols";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
