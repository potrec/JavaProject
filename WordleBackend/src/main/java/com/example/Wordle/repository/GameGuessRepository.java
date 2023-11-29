package com.example.Wordle.repository;

import com.example.Wordle.models.GameGuess;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface GameGuessRepository extends JpaRepository<GameGuess, Long> {
    Optional<GameGuess> findById(Long id);
}
