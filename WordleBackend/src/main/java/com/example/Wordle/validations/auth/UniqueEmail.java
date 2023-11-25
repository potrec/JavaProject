package com.example.Wordle.validations.auth;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueEmailValidator.class)
public @interface UniqueEmail {
    String message() default "This email already exists";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

