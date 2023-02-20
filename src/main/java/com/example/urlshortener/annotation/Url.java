package com.example.urlshortener.annotation;

import com.example.urlshortener.annotation.constraint.UrlValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = UrlValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Url {
    String message() default "Invalid url format";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
