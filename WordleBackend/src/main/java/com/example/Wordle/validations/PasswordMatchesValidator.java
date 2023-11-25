package com.example.Wordle.validations;

import com.example.Wordle.dtos.SignupDTO;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> {

    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext context){
        SignupDTO user = (SignupDTO) obj;
        return user.getPassword().equals(user.getConfirmPassword());
    }
}
