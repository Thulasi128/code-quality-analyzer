package com.qualityanalyzer.controller;

import com.qualityanalyzer.service.FileAnalyzerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class AnalysisController {

    @Autowired
    private FileAnalyzerService analyzer;

    /** Code Metrics + Syntax + Maintainability JSON */
    @PostMapping("/analyze")
    public ResponseEntity<Object> analyze(@RequestParam("file") MultipartFile file) {
        return ResponseEntity.ok(analyzer.analyzeFile(file));
    }

    /** AI Refactor */
    @PostMapping("/refactor")
    public ResponseEntity<Object> refactor(@RequestParam("file") MultipartFile file) {
        return ResponseEntity.ok(analyzer.refactorCode(file));
    }
}
