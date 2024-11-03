package com.example.Wordle.controllers;

import com.example.Wordle.dtos.SignupDTO;
import com.example.Wordle.exceptions.ValidationException;
import com.example.Wordle.models.User;
import com.example.Wordle.repository.UserRepository;
import com.example.Wordle.responses.ApiResponse;
import com.example.Wordle.services.Auth.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/auth/user")
public class UserController {
    @Autowired
    AuthService authService;

    @Autowired
    PasswordEncoder passwordEncoder;
    @RequestMapping(value = "/me", method = RequestMethod.GET)
    public ApiResponse<?> getAuthUser() {
        User user = authService.getAuthUser();
        return new ApiResponse<>(true, HttpStatus.OK, "User found", user);
    }

    @RequestMapping(value = "/edit", method = RequestMethod.PUT)
    public ApiResponse<?> editUser(@RequestBody @Valid SignupDTO user, BindingResult result) {
        User authUser = authService.getAuthUser();
        if (result.hasErrors()) {
            throw new ValidationException(result.getAllErrors()
                    .stream()
                    .map(ObjectError::getDefaultMessage)
                    .collect(Collectors.toList()), "Validation Failed");
        }
        authUser.setEmail(user.getEmail());
        authUser.setUsername(user.getUsername());
        authUser.setPassword(passwordEncoder.encode(user.getPassword()));
        authService.editUser(authUser);
        return new ApiResponse<>(true, HttpStatus.OK, "User updated", null);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public ApiResponse<?> deleteUser() {
        User user = authService.getAuthUser();
        authService.deleteUser(user);
        return new ApiResponse<>(true, HttpStatus.OK, "User deleted", null);
    }
}
