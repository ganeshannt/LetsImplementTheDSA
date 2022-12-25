package com.practise;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Ganeshan Nagarajan
 * @since 20-03-2022
 */

public class Launch {

    private static final String SRC_DIR =
            "/Users/ganeshannt/IdeaProjects/personal/LetsImplementTheDSA/src/main/java/com/practise/problemsolving/";
    private static final String README_FILE = "/Users/ganeshannt/IdeaProjects/personal/LetsImplementTheDSA/README.md";
    private static final String BRANCH_PATH =
            "https://github.com/ganeshannt/LetsImplementTheDSA/tree/master/src/main/java/com/practise/problemsolving/";

    public static void main(String[] args) throws IOException {
        UpdateReadMeFile();
    }

    private static void UpdateReadMeFile() throws IOException {

        Map<String, Integer> map = new LinkedHashMap<String, Integer>();
        File srcDir = new File(SRC_DIR);

        // Coding problem type will be list on high to low
        List<File> dirList = List.of(srcDir.listFiles());

        File file = new File(README_FILE);
        FileOutputStream fos = new FileOutputStream(file);

        StringBuilder sb = new StringBuilder();
        sb.append("# Problem Solving").append("\n");

        Map<String, String> nameMapping = new HashMap<>();
        nameMapping.put("SEARCHINGANDSORTING", "SEARCHING AND SORTING");

        for (File dir : dirList) {
            if (!dir.isFile()) {
                String directoryName = nameMapping.getOrDefault(dir.getName().toUpperCase(), dir.getName().toUpperCase());
                sb.append("### ").append(directoryName).append("\n");
                sb.append("No").append("|").append("Problems").append("|").append("\n");
                sb.append(":---: | :---:|").append("\n");
                map.put(dir.getName().toUpperCase(), dir.list().length);
                int count = 1;
                for (String fileName : dir.list()) {
                    if (fileName.contains(".java")) {
                        sb.append(count++).append("|[").append(fileName).append("](").append(BRANCH_PATH)
                                .append(dir.getName()).append("/").append(fileName).append(")|").append("\n");
                    }
                }
                sb.append("\n");
            }
        }
        sb.append("Topics").append("|").append("Problems").append("|").append("\n");
        sb.append(":---: | :---:|").append("\n");
        int total = 0;
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            sb.append(entry.getKey()).append("|").append(entry.getValue()).append("|").append("\n");
            total += entry.getValue();
        }
        sb.append("TOTAL").append("|").append(total).append("|").append("\n");
        fos.write(sb.toString().getBytes());
        System.out.println("Readme File updated successfully...!");
        fos.flush();
        fos.close();
    }
}
