package com.example.Wordle.validations.auth;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PasswordValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidPassword {
    String message() default "Invalid Password, must be at least 8 characters in length";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

