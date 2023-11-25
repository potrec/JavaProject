package com.example.Wordle.dtos;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class UserRegistrationDTO {
    private String username;
    private String password;
    private String email;
}
