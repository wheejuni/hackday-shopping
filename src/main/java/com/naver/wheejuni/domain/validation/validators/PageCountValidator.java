package com.naver.wheejuni.domain.validation.validators;

import com.naver.wheejuni.domain.validation.annotation.MinimalPageCount;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PageCountValidator implements ConstraintValidator<MinimalPageCount, Integer> {

    @Override
    public boolean isValid(Integer integer, ConstraintValidatorContext constraintValidatorContext) {
        return integer > 2;
    }
}
