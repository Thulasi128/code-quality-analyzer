package com.qualityanalyzer.analyzer;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class JavaFileScanner {

    public static List<File> scan(String rootPath) {
        List<File> javaFiles = new ArrayList<>();
        scanRecursive(new File(rootPath), javaFiles);
        return javaFiles;
    }

    private static void scanRecursive(File file, List<File> files) {
        if (file.isDirectory()) {
            for (File f : file.listFiles()) {
                scanRecursive(f, files);
            }
        } else if (file.getName().endsWith(".java")) {
            files.add(file);
        }
    }
}
