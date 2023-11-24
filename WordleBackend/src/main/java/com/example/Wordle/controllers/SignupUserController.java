package com.example.Wordle.controllers;

import com.example.Wordle.dtos.LoginDTO;
import com.example.Wordle.dtos.SignupDTO;
import com.example.Wordle.dtos.UserDTO;
import com.example.Wordle.response.ErrorResponse;
import com.example.Wordle.services.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class SignupUserController {
    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<?> createUser(@Valid @RequestBody SignupDTO signupDTO, BindingResult result) {
        UserDTO createdUser = authService.createUser(signupDTO);
        if (result.hasErrors()) {
            List<String> details = result.getAllErrors().stream()
                    .map(ObjectError::getDefaultMessage)
                    .collect(Collectors.toList());
            ErrorResponse errorResponse = new ErrorResponse("Validation Failed", details);
            return ResponseEntity.badRequest().body(errorResponse);
        }
        if(createdUser == null)
        {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(createdUser);

    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid  @RequestBody LoginDTO loginDTO) {
        UserDTO user = authService.login(loginDTO);
        if(user == null)
        {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(user);
    }
}
