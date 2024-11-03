package com.example.Wordle.dtos;

import com.example.Wordle.validations.auth.ValidPassword;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserEditDto {
    @NotBlank(message = "Username cannot be blank")
    private String username;
    @NotBlank(message = "Password cannot be blank")
    @ValidPassword
    private String password;
    @Email
    private String email;
}
