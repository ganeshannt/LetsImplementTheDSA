package com.practise.ds.tuf.advance;

import java.util.HashMap;
import java.util.LinkedHashSet;

public class LFUCache {
    private final int capacity;
    private final HashMap<Integer, Pair<Integer, Integer>> cache;
    private final HashMap<Integer, LinkedHashSet<Integer>> frequencyMap;
    private int minFrequency;

    public LFUCache(int capacity) {
        this.capacity = capacity;
        this.minFrequency = 0;
        this.cache = new HashMap<>();
        this.frequencyMap = new HashMap<>();
    }

    /**
     * Time Complexity: O(1)
     * Space Complexity: O(capacity)
     */
    public int get(int key) {
        if (!cache.containsKey(key)) {
            return -1;
        }
        Pair<Integer, Integer> pair = cache.get(key);
        updateFrequency(key, pair);
        return pair.getValue();
    }

    /**
     * Time Complexity: O(1)
     * Space Complexity: O(capacity)
     */
    public void put(int key, int value) {
        if (capacity == 0) {
            return;
        }
        if (cache.containsKey(key)) {
            Pair<Integer, Integer> pair = cache.get(key);
            pair.setValue(value);
            updateFrequency(key, pair);
        } else {
            if (cache.size() == capacity) {
                removeLeastFrequent();
            }
            Pair<Integer, Integer> newPair = new Pair<>(1, value);
            cache.put(key, newPair);
            frequencyMap.putIfAbsent(1, new LinkedHashSet<>());
            frequencyMap.get(1).add(key);
            minFrequency = 1;
        }
    }

    private void updateFrequency(int key, Pair<Integer, Integer> pair) {
        int freq = pair.getKey();
        frequencyMap.get(freq).remove(key);
        if (frequencyMap.get(freq).isEmpty() && freq == minFrequency) {
            minFrequency++;
        }
        pair.setKey(freq + 1);
        frequencyMap.putIfAbsent(freq + 1, new LinkedHashSet<>());
        frequencyMap.get(freq + 1).add(key);
    }

    private void removeLeastFrequent() {
        LinkedHashSet<Integer> minFreqSet = frequencyMap.get(minFrequency);
        int keyToRemove = minFreqSet.iterator().next();
        minFreqSet.remove(keyToRemove);
        if (minFreqSet.isEmpty()) {
            frequencyMap.remove(minFrequency);
        }
        cache.remove(keyToRemove);
    }

    public static class Pair<K, V> {
        private K key;
        private V value;

        public Pair(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public void setKey(K key) {
            this.key = key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }
    }
}