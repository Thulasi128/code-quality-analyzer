package com.qualityanalyzer.model;

public class FileQualityResult {

    private String fileName;
    private String riskLevel;
    private double score;

    public FileQualityResult(String fileName, String riskLevel, double score) {
        this.fileName = fileName;
        this.riskLevel = riskLevel;
        this.score = score;
    }

    public String getFileName() { return fileName; }
    public String getRiskLevel() { return riskLevel; }
    public double getScore() { return score; }
}
