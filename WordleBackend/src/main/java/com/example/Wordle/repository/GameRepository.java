package com.example.Wordle.repository;

import com.example.Wordle.models.Game;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GameRepository extends JpaRepository<Game, Long> {
    Optional<Game> findById(Long id);
}
