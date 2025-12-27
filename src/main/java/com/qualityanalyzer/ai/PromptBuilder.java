package com.qualityanalyzer.ai;

import com.qualityanalyzer.metrics.CodeMetrics;

public class PromptBuilder {

    public static String buildRefactorPrompt(String code, CodeMetrics metrics) {

        return """
You are a senior Java engineer.

Refactor the following Java code to:
- Reduce cyclomatic complexity
- Extract methods where needed
- Avoid nested conditionals
- Preserve functionality
- Return ONLY valid Java code

Current Metrics:
- Cyclomatic Complexity: %d
- Methods: %d
- Nesting Depth: %d

Java Code:
----------------
%s
----------------
""".formatted(
            metrics.getCyclomaticComplexity(),
            metrics.getMethods(),
            metrics.getNestingDepth(),
            code
        );
    }
}
