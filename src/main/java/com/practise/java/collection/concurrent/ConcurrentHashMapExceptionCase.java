package com.practise.java.collection.concurrent;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentHashMapExceptionCase {
    public static void main(String[] args) {
        Map<String, Integer> map = new ConcurrentHashMap<>();
        map.put("one", 1);
        map.put("two", 2);
        map.put("three", 3);

        // Attempt to modify the map while iterating
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (entry.getKey().equals("two")) {
                map.put("four", 4); // This will throw ConcurrentModificationException
            }
        }

        System.out.println(map);
    }
}
