package com.example.Wordle.dtos;

import com.example.Wordle.models.Word;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.util.Optional;

@Getter
@Setter
@AllArgsConstructor
public class WordDTO {
    private Optional<Word> word;
}
