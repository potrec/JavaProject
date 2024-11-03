package com.example.Wordle.repository;

import com.example.Wordle.models.Game;
import com.example.Wordle.models.User;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface GameRepository extends JpaRepository<Game, Long> {
    @NonNull
    Optional<Game> findById(@NonNull Long id);

    Optional<List<Game>> findByUser(User authUser);
}
