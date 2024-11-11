package com.example.Wordle.dtos;

import com.example.Wordle.models.Game;
import com.example.Wordle.models.GameGuess;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GameDTO {
    private Long gameId;
    private Long userId;
    private String word;
    private Integer attempts;
    private boolean status;
    private boolean finished;
    private LocalDateTime date;
    private Set<GameGuess> gameGuesses;

    public GameDTO(Game game) {
        this.gameId = game.getId();
        this.userId = game.getUser().getUserId();
        this.word = game.getWord().getWord();
        this.attempts = game.getAttempts();
        this.status = game.isStatus();
        this.finished = game.isFinished();
        this.date = game.getDate();
        this.gameGuesses = game.getGameGuesses();
    }
}
