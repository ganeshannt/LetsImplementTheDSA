package com.practise.java.collection.hashing;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OurMapTest {

    @Test
    void testPutAndGet() {
        OurMap<String, Integer> map = new OurMap<>();
        map.put("A", 1);
        map.put("B", 2);
        map.put("C", 3);

        assertEquals(1, map.get("A"));
        assertEquals(2, map.get("B"));
        assertEquals(3, map.get("C"));
        assertNull(map.get("D")); // Key not present
    }

    @Test
    void testUpdateValue() {
        OurMap<String, Integer> map = new OurMap<>();
        map.put("A", 1);
        map.put("A", 2); // Update value for the same key

        assertEquals(2, map.get("A"));
    }

    @Test
    void testRemoveExistingKey() {
        OurMap<String, Integer> map = new OurMap<>();
        map.put("A", 1);
        map.put("B", 2);

        assertEquals(1, map.remove("A")); // Remove existing key
        assertNull(map.get("A")); // Key should no longer exist
        assertEquals(2, map.get("B")); // Other keys should remain unaffected
    }

    @Test
    void testRemoveNonExistentKey() {
        OurMap<String, Integer> map = new OurMap<>();
        map.put("A", 1);

        assertNull(map.remove("B")); // Removing a non-existent key
    }

    @Test
    void testRehashing() {
        OurMap<Integer, String> map = new OurMap<>();
        for (int i = 0; i < 20; i++) {
            map.put(i, "Value" + i);
        }

        for (int i = 0; i < 20; i++) {
            assertEquals("Value" + i, map.get(i)); // Ensure all values are preserved after rehashing
        }
    }

    @Test
    void testCollisionHandling() {
        OurMap<Integer, String> map = new OurMap<>();
        int key1 = 1;
        int key2 = 17; // Assuming capacity is 16, these keys will collide (1 % 16 == 17 % 16)

        map.put(key1, "Value1");
        map.put(key2, "Value2");

        assertEquals("Value1", map.get(key1));
        assertEquals("Value2", map.get(key2));
    }

    @Test
    void testSize() {
        OurMap<String, Integer> map = new OurMap<>();
        assertEquals(0, map.size());

        map.put("A", 1);
        map.put("B", 2);
        assertEquals(2, map.size());

        map.remove("A");
        assertEquals(1, map.size());
    }

    @Test
    void testNullKeyHandling() {
        OurMap<String, Integer> map = new OurMap<>();
        assertThrows(NullPointerException.class, () -> map.put(null, 1)); // Null keys are not allowed
    }

    @Test
    void testNullValueHandling() {
        OurMap<String, Integer> map = new OurMap<>();
        map.put("A", null); // Null values should be allowed
        assertNull(map.get("A"));
    }

    @Test
    void testEmptyMapBehavior() {
        OurMap<String, Integer> map = new OurMap<>();
        assertNull(map.get("A")); // Getting from an empty map
        assertNull(map.remove("A")); // Removing from an empty map
    }
}