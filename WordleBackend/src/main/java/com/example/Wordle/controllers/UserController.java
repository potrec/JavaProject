package com.example.Wordle.controllers;

import com.example.Wordle.models.User;
import com.example.Wordle.services.Auth.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/auth/user")
public class UserController {
    @Autowired
    AuthService authService;
    @RequestMapping(value = "/me", method = RequestMethod.GET)
    public ResponseEntity<?> getAuthUser() {
        User user = authService.getAuthUser();
        return ResponseEntity.ok(user);
    }
}
