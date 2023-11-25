package com.example.Wordle.dtos;

import com.example.Wordle.validations.ValidPassword;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class UserRegistrationDTO {
    @NotBlank(message = "Username cannot be blank")
    private String username;
    @NotBlank(message = "Password cannot be blank")
    @ValidPassword
    private String password;
    @NotBlank(message = "Username cannot be blank")
    @Email
    private String email;
}
