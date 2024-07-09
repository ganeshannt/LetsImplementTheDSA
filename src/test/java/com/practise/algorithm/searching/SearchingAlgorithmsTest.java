package com.practise.algorithm.searching;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

;

class SearchingAlgorithmsTest {
    @Test
    void testLinearSearch() {
        assertEquals(0, SearchingAlgorithms.linearSearch(new int[]{1, 1, 1, 1}, 1));
        assertEquals(1, SearchingAlgorithms.linearSearch(new int[]{4, 1, 1, 1}, 1));
        assertEquals(-1, SearchingAlgorithms.linearSearch(new int[]{}, 1));
    }

    @Test
    void testRecursiveLinearSearchImpl() {
        assertEquals(1, SearchingAlgorithms.recursiveLinearSearchImpl(new int[]{1, 1, 1, 1}, 1, 1));
        assertEquals(2, SearchingAlgorithms.recursiveLinearSearchImpl(new int[]{1, 4, 1, 1}, 1, 1));
        assertEquals(-1, SearchingAlgorithms.recursiveLinearSearchImpl(new int[]{}, 1, 1));
    }

    @Test
    void testRecursiveLinearSearch() {
        assertEquals(0, SearchingAlgorithms.recursiveLinearSearch(new int[]{1, 1, 1, 1}, 1));
        assertEquals(1, SearchingAlgorithms.recursiveLinearSearch(new int[]{4, 1, 1, 1}, 1));
        assertEquals(-1, SearchingAlgorithms.recursiveLinearSearch(new int[]{}, 1));
        assertEquals(-1, SearchingAlgorithms.recursiveLinearSearch(new int[]{4, 4, 4, 4, 4, 4, 4, 4}, 1));
    }

    @Test
    void testIterativeBinarySearch() {
        assertEquals(1, SearchingAlgorithms.iterativeBinarySearch(new int[]{1, 1, 1, 1}, 1));
        assertEquals(1, SearchingAlgorithms.iterativeBinarySearch(new int[]{1, 1, 4, 1}, 1));
        assertEquals(1, SearchingAlgorithms.iterativeBinarySearch(new int[]{1, 1, 0, 1}, 1));
        assertEquals(-1, SearchingAlgorithms.iterativeBinarySearch(new int[]{}, 1));
    }

    @Test
    void testRecursiveBinarySearchImpl() {
        assertEquals(2, SearchingAlgorithms.recursiveBinarySearchImpl(new int[]{1, 1, 1, 1}, 1, 1, 3));
        assertEquals(-1, SearchingAlgorithms.recursiveBinarySearchImpl(new int[]{1, 1, 1, 1}, 1, 1, 1));
        assertEquals(-1, SearchingAlgorithms.recursiveBinarySearchImpl(new int[]{1, 1, 3, 1}, 1, 1, 3));
        assertEquals(-1, SearchingAlgorithms.recursiveBinarySearchImpl(new int[]{1, 1, 0, 1}, 1, 1, 3));
    }


    @Test
    void testRecursiveBinarySearch() {
        assertEquals(2, SearchingAlgorithms.recursiveBinarySearch(new int[]{1, 1, 1, 1}, 1));
        assertEquals(0, SearchingAlgorithms.recursiveBinarySearch(new int[]{1, 1, 4, 1}, 1));
        assertEquals(3, SearchingAlgorithms.recursiveBinarySearch(new int[]{1, 1, 0, 1}, 1));
        assertEquals(-1, SearchingAlgorithms.recursiveBinarySearch(new int[]{}, 1));
        assertEquals(-1, SearchingAlgorithms.recursiveBinarySearch(new int[]{4, 4, 4, 4, 4, 4, 4, 4}, 1));
    }
}

