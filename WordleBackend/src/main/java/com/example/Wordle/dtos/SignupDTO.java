package com.example.Wordle.dtos;

import com.example.Wordle.validations.auth.PasswordMatches;
import com.example.Wordle.validations.auth.ValidPassword;
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
    @Email
    private String email;

    public @NotBlank(message = "Password cannot be blank") String getConfirmPassword() {
        return null;
    }
}
