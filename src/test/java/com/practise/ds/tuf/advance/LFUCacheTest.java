package com.practise.ds.tuf.advance;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LFUCacheTest {

    private LFUCache cache;

    @BeforeEach
    void setUp() {
        cache = new LFUCache(2);  // Set up a cache with capacity 2
    }

    @Test
    void testBasicOperations() {
        cache.put(1, 1);
        assertEquals(1, cache.get(1)); // Check put and get
        cache.put(1, 10);
        assertEquals(10, cache.get(1)); // Update value and get
    }

    @Test
    void testCapacityLimitEviction() {
        cache.put(1, 1);
        cache.put(2, 2);
        cache.put(3, 3);  // Should evict key 1 as it and 2 both have freq 1, but 1 is LRU
        assertEquals(-1, cache.get(1));
        assertEquals(2, cache.get(2));
        assertEquals(3, cache.get(3));
    }

    @Test
    void testFrequencyUpdate() {
        cache.put(1, 1);    // (1, freq=1)
        cache.put(2, 2);    // (1, freq=1), (2, freq=1)
        assertEquals(1, cache.get(1)); // After this: 1 with freq=2, (2, freq=1)
        cache.put(3, 3);    // (1, freq=2), (3, freq=1), evicts (2)

        assertEquals(-1, cache.get(2)); // Should be -1, since (2) was evicted
        assertEquals(1, cache.get(1));  // Still in cache: returns 1, freq becomes 3
        assertEquals(3, cache.get(3));  // Should return 3, freq becomes 2
    }

    @Test
    void testEdgeCases() {
        LFUCache zeroCache = new LFUCache(0);
        zeroCache.put(1, 1);
        assertEquals(-1, zeroCache.get(1)); // No storage should be allowed

        LFUCache singleCache = new LFUCache(1);
        singleCache.put(1, 1);
        singleCache.put(2, 2);  // Evicts 1
        assertEquals(-1, singleCache.get(1));
        assertEquals(2, singleCache.get(2));
    }

    @Test
    void testSameFrequencyEviction() {
        cache.put(1, 1);
        cache.put(2, 2);
        cache.get(1); // Freq 1: {2}, Freq 2: {1}
        cache.put(3, 3); // Evicts 2 since 1 has higher frequency
        assertEquals(-1, cache.get(2));
        assertEquals(1, cache.get(1)); // 1 remains
        assertEquals(3, cache.get(3)); // 3 was just added
    }
}
