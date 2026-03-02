package com.ecommerce.moviecore.validation.annotation;

import com.ecommerce.moviecore.validation.validator.YearValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = YearValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Year {
    String message() default "Release year cannot be in the future and before 1988";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
