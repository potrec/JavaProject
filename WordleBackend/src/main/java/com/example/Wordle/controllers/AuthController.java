package com.example.Wordle.controllers;

import com.example.Wordle.dtos.LoginDTO;
import com.example.Wordle.dtos.UserLoginResponseDTO;
import com.example.Wordle.dtos.UserRegistrationDTO;
import com.example.Wordle.exceptions.DataNotFoundException;
import com.example.Wordle.exceptions.ValidationException;
import com.example.Wordle.responses.ApiResponse;
import com.example.Wordle.services.Auth.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@CrossOrigin("*")
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ApiResponse<?> createUser(@Valid @RequestBody UserRegistrationDTO body, BindingResult result) {
        if (result.hasErrors()) {
            throw new ValidationException(result.getAllErrors()
                    .stream()
                    .map(ObjectError::getDefaultMessage)
                    .collect(Collectors.toList()), "Validation Failed");
        }
        try {
            authService.registerUser(body);
        }
        catch(Exception e)
        {
            throw new DataNotFoundException(e.getMessage());
        }
        return new ApiResponse<>(true,HttpStatus.OK,"User created successfully",null);
    }

    @PostMapping("/login")
    public ApiResponse<?> login(@Valid @RequestBody LoginDTO loginDTO) {
        UserLoginResponseDTO user = authService.login(loginDTO);
        return new ApiResponse<>(true, HttpStatus.OK, "User logged in successfully", user);
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<?> handleValidationException(ValidationException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getErrorResponse());
    }
}
