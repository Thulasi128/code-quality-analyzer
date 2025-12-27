package com.qualityanalyzer.analyzer;

import com.qualityanalyzer.metrics.CodeMetrics;

public class MetricsExtractor {

    public static CodeMetrics extract(String code) {
        return CodeMetrics.fromCode(code);
    }
}
