package com.example.Wordle.dtos;

import com.example.Wordle.models.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Optional;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserLoginResponseDTO {
    private Optional<User> user;
    private String jwt;
}
