package com.example.Wordle.dtos;

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
}
