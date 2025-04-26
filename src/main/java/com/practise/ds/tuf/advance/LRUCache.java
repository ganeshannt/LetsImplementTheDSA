package com.practise.ds.tuf.advance;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class LRUCache {

    private final Map<Integer, Integer> cacheMap;
    private final LinkedList<Integer> cacheOrderList;
    private final int capacity;

    public LRUCache(int capacity) {
        this.cacheMap = new HashMap<>(capacity);
        this.cacheOrderList = new LinkedList<>();
        this.capacity = capacity;
    }

    public static void main(String[] args) {
        LRUCache cache = new LRUCache(10);
        cache.put(1, 1);
    }

    public int get(int key) {
        if (cacheMap.containsKey(key)) {
            cacheOrderList.remove((Object) key);
            cacheOrderList.addFirst(key);
            return cacheMap.get(key);
        }
        return -1;
    }

    public void put(int key, int value) {
        if (cacheMap.containsKey(key)) {
            cacheOrderList.remove((Object) key);
        } else {
            if (cacheMap.size() == capacity) {
                int lastKey = cacheOrderList.removeLast();
                cacheMap.remove(lastKey);
            }
        }
        cacheOrderList.addFirst(key);
        cacheMap.put(key, value);
    }
}
