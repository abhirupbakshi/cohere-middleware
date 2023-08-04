package com.example.coheremiddleware.controller;

import com.example.coheremiddleware.model.ClientRequest;
import com.example.coheremiddleware.model.ClientResponse;
import com.example.coheremiddleware.model.GenerateRequestBody;
import com.example.coheremiddleware.model.GenerateResponseBody;
import com.example.coheremiddleware.utility.CohereConfig;
import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.DefaultAsyncHttpClient;
import org.asynchttpclient.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;

@RestController
public class ConversationController {

    @Autowired
    CohereConfig cohereConfig;

    @PostMapping("/generate")
    public ResponseEntity<ClientResponse> generate(@RequestBody ClientRequest request) {

        AsyncHttpClient client = new DefaultAsyncHttpClient();
        GenerateRequestBody body = new GenerateRequestBody();
        AtomicReference<Response> response = new AtomicReference<>();
        HttpHeaders header = new HttpHeaders();
        ClientResponse clientResponse = new ClientResponse();

        body.maxTokens = cohereConfig.maxTokens;
        body.returnLikelihoods = GenerateRequestBody.ReturnLikelihood.NONE;
        body.truncate = GenerateRequestBody.Truncate.END;
        body.prompt = request.prompt;

        try {
            client.prepare("POST", cohereConfig.generateUrl)
                    .setHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON)
                    .setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                    .setHeader(HttpHeaders.AUTHORIZATION, "Bearer " + cohereConfig.token)
                    .setBody(body.toJson())
                    .execute()
                    .toCompletableFuture()
                    .thenAccept(response::set)
                    .join();

            client.close();
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }

        header.set(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "*");
        header.set(HttpHeaders.ACCESS_CONTROL_ALLOW_METHODS, "POST, PUT, DELETE, GET, OPTIONS");
        header.set(HttpHeaders.ACCESS_CONTROL_REQUEST_METHOD, "*");
        header.set(HttpHeaders.ACCESS_CONTROL_ALLOW_HEADERS, "Origin, X-Requested-With, Content-Type, Accept, Authorization");
        header.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        clientResponse.generatedResponse = GenerateResponseBody
                .fromJson(response.get().getResponseBody())
                .generations.get(0).text;

        return new ResponseEntity<>(clientResponse, header, HttpStatus.OK);
    }
}
