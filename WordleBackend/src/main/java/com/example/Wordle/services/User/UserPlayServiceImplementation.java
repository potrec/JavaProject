package com.example.Wordle.services.User;

import com.example.Wordle.dtos.WordDTO;
import com.example.Wordle.exceptions.DataNotFoundException;
import com.example.Wordle.models.Word;
import com.example.Wordle.repository.WordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserPlayServiceImplementation implements UserPlayService {
    @Autowired
    private WordRepository wordRepository;

    @Override
    public WordDTO getRandomWord() {
        List<Word> allWords = new ArrayList<>();
        wordRepository.findAll().forEach(allWords::add);
        Random rand = new Random();
        Word word = allWords.get(rand.nextInt(allWords.size()));
        return new WordDTO(Optional.ofNullable(word));
    }

    @Override
    public Iterable<WordDTO> getAllWords() {
        List<WordDTO> allWords = new ArrayList<>();
        wordRepository.findAll().forEach(word -> allWords.add(new WordDTO(Optional.ofNullable(word))));
        return allWords;
    }

    @Override
    public WordDTO getWordById(int id) throws DataNotFoundException {
        return new WordDTO(Optional.ofNullable(wordRepository.findById(id).orElseThrow(() -> new DataNotFoundException("Word not found"))));
    }

    @Override
    public WordDTO getWordByWord(String word){
        return new WordDTO(Optional.ofNullable(wordRepository.findByWord(word).orElseThrow(() -> new DataNotFoundException("Word not found"))));
    }

}
