package com.qualityanalyzer.analyzer;

import com.github.javaparser.*;
import com.github.javaparser.ast.CompilationUnit;

import java.util.List;
import java.util.stream.Collectors;

public class SyntaxAnalyzer {

    public static List<String> checkSyntax(String code) {
        JavaParser parser = new JavaParser();
        ParseResult<CompilationUnit> result = parser.parse(code);

        if (result.isSuccessful()) {
            return List.of();
        }

        return result.getProblems()
                .stream()
                .map(problem -> {
                    if (problem.getLocation().isPresent()) {
                        var location = problem.getLocation().get();
                        return "At " + location + " : " + problem.getMessage();
                    }
                    return problem.getMessage();
                })
                .collect(Collectors.toList());
    }
}
