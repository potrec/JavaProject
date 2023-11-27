package com.example.Wordle.dtos;

import com.example.Wordle.validations.word.FiveCharWord;
import com.example.Wordle.validations.word.GameExist;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GuessWordDTO {
    @FiveCharWord
    private String word;
    @NotNull
    @GameExist
    private Long gameId;
}
