package com.example.Wordle.repository;

import com.example.Wordle.exceptions.DataNotFoundException;
import com.example.Wordle.models.Word;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class WordRepositoryTest {
    @Autowired
    private WordRepository wordRepository;

    private static final String WORD = "Słowo";
    private static final Integer ID = 1;
    @Test
    void createWord()
    {
        Word word = new Word();
        word.setWord(WORD);
        Assertions.assertThat(word).isNotNull();

        Word savedWord = wordRepository.save(word);
        Assertions.assertThat(savedWord.getWordId()).isGreaterThan(0);
        Assertions.assertThat(savedWord.getWord()).hasSize(5);

    }

    @Test
    void findById() {
        Word word = wordRepository.findById(ID).orElseThrow(()-> new DataNotFoundException("Word:"+ WORD +" not found"));
        Assertions.assertThat(word.getWordId()).isGreaterThan(0);
        Assertions.assertThat(word.getWord()).hasSize(5);
    }
}