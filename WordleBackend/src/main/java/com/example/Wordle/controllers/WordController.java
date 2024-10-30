package com.example.Wordle.controllers;

import com.example.Wordle.services.Word.WordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/word")
public class WordController {
    @Autowired
    private WordService wordService;

    @RequestMapping(value = "/random", method = RequestMethod.GET)
    public ResponseEntity<?> getRandomWord() {
        return ResponseEntity.ok(wordService.getRandomWord());
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResponseEntity<?> getAllWords() {
        return ResponseEntity.ok(wordService.getAllWords());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getWordById(@PathVariable int id){
        return ResponseEntity.ok(wordService.getWordById(id));
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<?> getWordByWord(@RequestParam String word){
        if(word == null || word.isEmpty()){
            return ResponseEntity.badRequest().body("Word cannot be empty");
        }
        return ResponseEntity.ok(wordService.getWordByWord(word));
    }
}

