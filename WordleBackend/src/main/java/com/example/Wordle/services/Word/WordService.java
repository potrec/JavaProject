package com.example.Wordle.services.Word;

import com.example.Wordle.dtos.WordDTO;
import com.example.Wordle.exceptions.DataNotFoundException;

public interface WordService {
    WordDTO getRandomWord();
    Iterable<WordDTO> getAllWords();
    WordDTO getWordById(int id) throws DataNotFoundException;
    WordDTO getWordByWord(String word) throws DataNotFoundException;
}
