package com.example.Wordle.dtos;

import com.example.Wordle.validations.PasswordMatches;
import com.example.Wordle.validations.ValidPassword;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@PasswordMatches
public class SignupDTO {
    @NotBlank(message = "Username cannot be blank")
    private String username;
    @NotBlank(message = "Password cannot be blank")
    @ValidPassword
    private String password;
    @NotBlank(message = "Email cannot be blank")
    @ValidPassword
    private String confirmPassword;
    @Email
    private String email;

}
