package com.ecommerce.reactivemoviehub.validation.validator;

import com.ecommerce.reactivemoviehub.validation.annotation.Year;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;

public class YearValidator implements ConstraintValidator<Year, Integer> {
    @Override
    public void initialize(Year constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Integer integer, ConstraintValidatorContext constraintValidatorContext) {
        if (integer == null) return false;
        int currentYear = LocalDate.now().getYear();
        return 1888 <= integer && integer <= currentYear;
    }
}
