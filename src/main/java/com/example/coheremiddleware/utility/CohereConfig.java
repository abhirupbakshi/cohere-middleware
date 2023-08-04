package com.example.coheremiddleware.utility;

import org.springframework.stereotype.Component;

@Component
public class CohereConfig {

    public String token;
    public String generateUrl;
    public int maxTokens;

    {
        token = System.getenv("COHERE_TOKEN");
        generateUrl = System.getenv("COHERE_GENERATE_URL");
        maxTokens = Integer.parseInt(System.getenv("COHERE_MAX_TOKENS"));
    }
}
