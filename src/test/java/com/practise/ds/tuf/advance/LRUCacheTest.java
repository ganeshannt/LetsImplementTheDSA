package com.practise.ds.tuf.advance;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LRUCacheTest {

    private LRUCache cache;

    @BeforeEach
    void setUp() {
        cache = new LRUCache(2);
    }

    @Test
    void testPutAndGet() {
        cache.put(1, 1);
        cache.put(2, 2);
        assertEquals(1, cache.get(1));  // returns 1
        cache.put(3, 3);    // evicts key 2
        assertEquals(-1, cache.get(2)); // returns -1 (not found)
        cache.put(4, 4);    // evicts key 1
        assertEquals(-1, cache.get(1)); // returns -1 (not found)
        assertEquals(3, cache.get(3));  // returns 3
        assertEquals(4, cache.get(4));  // returns 4
    }

    @Test
    void testUpdateValue() {
        cache.put(1, 1);
        cache.put(2, 2);
        assertEquals(1, cache.get(1));  // returns 1
        cache.put(1, 10);
        assertEquals(10, cache.get(1)); // returns 10
    }

    @Test
    void testSizeLimit() {
        LRUCache smallCache = new LRUCache(1);
        smallCache.put(1, 1);
        smallCache.put(2, 2);
        assertEquals(-1, smallCache.get(1)); // returns -1 (evicted)
        assertEquals(2, smallCache.get(2));  // returns 2
    }

    @Test
    void testWithEmptyCache() {
        assertEquals(-1, cache.get(100)); // returns -1 (not found)
    }
}
