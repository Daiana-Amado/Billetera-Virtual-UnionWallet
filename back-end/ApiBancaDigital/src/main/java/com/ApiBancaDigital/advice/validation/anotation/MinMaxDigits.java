package com.ApiBancaDigital.advice.validation.anotation;


import com.ApiBancaDigital.advice.validation.validator.MinMaxDigitsValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({
        ElementType.FIELD,
        ElementType.PARAMETER,
        ElementType.METHOD,
        ElementType.ANNOTATION_TYPE,
        ElementType.TYPE_USE
})
@Documented
@Constraint(validatedBy = MinMaxDigitsValidator.class)
public @interface MinMaxDigits {
    int min() default 1;
    int max() default 9;
    //String message() default "Value must have between {min} and {max} digits (inclusive)";
    String message() default "{validator.minMaxDigits.customMessage}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
