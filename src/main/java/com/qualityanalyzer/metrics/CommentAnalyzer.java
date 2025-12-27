package com.qualityanalyzer.metrics;



public class CommentAnalyzer {

    public static double calculate(String code) {
        int totalLines = code.split("\n").length;
        long commentLines = code.lines()
                .filter(l -> l.trim().startsWith("//") || l.trim().startsWith("/*") || l.trim().startsWith("*"))
                .count();

        return totalLines == 0 ? 0 : (commentLines * 100.0) / totalLines;
    }
}
