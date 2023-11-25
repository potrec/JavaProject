package com.example.Wordle.dtos;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class LoginDTO {
    private String username;
    private String password;
}
