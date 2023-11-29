package com.example.Wordle.dtos;

import com.example.Wordle.validations.auth.UniqueEmail;
import com.example.Wordle.validations.auth.UniqueUsername;
import com.example.Wordle.validations.auth.ValidPassword;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
public class UserRegistrationDTO {
    @NotBlank(message = "Username cannot be blank")
    @UniqueUsername
    private String username;
    @NotBlank(message = "Password cannot be blank")
    @ValidPassword
    private String password;
    @NotBlank(message = "Username cannot be blank")
    @UniqueEmail
    @Email
    private String email;
}
