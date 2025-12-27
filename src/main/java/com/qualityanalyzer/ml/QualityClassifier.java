package com.qualityanalyzer.ml;

import com.qualityanalyzer.metrics.CodeMetrics;
import com.qualityanalyzer.model.QualityReport;

import java.util.ArrayList;
import java.util.List;

public class QualityClassifier {

    public QualityReport analyze(CodeMetrics metrics, List<String> syntaxErrors) {

        List<String> issues = new ArrayList<>();

        if (metrics.getCommentRatio() < 20)
            issues.add("Low comment density");

        if (metrics.getCyclomaticComplexity() > 10)
            issues.add("High cyclomatic complexity");

        if (metrics.getNestingDepth() > 4)
            issues.add("Deep nesting detected");

        double score = 10;
        score -= metrics.getCyclomaticComplexity() * 0.3;
        score -= metrics.getNestingDepth() * 0.5;
        if (metrics.getCommentRatio() < 20) score -= 1.5;

        score = Math.max(1, Math.min(10, score));

        String overall =
                score >= 8 ? "GOOD" :
                score >= 5 ? "MODERATE" : "POOR";

        return new QualityReport(
                overall,
                metrics,
                issues,
                syntaxErrors.isEmpty() ? List.of("None") : syntaxErrors,
                score
        );
    }
}
