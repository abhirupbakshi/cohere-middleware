package com.example.coheremiddleware.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ClientRequest {

    @JsonProperty(value = "prompt", required = true)
    public String prompt;

    public static ClientRequest fromJson(String json) {
        try {
            return new ObjectMapper().readValue(json, ClientRequest.class);
        }
        catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public String toJson() {
        try {
            return new ObjectMapper().writeValueAsString(this);
        }
        catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
