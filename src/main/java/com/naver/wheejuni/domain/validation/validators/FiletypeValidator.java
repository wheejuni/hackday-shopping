package com.naver.wheejuni.domain.validation.validators;

import com.naver.wheejuni.domain.validation.annotation.EligibleFileTypes;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class FiletypeValidator implements ConstraintValidator<EligibleFileTypes, String> {

    @Override
    public void initialize(EligibleFileTypes constraintAnnotation) {

    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return false;
    }
}
