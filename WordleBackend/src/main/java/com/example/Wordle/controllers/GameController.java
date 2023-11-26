package com.example.Wordle.controllers;

import com.example.Wordle.models.Game;
import com.example.Wordle.repository.GameRepository;
import com.example.Wordle.repository.UserGameGuessRepository;
import com.example.Wordle.repository.WordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/game")
public class GameController {
    @Autowired
    private GameRepository gameRepository;
    @Autowired
    private UserGameGuessRepository userGameGuessRepository;
    @Autowired
    private WordRepository wordRepository;
    @RequestMapping(value = "/start", method = RequestMethod.POST)
    public String startGame() {
        Game game = new Game();
        game.word(wordRepository.findById(1));
        gameRepository.save(game);
    }
}
