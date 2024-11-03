package com.example.Wordle.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class DataNotFoundException extends RuntimeException {
    private ErrorResponse errorResponse;

    public DataNotFoundException(String message) {
        this.errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND, message);
    }
}
