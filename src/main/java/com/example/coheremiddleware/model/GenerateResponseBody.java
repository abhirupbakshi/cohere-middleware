package com.example.coheremiddleware.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;

public class GenerateResponseBody {

    public static class Generation {

        public static class TokenLikelihood {

            @JsonProperty(value = "token", required = true)
            public String token;

            @JsonProperty(value = "likelihood", required = true)
            public Number likelihood;

            @Override
            public String toString() {
                return "TokenLikelihood{" +
                        "token='" + token + '\'' +
                        ", likelihood=" + likelihood +
                        '}';
            }
        }

        @JsonProperty(value = "id", required = true)
        public String id;

        @JsonProperty(value = "text", required = true)
        public String text;

        @JsonProperty(value = "index")
        public Integer index;

        @JsonProperty(value = "likelihood")
        public Number likelihood;

        @JsonProperty(value = "token_likelihoods")
        public List<TokenLikelihood> tokenLikelihoods;

        @Override
        public String toString() {
            return "Generation{" +
                    "id='" + id + '\'' +
                    ", text='" + text + '\'' +
                    ", index=" + index +
                    ", likelihood=" + likelihood +
                    ", token_likelihoods=" + tokenLikelihoods +
                    '}';
        }
    }

    public static class Meta {

        public static class ApiVersion {

            @JsonProperty(value = "version", required = true)
            public String version;

            @JsonProperty(value = "is_deprecated")
            public Boolean isDeprecated;

            @JsonProperty(value = "is_experimental")
            public Boolean isExperimental;

            @Override
            public String toString() {
                return "ApiVersion{" +
                        "version='" + version + '\'' +
                        ", is_deprecated=" + isDeprecated +
                        ", is_experimental=" + isExperimental +
                        '}';
            }
        }

        // In the documentation, this is a list of ApiVersion (https://docs.cohere.com/reference/generate)
        @JsonProperty(value = "api_version")
        public ApiVersion apiVersion;

        @JsonProperty(value = "warnings")
        public List<String> warnings;

        @Override
        public String toString() {
            return "Meta{" +
                    "api_version=" + apiVersion +
                    ", warnings=" + warnings +
                    '}';
        }
    }

    @JsonProperty(value = "id", required = true)
    public String id;

    @JsonProperty(value = "prompt")
    public String prompt;

    @JsonProperty(value = "generations", required = true)
    public List<Generation> generations;

    // In the documentation, this is a list of Meta (https://docs.cohere.com/reference/generate)
    @JsonProperty(value = "meta")
    public Meta meta;

    @Override
    public String toString() {
        return "Generate{" +
                "id='" + id + '\'' +
                ", prompt='" + prompt + '\'' +
                ", generations=" + generations +
                ", meta=" + meta +
                '}';
    }

    public static GenerateResponseBody fromJson(String json) {
        try {
            return new ObjectMapper().readValue(json, GenerateResponseBody.class);
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
