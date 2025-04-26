package com.practise.commons;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class ReadmeUpdater {

    private static final Logger logger = Logger.getLogger(ReadmeUpdater.class.getName());
    private static final String SRC_DIR = "src/main/java/com/practise/";
    private static final String README_FILE = "README.md";
    private static final String BRANCH_PATH = "https://github.com/ganeshannt/LetsImplementTheDSA/tree/master/src/main/java/com/practise/";

    private ReadmeUpdater() {
        throw new IllegalStateException("Utility class");
    }

    public static void updateReadMeFile() throws IOException {
        var map = new LinkedHashMap<String, Integer>();
        var srcDir = new File(SRC_DIR);

        var file = new File(README_FILE);

        try (var fos = new FileOutputStream(file)) {
            var sb = new StringBuilder();
            sb.append("# Package Structure\n\n");

            processDirectory(srcDir, sb, map);

            appendSummary(sb, map);

            fos.write(sb.toString().getBytes());
            logger.info("README file updated successfully.");
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error updating README file", e);
            throw e;
        }
    }

    private static void processDirectory(File dir, StringBuilder sb, Map<String, Integer> map) {
        var subDirs = Arrays.stream(Objects.requireNonNull(dir.listFiles(File::isDirectory)))
                .sorted()
                .toList();

        for (var subDir : subDirs) {
            logger.info(subDir.getParentFile().getName());
            if (subDir.getName().equals("problem") || subDir.getParentFile().getName().equals("ps")) {
                var packageName = getFormattedPackageName(subDir);
                sb.append("### ").append(packageName).append("\n");
                sb.append("No").append("|").append("Java File").append("|").append("\n");
                sb.append(":---:|:---:|").append("\n");

                var javaFiles = Arrays.stream(Objects.requireNonNull(subDir.listFiles((d, name) -> name.endsWith(".java"))))
                        .sorted()
                        .toList();

                map.put(packageName, javaFiles.size());

                int count = 1;
                for (var javaFile : javaFiles) {
                    sb.append(count++).append("|[").append(javaFile.getName()).append("](").append(BRANCH_PATH)
                            .append(getRelativePath(javaFile)).append(")|").append("\n");
                }
                sb.append("\n");
            } else {
                processDirectory(subDir, sb, map);
            }
        }
    }

    private static String getFormattedPackageName(File dir) {
        var pathElements = dir.getPath().split(File.separator);
        var problemIndex = Arrays.asList(pathElements).indexOf(dir.getName());
        String[] packageElements = null;
        if (dir.getParentFile().getName().equals("ps")) {
            packageElements = new String[]{dir.getName()};
        } else {
            packageElements = Arrays.copyOfRange(pathElements, problemIndex - 1, problemIndex);
        }

        return String.join(" ", packageElements).replaceAll("[a-z]", " $0").toUpperCase();
    }

    private static String getRelativePath(File file) {
        var srcPath = new File(SRC_DIR).getAbsolutePath();
        return file.getAbsolutePath().substring(srcPath.length() + 1).replace(File.separator, "/");
    }

    private static void appendSummary(StringBuilder sb, Map<String, Integer> map) {
        sb.append("### Summary\n");
        sb.append("Package|File Count|\n");
        sb.append(":---:|:---:|\n");

        int total = 0;
        for (var entry : map.entrySet()) {
            sb.append(entry.getKey()).append("|").append(entry.getValue()).append("|\n");
            total += entry.getValue();
        }
        sb.append("TOTAL|").append(total).append("|\n");
    }
}
