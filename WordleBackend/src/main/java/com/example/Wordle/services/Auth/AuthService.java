package com.example.Wordle.services.Auth;

import com.example.Wordle.dtos.LoginDTO;
import com.example.Wordle.dtos.UserLoginResponseDTO;
import com.example.Wordle.dtos.UserRegistrationDTO;
import com.example.Wordle.models.User;
import org.springframework.web.bind.annotation.RestController;


@RestController
public interface AuthService {

    User registerUser(UserRegistrationDTO userRegistrationDTO);
    UserLoginResponseDTO login(LoginDTO loginDTO);


}