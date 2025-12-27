package com.qualityanalyzer.metrics;

import com.github.javaparser.ast.CompilationUnit;


public class ComplexityCalculator {
    public static int calculate(CompilationUnit cu) {
        return cu.findAll(com.github.javaparser.ast.stmt.IfStmt.class).size()
             + cu.findAll(com.github.javaparser.ast.stmt.ForStmt.class).size()
             + cu.findAll(com.github.javaparser.ast.stmt.WhileStmt.class).size()
             + 1;
    }
}
