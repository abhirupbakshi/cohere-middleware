package com.example.coheremiddleware.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;

public class GenerateRequestBody {

    public enum ReturnLikelihood {
        GENERATION,
        ALL,
        NONE
    }

    public enum Truncate {

        NONE,
        START,
        END
    }

    public static class LogitBias {

        @JsonProperty(value = "token_id")
        public String tokenId;

        @JsonProperty(value = "bias")
        public Double bias;

        @Override
        public String toString() {
            return "LogitBias{" +
                    "tokenId='" + tokenId + '\'' +
                    ", bias=" + bias +
                    '}';
        }
    }

    @JsonProperty(value = "prompt", required = true)
    public String prompt;

    @JsonProperty(value = "model")
    public String model;

    @JsonProperty(value = "num_generations")
    public Integer numGenerations;

    @JsonProperty(value = "max_tokens")
    public Integer maxTokens;

    @JsonProperty(value = "preset")
    public String preset;

    @JsonProperty(value = "temperature")
    public Number temperature;

    @JsonProperty(value = "k")
    public Integer k;

    @JsonProperty(value = "p")
    public Number p;

    @JsonProperty(value = "frequency_penalty")
    public Number frequencyPenalty;

    @JsonProperty(value = "presence_penalty")
    public Number presencePenalty;

    @JsonProperty(value = "end_sequences")
    public List<String> endSequences;

    @JsonProperty(value = "stop_sequences")
    public List<String> stopSequences;

    @JsonProperty(value = "return_likelihoods")
    public ReturnLikelihood returnLikelihoods;

    @JsonProperty(value = "logit_bias")
    public LogitBias logitBias;

    @JsonProperty(value = "truncate")
    public Truncate truncate;

    @JsonProperty(value = "stream")
    public Boolean stream;

    @Override
    public String toString() {
        return "GenerateRequest{" +
                "prompt='" + prompt + '\'' +
                ", model='" + model + '\'' +
                ", numGenerations=" + numGenerations +
                ", maxTokens=" + maxTokens +
                ", preset='" + preset + '\'' +
                ", temperature=" + temperature +
                ", k=" + k +
                ", p=" + p +
                ", frequencyPenalty=" + frequencyPenalty +
                ", presencePenalty=" + presencePenalty +
                ", endSequences=" + endSequences +
                ", stopSequences=" + stopSequences +
                ", returnLikelihoods=" + returnLikelihoods +
                ", logitBias=" + logitBias +
                ", truncate=" + truncate +
                ", stream=" + stream +
                '}';
    }

    public static GenerateRequestBody fromJson(String json) {
        try {
            return new ObjectMapper().readValue(json, GenerateRequestBody.class);
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
