package com.qualityanalyzer.model;

import com.qualityanalyzer.metrics.CodeMetrics;

public class AiRefactorResponse {

    private String originalCode;
    private String improvedCode;
    private CodeMetrics beforeMetrics;
    private CodeMetrics afterMetrics;

    public AiRefactorResponse(
            String originalCode,
            String improvedCode,
            CodeMetrics beforeMetrics,
            CodeMetrics afterMetrics) {

        this.originalCode = originalCode;
        this.improvedCode = improvedCode;
        this.beforeMetrics = beforeMetrics;
        this.afterMetrics = afterMetrics;
    }

    public String getOriginalCode() { return originalCode; }
    public String getImprovedCode() { return improvedCode; }
    public CodeMetrics getBeforeMetrics() { return beforeMetrics; }
    public CodeMetrics getAfterMetrics() { return afterMetrics; }
}
