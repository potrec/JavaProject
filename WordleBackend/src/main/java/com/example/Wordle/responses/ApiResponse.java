package com.example.Wordle.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
public class ApiResponse<T> {
    @JsonProperty("success")
    private boolean success;
    @JsonProperty("responseCode")
    private int responseCode;
    @JsonProperty("responseMessage")
    private String responseMessage;
    @JsonProperty("data")
    private T data;

    public ApiResponse(boolean success, HttpStatus responseCode, String responseMessage, T data) {
        this.success = success;
        this.responseCode = responseCode.value();
        this.responseMessage = responseMessage;
        this.data = data;
    }
}

