package com.example.Wordle.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ValidationException extends RuntimeException {
    private ErrorResponse errorResponse;

    public ValidationException(List<String> errors) {
        this.errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST, "Validation failed", errors);
    }

    public ValidationException(List<String> errors, String message) {
        this.errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST, message, errors);
    }

    public ValidationException(List<String> errors, String message, int code) {
        this.errorResponse = new ErrorResponse(HttpStatus.valueOf(code), message, errors);
    }
}
