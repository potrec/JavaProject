package com.example.Wordle.services.Game;

import com.example.Wordle.dtos.GameDTO;
import com.example.Wordle.dtos.GameStateDTO;
import com.example.Wordle.dtos.GuessWordDTO;
import com.example.Wordle.models.Game;
import com.example.Wordle.models.Word;

public interface GameService {
    //create a new word guessing game
    GameDTO createGame(Word word);
    GameStateDTO guessWord(GuessWordDTO body, Game game);
}
