package com.capstone.user.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class StrongPasswordValidator
        implements ConstraintValidator<StrongPassword, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        if (value == null) return false;

        int length = value.length();

        return length >= 10 && length <= 15;
    }
}