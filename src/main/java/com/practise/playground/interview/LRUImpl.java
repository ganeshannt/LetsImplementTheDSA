package com.practise.playground.interview;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class LRUImpl {

    private Map<String, String> cacheMap;
    private int size;
    private int capacity;
    private LinkedList<String> frequenceyList;


    public LRUImpl(int capacity) {
        this.cacheMap = new HashMap<>();
        this.size = 0;
        this.capacity = capacity;
        this.frequenceyList = new LinkedList<>();
    }


    void put(String key, String value) {
        if (size == capacity) {
            String removedKey = frequenceyList.removeLast();
            cacheMap.remove(removedKey);
            cacheMap.put(key, value);
        } else {
            cacheMap.put(key, value);
            updateFrequency(key);
            size++;
        }
    }


    String get(String key) {
        String value = cacheMap.get(key);
        if (value != null) {
            updateFrequency(key);
        }
        return value;
    }

    private void updateFrequency(String key) {
        frequenceyList.remove(key);
        frequenceyList.addFirst(key);
    }


    private void printCache() {
        cacheMap.forEach((k, v) -> {
            System.out.printf("key : {%s} , value: {%s}\n", k, v);
        });
    }


    public static void main(String[] args) {
        LRUImpl lru = new LRUImpl(3);

        lru.put("key1", "value1");
        lru.put("key2", "value2");
        lru.put("key3", "value3");
        lru.put("key4", "value4");

        lru.get("key2");

        lru.put("key5", "value5");

        lru.printCache();


    }


}
