package com.example.Wordle.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor public class CustomDataNotFoundException extends RuntimeException
{
    private String message;
}
