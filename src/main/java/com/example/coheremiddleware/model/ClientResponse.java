package com.example.coheremiddleware.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ClientResponse {

    @JsonProperty(value = "generated_response", required = true)
    public String generatedResponse;

    public static ClientResponse fromJson(String json) {
        try {
            return new ObjectMapper().readValue(json, ClientResponse.class);
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
