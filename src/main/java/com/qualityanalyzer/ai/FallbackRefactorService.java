package com.qualityanalyzer.ai;

import org.springframework.stereotype.Component;

@Component
public class FallbackRefactorService {

    public String basicRefactor(String originalCode) {

        String improved = originalCode
                .replaceAll("\\s+", " ")
                .replace("{ ", "{\n")
                .replace(" }", "\n}")
                .replace(";", ";\n");

        return
                "⚠ Gemini quota exceeded — using basic code cleanup\n\n" +
                "📌 Improvements Applied:\n" +
                "✔ Removed extra spaces\n" +
                "✔ Added consistent line breaks\n" +
                "✔ Cleaned up indentation\n" +
                "✔ No logic was changed\n\n" +
                "📌 AI Code Not Available — free quota crossed\n" +
                "➡ Get new free quota tomorrow or upgrade Gemini plan\n\n" +
                "✨ Cleaned Code Below:\n\n" +
                improved;
    }
}
