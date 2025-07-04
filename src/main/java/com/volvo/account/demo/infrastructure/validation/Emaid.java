package com.volvo.account.demo.infrastructure.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = EmaidValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface Emaid {
    String message() default "Invalid EMAID format";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}