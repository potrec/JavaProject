package com.example.Wordle.validations.word;

import com.example.Wordle.dtos.GuessWordDTO;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class FiveCharWordValidator implements ConstraintValidator<FiveCharWord, String> {

    @Override
    public boolean isValid(String word, ConstraintValidatorContext context){
        if (word == null) {
            return false;
        }
        int length = word.length();
        return length == 5;
    }
}
