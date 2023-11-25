package com.example.Wordle.controllers;

import com.example.Wordle.dtos.LoginDTO;
import com.example.Wordle.dtos.UserLoginResponseDTO;
import com.example.Wordle.dtos.UserRegistrationDTO;
import com.example.Wordle.exceptions.CustomDataNotFoundException;
import com.example.Wordle.models.User;
import com.example.Wordle.response.ErrorResponse;
import com.example.Wordle.services.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;
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
        User createdUser;
        try {
            createdUser = authService.registerUser(body);
        }
        catch(Exception e)
        {
            throw new CustomDataNotFoundException(e.getMessage());
        }
//        if (result.hasErrors()) {
//            List<String> details = result.getAllErrors().stream()
//                    .map(ObjectError::getDefaultMessage)
//                    .collect(Collectors.toList());
//            ErrorResponse errorResponse = new ErrorResponse("Validation Failed", details);
//            return ResponseEntity.badRequest().body(errorResponse);
//        }
//
//        if(createdUser == null)
//        {
//            return ResponseEntity.badRequest().build();
//        }
        return ResponseEntity.ok(createdUser);

    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO) {
        UserLoginResponseDTO user = authService.login(loginDTO);
        if(user == null)
        {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(user);
    }
}
