package com.example.Wordle.controllers;

import com.example.Wordle.dtos.GameDTO;
import com.example.Wordle.dtos.GameStateDTO;
import com.example.Wordle.dtos.GuessWordDTO;
import com.example.Wordle.dtos.WordDTO;
import com.example.Wordle.exceptions.CustomDataNotFoundException;
import com.example.Wordle.exceptions.ValidationException;
import com.example.Wordle.models.Game;
import com.example.Wordle.models.GameGuess;
import com.example.Wordle.models.Word;
import com.example.Wordle.repository.GameGuessRepository;
import com.example.Wordle.repository.GameRepository;
import com.example.Wordle.services.Auth.AuthService;
import com.example.Wordle.services.Game.GameService;
import com.example.Wordle.services.Word.WordService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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

    @Autowired
    GameGuessRepository gameGuessRepository;

    @Autowired
    GameService gameService;
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<?> createGame() {
        WordDTO wordDTO = new WordDTO(wordService.getRandomWord().getWord());
        Word word = wordDTO.getWord().get();
        return ResponseEntity.ok(gameService.createGame(word));
    }

    @RequestMapping(value = "/guess" , method = RequestMethod.POST)
    public ResponseEntity<?> guessWord(@Valid @RequestBody GuessWordDTO body, BindingResult result) {
        if (result.hasErrors()) {
            throw new ValidationException(result.getAllErrors()
                    .stream()
                    .map(ObjectError::getDefaultMessage)
                    .collect(Collectors.toList()), body.getWord());
        }
        Game game = gameRepository.findById(body.getGameId()).orElseThrow(() -> new CustomDataNotFoundException("Game not found"));
        if(game.isFinished()) return ResponseEntity.ok("Game already finished");
        return ResponseEntity.ok(gameService.guessWord(body, game));
    }
}
