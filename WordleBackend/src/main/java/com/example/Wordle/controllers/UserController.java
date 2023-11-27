package com.example.Wordle.controllers;

import com.example.Wordle.dtos.SignupDTO;
import com.example.Wordle.models.User;
import com.example.Wordle.services.Auth.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/auth/user")
public class UserController {
    @Autowired
    AuthService authService;

    @Autowired
    PasswordEncoder passwordEncoder;
    @RequestMapping(value = "/me", method = RequestMethod.GET)
    public ResponseEntity<?> getAuthUser() {
        User user = authService.getAuthUser();
        return ResponseEntity.ok(user);
    }

    //edit user
    @RequestMapping(value = "/edit", method = RequestMethod.PUT)
    public ResponseEntity<?> editUser(@RequestBody SignupDTO user) {
        User authUser = authService.getAuthUser();
        authUser.setEmail(user.getEmail());
        authUser.setUsername(user.getUsername());
        authUser.setPassword(passwordEncoder.encode(user.getPassword()));
        authService.editUser(authUser);
        return ResponseEntity.ok(user);
    }

    //delete account
//    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
//    public ResponseEntity<?> deleteUser() {
//        User authUser = authService.getAuthUser();
//        authService.
//    }
}
