package com.alurachallenge.literalura.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ApiClient {
    private ObjectMapper objectMapper;

    public ApiClient() {
        this.objectMapper = new ObjectMapper();
    }

    public String obtenerDatos(String url){
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();
        HttpResponse<String> response;
        try {
            response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        try {
            JsonNode rootNode = objectMapper.readTree(response.body());
            JsonNode resultsNode = rootNode.get("results");
            JsonNode book = resultsNode.get(0);
            JsonNode bookl = book.get("languages");
            if (bookl.size() > 0) {
                String firstLanguage = bookl.get(0).asText();
                ((ObjectNode) book).put("languages", firstLanguage);
            }
            if (resultsNode == null || !resultsNode.isArray() || resultsNode.size() == 0) {
                throw new RuntimeException("Libro no encontrado");
            }
            return book.toString();
        } catch (IOException | NullPointerException e) {
            throw new RuntimeException(e);
        }
    }
}
