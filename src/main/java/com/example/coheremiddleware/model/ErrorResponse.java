package com.example.coheremiddleware.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDateTime;

public class ErrorResponse {

    @JsonProperty(value = "message", required = true)
    public String message;

    @JsonProperty(value = "timestamp", required = true)
    public final LocalDateTime timestamp = LocalDateTime.now();

    public ErrorResponse(String message) {
        this.message = message;
    }
}
