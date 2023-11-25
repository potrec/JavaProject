package com.example.Wordle.controllers;

import com.example.Wordle.dtos.LoginDTO;
import com.example.Wordle.dtos.UserLoginResponseDTO;
import com.example.Wordle.dtos.UserRegistrationDTO;
import com.example.Wordle.models.User;
import com.example.Wordle.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

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
    public ResponseEntity<?> createUser(@RequestBody UserRegistrationDTO body) {
//        if (result.hasErrors()) {
//            List<String> details = result.getAllErrors().stream()
//                    .map(ObjectError::getDefaultMessage)
//                    .collect(Collectors.toList());
//            ErrorResponse errorResponse = new ErrorResponse("Validation Failed", details);
//            return ResponseEntity.badRequest().body(errorResponse);
//        }
        User createdUser = authService.registerUser(body);
        if(createdUser == null)
        {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(authService.registerUser(body));

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
