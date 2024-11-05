package com.example.Wordle.controllers;

import com.example.Wordle.dtos.GameDTO;
import com.example.Wordle.dtos.GuessWordDTO;
import com.example.Wordle.dtos.WordDTO;
import com.example.Wordle.exceptions.DataNotFoundException;
import com.example.Wordle.exceptions.ValidationException;
import com.example.Wordle.models.Game;
import com.example.Wordle.models.Word;
import com.example.Wordle.repository.GameGuessRepository;
import com.example.Wordle.repository.GameRepository;
import com.example.Wordle.responses.ApiResponse;
import com.example.Wordle.services.Auth.AuthService;
import com.example.Wordle.services.Game.GameService;
import com.example.Wordle.services.Word.WordService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
    GameService gameService;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ApiResponse<?> createGame() {
        WordDTO wordDTO = new WordDTO(wordService.getRandomWord().getWord());
        Word word = wordDTO.getWord().get();

        return new ApiResponse<>(true, HttpStatus.CREATED,"Game created",gameService.createGame(word));
    }

    @RequestMapping(value = "/guess" , method = RequestMethod.POST)
    public ApiResponse<?> guessWord(@Valid @RequestBody GuessWordDTO body, BindingResult result) {
        if (result.hasErrors()) {
            throw new ValidationException(result.getAllErrors()
                    .stream()
                    .map(ObjectError::getDefaultMessage)
                    .collect(Collectors.toList()), body.getWord());
        }

        Game game = gameRepository.findById(body.getGameId()).orElseThrow(() -> new DataNotFoundException("Game not found"));

        if(game.isFinished()) {
            throw new DataNotFoundException("Game is already finished");
        }

        return new ApiResponse<>(true, HttpStatus.OK,"Word guessed",gameService.guessWord(body, game));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ApiResponse<?> getGameById(@PathVariable int id) {
        Game game = gameRepository.findById((long) id).orElseThrow(() -> new DataNotFoundException("Game not found"));
        GameDTO gameDTO = new GameDTO(game);

        return new ApiResponse<>(true, HttpStatus.OK,"Game found",gameDTO);
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ApiResponse<?> getAllGames() {
        List<GameDTO> gameDtos = gameRepository.findAll().stream().map(GameDTO::new).collect(Collectors.toList());

        return new ApiResponse<>(true, HttpStatus.OK,"Games found",gameDtos);
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public ApiResponse<?> getGamesByUser() {
        List<GameDTO> gameDtos = gameRepository.findByUser(authService.getAuthUser()).orElseThrow(() -> new DataNotFoundException("Games not found"))
                .stream().map(GameDTO::new).collect(Collectors.toList());

        return new ApiResponse<>(true, HttpStatus.OK,"Games found",gameDtos);
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<?> handleValidationException(ValidationException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getErrorResponse());
    }

    @ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity<?> handleDataNotFoundException(DataNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getErrorResponse());
    }
}
