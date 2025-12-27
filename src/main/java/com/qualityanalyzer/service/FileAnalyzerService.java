package com.qualityanalyzer.service;

import com.qualityanalyzer.analyzer.MetricsExtractor;
import com.qualityanalyzer.analyzer.SyntaxAnalyzer;
import com.qualityanalyzer.ml.QualityClassifier;
import com.qualityanalyzer.model.QualityReport;
import com.qualityanalyzer.ai.GeminiClient;
import com.qualityanalyzer.ai.FallbackRefactorService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Service
public class FileAnalyzerService {

    private final QualityClassifier classifier = new QualityClassifier();
    private final GeminiClient geminiClient;
    private final FallbackRefactorService fallbackRefactorService;

    public FileAnalyzerService(GeminiClient geminiClient, FallbackRefactorService fallbackRefactorService) {
        this.geminiClient = geminiClient;
        this.fallbackRefactorService = fallbackRefactorService;
    }

    public Map<String, Object> analyzeFile(MultipartFile file) {
        Map<String, Object> response = new HashMap<>();
        try {
            String code = new String(file.getBytes(), StandardCharsets.UTF_8);

            var metrics = MetricsExtractor.extract(code);
            var syntaxErrors = SyntaxAnalyzer.checkSyntax(code);
            QualityReport report = classifier.analyze(metrics, syntaxErrors);

            response.put("metrics", metrics);
            response.put("syntaxErrors", syntaxErrors);
            response.put("qualityReport", report);

        } catch (Exception e) {
            response.put("error", "Failed to analyze file: " + e.getMessage());
        }
        return response;
    }

    // ------------------ AI REFACTOR ------------------ //
    public Map<String, Object> refactorCode(MultipartFile file) {
        Map<String, Object> response = new HashMap<>();
        try {
            String original = new String(file.getBytes(), StandardCharsets.UTF_8);

            String prompt = "Refactor this Java code and explain improvements:\n\n" + original;

            try {
                Map<String, Object> aiResponse = geminiClient.generateText(prompt);
                response.put("improvedCode", aiResponse.get("text"));
                response.put("remaining", aiResponse.get("remaining"));
                response.put("limit", aiResponse.get("limit"));
                response.put("used", aiResponse.get("used"));
            } catch (Exception aiError) {
                response.put("improvedCode", fallbackRefactorService.basicRefactor(original));
                response.put("note", "⚠ Gemini quota exceeded — used fallback method.");
            }

            response.put("originalCode", original);

        } catch (Exception e) {
            response.put("error", "AI refactor failed: " + e.getMessage());
        }
        return response;
    }
}
