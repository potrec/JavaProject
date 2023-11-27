package com.example.Wordle.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GameStateDTO {
    private Long gameId;
    private Long gameGuessId;
    private Long userId;
    boolean finished;
    boolean status;
    private List<Integer> wordGuesses;
}
