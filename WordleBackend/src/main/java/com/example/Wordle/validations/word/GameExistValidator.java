package com.example.Wordle.validations.word;

import com.example.Wordle.repository.GameRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class GameExistValidator implements ConstraintValidator<GameExist, Long>{
    @Autowired
    private GameRepository gameRepository;
    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        if (value == null) {
            return false;
        }
        return gameRepository.existsById(value);
    }
}

