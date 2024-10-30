package com.example.Wordle.dtos;

import com.example.Wordle.models.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserLoginResponseDTO {
    private UserResponseDTO user;
    private String jwt;
}
