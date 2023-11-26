package com.example.Wordle.repository;

import com.example.Wordle.models.UserGameGuess;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserGameGuessRepository extends JpaRepository<UserGameGuess, Long> {
    UserGameGuess findByUserGameGuessId(Long userGameGuessId);
}
