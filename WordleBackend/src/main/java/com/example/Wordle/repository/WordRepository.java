package com.example.Wordle.repository;

import com.example.Wordle.models.Word;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface WordRepository extends JpaRepository<Word, Integer> {
    Optional<Word> findByWord(String word);
    Optional<Word> findById(int id);
}
