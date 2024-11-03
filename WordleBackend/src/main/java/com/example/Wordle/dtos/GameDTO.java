package com.example.Wordle.dtos;

import com.example.Wordle.models.Game;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

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
    private LocalDateTime date;

    public GameDTO(Game game) {
        this.gameId = game.getId();
        this.userId = game.getUser().getUserId();
        this.word = game.getWord().getWord();
        this.attempts = game.getAttempts();
        this.status = game.isStatus();
        this.date = game.getDate();
    }
}
