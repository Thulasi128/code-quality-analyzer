package com.qualityanalyzer.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {

    public static List<File> listJavaFiles(File root) {
        List<File> files = new ArrayList<>();
        collect(root, files);
        return files;
    }

    private static void collect(File file, List<File> files) {
        if (file.isDirectory()) {
            for (File f : file.listFiles()) {
                collect(f, files);
            }
        } else if (file.getName().endsWith(".java")) {
            files.add(file);
        }
    }
}
