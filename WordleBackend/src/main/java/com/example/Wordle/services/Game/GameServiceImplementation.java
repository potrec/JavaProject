package com.example.Wordle.services.Game;

import com.example.Wordle.dtos.GameDTO;
import com.example.Wordle.dtos.GameStateDTO;
import com.example.Wordle.dtos.GuessWordDTO;
import com.example.Wordle.models.Game;
import com.example.Wordle.models.GameGuess;
import com.example.Wordle.models.Word;
import com.example.Wordle.repository.GameGuessRepository;
import com.example.Wordle.repository.GameRepository;
import com.example.Wordle.services.Auth.AuthService;
import com.example.Wordle.services.Word.WordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class GameServiceImplementation implements GameService {

    @Autowired
    GameRepository gameRepository;

    @Autowired
    WordService wordService;

    @Autowired
    AuthService authService;

    @Autowired
    GameGuessRepository gameGuessRepository;

    public GameDTO createGame(Word word) {
        Game game = new Game();
        game.setWord(word);
        game.setAttempts(0);
        game.setStatus(true);
        game.setFinished(false);
        game.setDate(LocalDateTime.now());
        game.setUser(authService.getAuthUser());
        gameRepository.save(game);
        return new GameDTO(game.getId(), game.getUser().getUserId(), word.getWord(), game.getAttempts(), true,game.getDate());
    }

    public GameStateDTO guessWord(GuessWordDTO body, Game game) {
        game.setAttempts(game.getAttempts() + 1);
        GameGuess wordGuess = new GameGuess();
        wordGuess.setGame(game);
        wordGuess.setGuessNumber(game.getAttempts());
        wordGuess.setWord(body.getWord());
        wordGuess.setDate(LocalDateTime.now());
        boolean areEqual = Objects.equals(game.getWord().getWord(), body.getWord());
        wordGuess.setResult(areEqual);
        gameGuessRepository.save(wordGuess);
        GameStateDTO gameStateDTO = new GameStateDTO();
        gameStateDTO.setStatus(false);
        if(game.getAttempts() == 5)
        {
            game.setFinished(true);
            game.setStatus(false);
            gameStateDTO.setFinished(true);
            gameStateDTO.setStatus(false);
        }
        if(areEqual) {
            game.setStatus(true);
            game.setFinished(true);
            gameStateDTO.setStatus(true);
            gameStateDTO.setFinished(true);
        }
        gameRepository.save(game);
        gameGuessRepository.save(wordGuess);
        gameStateDTO.setGameId(game.getId());
        gameStateDTO.setUserId(game.getUser().getUserId());
        gameStateDTO.setGameGuessId(wordGuess.getGameGuessId());
        List<Integer> charStatus = getCharStatus(body, game);
        gameStateDTO.setWordGuesses(charStatus);
        return gameStateDTO;
    }

    private List<Integer> getCharStatus(GuessWordDTO body, Game game) {
        List<Integer> charStatus = new ArrayList<>();
        String targetWord = game.getWord().getWord();
        String guessedWord = body.getWord();
        for (int i = 0; i < guessedWord.length(); i++) {
            char guessedChar = guessedWord.charAt(i);
            char targetChar = targetWord.charAt(i);
            if (guessedChar == targetChar) {
                charStatus.add(2);
            } else if (targetWord.contains(String.valueOf(guessedChar))) {
                charStatus.add(1);
            } else {
                charStatus.add(0);
            }
        }
        return charStatus;
    }
}

