package com.ApiBancaDigital.advice.validation.validator;

import com.ApiBancaDigital.advice.validation.anotation.MinMaxDigits;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class MinMaxDigitsValidator implements ConstraintValidator<MinMaxDigits,Long> {

    private int min;
    private int max;

    @Override
    public void initialize(MinMaxDigits constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
        this.min = constraintAnnotation.min();
        this.max = constraintAnnotation.max();

    }

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        double log10 = Math.log10(value.doubleValue());
        int numDigits = (int) Math.floor(log10) + 1;

        if (!(value instanceof Long)) {
            return false;
        }

        if(numDigits < min){
            return false;
        }
        if(numDigits > max){
            return false;
        }

        return true;
    }

}
