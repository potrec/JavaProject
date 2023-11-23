package com.example.Wordle.services;

import com.example.Wordle.dtos.SignupDTO;
import com.example.Wordle.dtos.UserDTO;
import org.springframework.web.bind.annotation.RestController;


@RestController
public interface AuthService {

    UserDTO createUser(SignupDTO signupDTO);
}
