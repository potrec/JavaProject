package com.example.Wordle.controllers;

import com.example.Wordle.dtos.GuessWordDTO;
import com.example.Wordle.dtos.WordDTO;
import com.example.Wordle.exceptions.ValidationException;
import com.example.Wordle.models.Game;
import com.example.Wordle.models.Word;
import com.example.Wordle.repository.GameRepository;
import com.example.Wordle.services.Auth.AuthService;
import com.example.Wordle.services.Word.WordService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/game")
public class GameController {
    @Autowired
    GameRepository gameRepository;
    @Autowired
    WordService wordService;

    @Autowired
    AuthService authService;
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<?> createGame() {
        Game game = new Game();
        WordDTO wordDTO = new WordDTO(wordService.getRandomWord().getWord());
        Word word = wordDTO.getWord().get();
        game.setWord(word);
        game.setAttempts(0);
        game.setStatus(false);
        game.setDate(LocalDateTime.now());
        game.setUser(authService.getAuthUser());
        gameRepository.save(game);
        return ResponseEntity.ok(game);
    }

    @RequestMapping(value = "/guess" , method = RequestMethod.POST)
    public ResponseEntity<?> guessWord(@Valid @RequestBody GuessWordDTO body, BindingResult result) {
        if (result.hasErrors()) {
            throw new ValidationException(result.getAllErrors()
                    .stream()
                    .map(ObjectError::getDefaultMessage)
                    .collect(Collectors.toList()), body.getWord());
        }
        Game game = gameRepository.findById(body.getGameId()).get();
        return ResponseEntity.ok(game);
    }
}
