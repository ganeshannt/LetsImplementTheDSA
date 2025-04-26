package com.practise.java.generics;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GenericArrayTest {

    @Test
    void testAddAndGet() {
        GenericArray<String> genericArray = new GenericArray<>(2);
        genericArray.add("Hello");
        genericArray.add("World");

        assertEquals("Hello", genericArray.get(0));
        assertEquals("World", genericArray.get(1));
    }

    @Test
    void testResize() {
        GenericArray<Integer> genericArray = new GenericArray<>(2);
        genericArray.add(1);
        genericArray.add(2);
        genericArray.add(3); // Should trigger resize

        assertEquals(1, genericArray.get(0));
        assertEquals(2, genericArray.get(1));
        assertEquals(3, genericArray.get(2));
    }

    @Test
    void testAddBeyondInitialCapacity() {
        GenericArray<Integer> genericArray = new GenericArray<>(1);
        genericArray.add(1);
        genericArray.add(2); // Should trigger resize
        genericArray.add(3);

        assertEquals(1, genericArray.get(0));
        assertEquals(2, genericArray.get(1));
        assertEquals(3, genericArray.get(2));
    }

    @Test
    void testRemoveNonExistentElement() {
        GenericArray<String> genericArray = new GenericArray<>(3);
        genericArray.add("X");
        genericArray.add("Y");
        genericArray.add("Z");

        genericArray.remove("A"); // Element not in the array

        assertEquals("X", genericArray.get(0));
        assertEquals("Y", genericArray.get(1));
        assertEquals("Z", genericArray.get(2));
    }

    @Test
    void testRemoveFromEmptyArray() {
        GenericArray<String> genericArray = new GenericArray<>(3);
        genericArray.remove("A"); // Attempt to remove from an empty array

        assertNull(genericArray.get(0));
    }

    @Test
    void testAddNullElement() {
        GenericArray<String> genericArray = new GenericArray<>(2);
        genericArray.add(null); // Adding a null element
        genericArray.add("Test");

        assertNull(genericArray.get(0));
        assertEquals("Test", genericArray.get(1));
    }

    @Test
    void testRemoveNullElement() {
        GenericArray<String> genericArray = new GenericArray<>(2);
        genericArray.add(null);
        genericArray.add("Test");

        genericArray.remove(null); // Removing a null element

        assertEquals("Test", genericArray.get(0));
        assertNull(genericArray.get(1));
    }

    @Test
    void testGetFromEmptyArray() {
        GenericArray<Integer> genericArray = new GenericArray<>(2);

        assertNull(genericArray.get(0)); // Attempt to get from an empty array
    }

    @Test
    void testInvalidIndex() {
        GenericArray<Integer> genericArray = new GenericArray<>(2);
        genericArray.add(10);

        assertNull(genericArray.get(-1)); // Invalid index
        assertNull(genericArray.get(5));  // Invalid index
    }

    @Test
    void testIsEmpty() {
        GenericArray<String> genericArray = new GenericArray<>();
        assertTrue(genericArray.get(0) == null); // Empty array should return null
    }
}