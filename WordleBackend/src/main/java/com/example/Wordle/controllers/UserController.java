package com.example.Wordle.controllers;

import com.example.Wordle.dtos.UserEditDto;
import com.example.Wordle.exceptions.DataNotFoundException;
import com.example.Wordle.exceptions.ValidationException;
import com.example.Wordle.models.User;
import com.example.Wordle.responses.ApiResponse;
import com.example.Wordle.services.Auth.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
    public ApiResponse<?> editUser(@Valid @RequestBody UserEditDto userEditDto, BindingResult result) {
        if (result.hasErrors()) {
            List<String> errors = result.getAllErrors()
                    .stream()
                    .map(ObjectError::getDefaultMessage)
                    .collect(Collectors.toList());
            throw new ValidationException(errors, "Validation Failed");
        }

        authService.editUser(userEditDto);
        return new ApiResponse<>(true, HttpStatus.OK, "User updated", null);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public ApiResponse<?> deleteUser() {
        User user = authService.getAuthUser();
        authService.deleteUser(user);
        return new ApiResponse<>(true, HttpStatus.OK, "User deleted", null);
    }

    @ExceptionHandler({ValidationException.class, DataNotFoundException.class})
    public ResponseEntity<?> handleValidationException(ValidationException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getErrorResponse());
    }
}
