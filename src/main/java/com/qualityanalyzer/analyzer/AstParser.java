package com.qualityanalyzer.analyzer;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ParseResult;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.stmt.*;

import java.io.File;
import java.nio.file.Files;

public class AstParser {

    private final JavaParser parser = new JavaParser();

    public CompilationUnit parseFile(File file) {
        try {
            String code = Files.readString(file.toPath());

            ParseResult<CompilationUnit> result = parser.parse(code);

            return result.getResult()
                    .orElseThrow(() -> new RuntimeException("Unable to parse file: " + file.getName()));

        } catch (Exception e) {
            throw new RuntimeException("AST parsing failed", e);
        }
    }

    public int countMethods(CompilationUnit cu) {
        return cu.findAll(MethodDeclaration.class).size();
    }

    public int calculateCyclomaticComplexity(CompilationUnit cu) {
        int complexity = 1;

        complexity += cu.findAll(IfStmt.class).size();
        complexity += cu.findAll(ForStmt.class).size();
        complexity += cu.findAll(WhileStmt.class).size();
        complexity += cu.findAll(DoStmt.class).size();
        complexity += cu.findAll(SwitchEntry.class).size();
        complexity += cu.findAll(CatchClause.class).size();

        return complexity;
    }
}
