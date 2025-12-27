package com.qualityanalyzer.metrics;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.stmt.IfStmt;
import com.github.javaparser.ast.stmt.ForStmt;
import com.github.javaparser.ast.stmt.WhileStmt;

public class NestingDepthCalculator {

    public static int calculate(CompilationUnit cu) {
        int depth = 0;

        depth = Math.max(depth, cu.findAll(IfStmt.class).size());
        depth = Math.max(depth, cu.findAll(ForStmt.class).size());
        depth = Math.max(depth, cu.findAll(WhileStmt.class).size());

        return depth;
    }
}
