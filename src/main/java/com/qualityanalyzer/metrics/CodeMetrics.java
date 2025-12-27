package com.qualityanalyzer.metrics;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CodeMetrics {

    private int linesOfCode;
    private int methods;
    private int cyclomaticComplexity;
    private int nestingDepth;
    private double commentRatio;

    // -------- FACTORY METHOD --------
    public static CodeMetrics fromCode(String code) {
        CodeMetrics m = new CodeMetrics();

        if (code == null || code.isBlank()) {
            return m;
        }

        // 1. PHYSICAL lines of code (includes braces, blanks)
        m.linesOfCode = code.split("\\R").length;

        // 2. Method count
        m.methods = count(code,
                "\\b(public|protected|private|static|\\s)+\\s+[\\w<>\\[\\]]+\\s+\\w+\\s*\\(");

        // 3. Cyclomatic complexity
        m.cyclomaticComplexity = 1 + count(code,
                "\\b(if|for|while|case|catch|&&|\\|\\|)\\b");

        // 4. Nesting depth
        m.nestingDepth = calculateDepth(code);

        // 5. Comment ratio (safe)
        m.commentRatio = calculateCommentRatio(code);

        return m;
    }

    // -------- HELPERS --------

    private static int count(String code, String regex) {
        Matcher m = Pattern.compile(regex).matcher(code);
        int c = 0;
        while (m.find()) c++;
        return c;
    }

    private static int calculateDepth(String code) {
        int current = 0, max = 0;
        for (char ch : code.toCharArray()) {
            if (ch == '{') {
                current++;
                max = Math.max(max, current);
            } else if (ch == '}') {
                current--;
            }
        }
        return max;
    }

    private static double calculateCommentRatio(String code) {
        String[] lines = code.split("\\R");
        if (lines.length == 0) return 0;

        int commentLines = 0;
        for (String l : lines) {
            String t = l.trim();
            if (t.startsWith("//") || t.startsWith("/*") || t.startsWith("*")) {
                commentLines++;
            }
        }
        return (commentLines * 100.0) / lines.length;
    }

    // -------- GETTERS --------

    public int getLinesOfCode() {
        return linesOfCode;
    }

    public int getMethods() {
        return methods;
    }

    public int getCyclomaticComplexity() {
        return cyclomaticComplexity;
    }

    public int getNestingDepth() {
        return nestingDepth;
    }

    public double getCommentRatio() {
        return commentRatio;
    }
}
