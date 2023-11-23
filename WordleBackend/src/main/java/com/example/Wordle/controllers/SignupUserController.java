package com.example.Wordle.controllers;

import com.example.Wordle.dtos.SignupRequest;
import com.example.Wordle.dtos.UserDTO;
import com.example.Wordle.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;

@RestController
public class SignupUserController {
    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<?> createUser(@RequestBody SignupRequest signupRequest) {
        UserDTO createdUser = authService.createUser(signupRequest);
        if(createdUser == null)
        {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(createdUser);

    }
}