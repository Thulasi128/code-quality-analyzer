package com.qualityanalyzer.ai;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.Map;

@Component
public class GeminiClient {

    @Value("${gemini.key}")
    private String apiKey;

    private final RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper mapper = new ObjectMapper();

    public Map<String, Object> generateText(String prompt) throws Exception {

        String apiUrl =
                "https://generativelanguage.googleapis.com/v1beta/models/gemini-2.0-flash-lite:generateContent?key="
                        + apiKey;

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        String jsonRequest = """
            {
                "contents": [{
                    "parts": [{
                        "text": "%s"
                    }]
                }]
            }
        """.formatted(prompt.replace("\"", "\\\""));

        HttpEntity<String> request = new HttpEntity<>(jsonRequest, headers);
        String jsonResponse = restTemplate.postForObject(apiUrl, request, String.class);

        JsonNode root = mapper.readTree(jsonResponse);

        String responseText = root.path("candidates")
                .path(0)
                .path("content")
                .path("parts")
                .path(0)
                .path("text")
                .asText("⚠ AI did not return any content");

        // Quota usage tracking
        int used = 1;  // AI request = 1 usage
        int limit = 20; // Free quota
        int remaining = Math.max(0, limit - used);

        Map<String, Object> response = new HashMap<>();
        response.put("text", responseText);
        response.put("used", used);
        response.put("limit", limit);
        response.put("remaining", remaining);

        return response;
    }
}
