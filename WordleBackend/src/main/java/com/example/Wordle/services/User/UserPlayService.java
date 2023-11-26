package com.example.Wordle.services.User;

import com.example.Wordle.dtos.WordDTO;
import com.example.Wordle.exceptions.DataNotFoundException;
import com.example.Wordle.models.Word;

public interface UserPlayService {
    WordDTO getRandomWord();
    Iterable<WordDTO> getAllWords();
    WordDTO getWordById(int id) throws DataNotFoundException;
    WordDTO getWordByWord(String word) throws DataNotFoundException;
}
