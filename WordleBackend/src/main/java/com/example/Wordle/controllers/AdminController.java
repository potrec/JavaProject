package com.example.Wordle.controllers;

import com.example.Wordle.repository.UserRepository;
import com.example.Wordle.repository.WordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


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
