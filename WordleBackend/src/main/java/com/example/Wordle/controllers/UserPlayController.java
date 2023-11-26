package com.example.Wordle.controllers;

import com.example.Wordle.exceptions.DataNotFoundException;
import com.example.Wordle.services.User.UserPlayService;
import com.example.Wordle.services.User.UserPlayServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/word")
public class UserPlayController {
    @Autowired
    private UserPlayService userPlayService;

    @RequestMapping(value = "/random", method = RequestMethod.GET)
    public ResponseEntity<?> getRandomWord() {
        return ResponseEntity.ok(userPlayService.getRandomWord());
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResponseEntity<?> getAllWords() {
        return ResponseEntity.ok(userPlayService.getAllWords());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getWordById(@PathVariable int id){
        return ResponseEntity.ok(userPlayService.getWordById(id));
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<?> getWordByWord(@RequestParam String word){
        return ResponseEntity.ok(userPlayService.getWordByWord(word));
    }
}

