package com.example.Wordle.controllers;

import com.example.Wordle.dtos.GuessWordDTO;
import com.example.Wordle.dtos.UserRegistrationDTO;
import com.example.Wordle.dtos.WordDTO;
import com.example.Wordle.exceptions.CustomDataNotFoundException;
import com.example.Wordle.exceptions.ValidationException;
import com.example.Wordle.models.User;
import com.example.Wordle.models.Word;
import com.example.Wordle.repository.UserRepository;
import com.example.Wordle.repository.WordRepository;
import com.example.Wordle.services.Auth.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@CrossOrigin("*")
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private WordRepository wordRepository;
    @PostMapping("/users")
    public ResponseEntity<?> getAllUsers() {
        return ResponseEntity.ok(userRepository.findAll());
    }
}
