package com.volvo.account.demo.infrastructure.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class EmaidValidator implements ConstraintValidator<Emaid, String> {

    // EMAID格式正则表达式，简化版
    private static final Pattern EMAID_PATTERN = Pattern.compile(
            "^[A-Z0-9]{3}\\.([A-Z0-9]{3}\\.){0,2}[A-Z0-9]{3,5}$"
    );

    @Override
    public void initialize(Emaid constraintAnnotation) {
        // 初始化方法
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return false;
        }
        return EMAID_PATTERN.matcher(value).matches();
    }
}
