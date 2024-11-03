package com.example.Wordle.exceptions;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class ErrorResponse {
    private boolean success = false;
    private int responseCode;
    private String status;
    private String responseMessage;
    private String stackTrace;
    private List<String> errors;

    public ErrorResponse() {}

    public ErrorResponse(
            HttpStatus httpStatus,
            String message
    ) {
        this();

        this.responseCode = httpStatus.value();
        this.status = httpStatus.name();
        this.responseMessage = message;
    }

    public ErrorResponse(
            HttpStatus httpStatus,
            String message,
            String stackTrace
    ) {
        this(
                httpStatus,
                message
        );

        this.stackTrace = stackTrace;
    }

    public ErrorResponse(
            HttpStatus httpStatus,
            String message,
            List<String> errors
    ) {
        this(
                httpStatus,
                message
        );
        this.errors = errors;
    }
}
