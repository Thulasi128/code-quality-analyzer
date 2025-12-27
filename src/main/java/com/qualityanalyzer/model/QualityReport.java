package com.qualityanalyzer.model;

import com.qualityanalyzer.metrics.CodeMetrics;

import java.util.List;

public class QualityReport {

    private String overallQuality;
    private CodeMetrics metrics;
    private List<String> issues;
    private List<String> syntaxErrors;
    private double maintainabilityScore;

    // ✅ REQUIRED CONSTRUCTOR (THIS FIXES YOUR ERROR)
    public QualityReport(
            String overallQuality,
            CodeMetrics metrics,
            List<String> issues,
            List<String> syntaxErrors,
            double maintainabilityScore
    ) {
        this.overallQuality = overallQuality;
        this.metrics = metrics;
        this.issues = issues;
        this.syntaxErrors = syntaxErrors;
        this.maintainabilityScore = maintainabilityScore;
    }

    // ---------- GETTERS ----------
    public String getOverallQuality() {
        return overallQuality;
    }

    public CodeMetrics getMetrics() {
        return metrics;
    }

    public List<String> getIssues() {
        return issues;
    }

    public List<String> getSyntaxErrors() {
        return syntaxErrors;
    }

    public double getMaintainabilityScore() {
        return maintainabilityScore;
    }
}
