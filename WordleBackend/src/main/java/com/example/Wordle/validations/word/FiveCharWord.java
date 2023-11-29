package com.example.Wordle.validations.word;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = FiveCharWordValidator.class)
public @interface FiveCharWord {
    String message() default "This word must be 5 characters";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}