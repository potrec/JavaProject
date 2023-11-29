package com.example.Wordle.controllers;

import com.example.Wordle.dtos.LoginDTO;
import com.example.Wordle.dtos.UserLoginResponseDTO;
import com.example.Wordle.dtos.UserRegistrationDTO;
import com.example.Wordle.exceptions.CustomDataNotFoundException;
import com.example.Wordle.exceptions.ValidationException;
import com.example.Wordle.models.User;
import com.example.Wordle.services.Auth.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import java.util.stream.Collectors;

@RestController
@CrossOrigin("*")
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;
    @PostMapping("/")
    public ResponseEntity<?> test()
    {
        return ResponseEntity.ok("dfghdfghdfghdfg");
    }

    @PostMapping("/register")
    public ResponseEntity<?> createUser(@Valid @RequestBody UserRegistrationDTO body, BindingResult result) {
        if (result.hasErrors()) {
            throw new ValidationException(result.getAllErrors()
                    .stream()
                    .map(ObjectError::getDefaultMessage)
                    .collect(Collectors.toList()), "Validation Failed");
        }
        User createdUser;
        try {
            createdUser = authService.registerUser(body);
        }
        catch(Exception e)
        {
            throw new CustomDataNotFoundException(e.getMessage());
        }
        return ResponseEntity.ok(createdUser);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginDTO loginDTO) {
        UserLoginResponseDTO user = authService.login(loginDTO);
        return ResponseEntity.ok(user);
    }
}
