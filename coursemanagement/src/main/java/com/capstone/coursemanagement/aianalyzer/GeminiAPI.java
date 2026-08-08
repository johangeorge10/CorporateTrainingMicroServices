package com.capstone.coursemanagement.aianalyzer;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClient;
import com.capstone.coursemanagement.aianalyzer.Instrctions;
import java.util.List;

@Service
public class GeminiAPI {
    private final RestClient restClient;
    private final String apiKey;

    public GeminiAPI(@Value("${gemini.api.url}") String baseUrl, @Value("${gemini.api.key}") String apiKey) {
        this.apiKey = apiKey;
        this.restClient = RestClient.create(baseUrl);
    }
    
    public String askAi(String input) {
        var request = new GeminiRequest(
            "gemini-3.6-flash", 
            Instrctions.courseInstruction + ": " + input
        );

        GeminiResponse response = restClient.post()
            .uri("/v1beta/interactions")
            .header("x-goog-api-key", apiKey)
            .contentType(MediaType.APPLICATION_JSON)
            .body(request) 
            .retrieve()
            .body(GeminiResponse.class);

        // Extracts text from the new Interactions API response structure
        return response.steps().stream()
            .filter(step -> "model_output".equals(step.type()))
            .flatMap(step -> step.content().stream())
            .filter(c -> "text".equals(c.type()))
            .findFirst()
            .map(GeminiResponse.ContentPart::text)
            .orElse("No response generated");
    }

    // --- Updated DTO Records for Interactions API ---
    public record GeminiRequest(String model, String input) {}

    public record GeminiResponse(List<Step> steps) {
        public record Step(String type, List<ContentPart> content) {}
        public record ContentPart(String type, String text) {}
    }
}