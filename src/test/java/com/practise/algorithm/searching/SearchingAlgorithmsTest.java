package com.practise.algorithm.searching;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

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
    @Disabled("TODO: This test is incomplete")
    void testRecursiveLinearSearchImpl2() {
        // TODO: This test is incomplete.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.ArrayIndexOutOfBoundsException: Index -1 out of bounds for length 4
        //       at com.practise.algorithm.searching.SearchingAlgorithms.recursiveLinearSearchImpl(SearchingAlgorithms.java:24)
        //   In order to prevent recursiveLinearSearchImpl(int[], int, int)
        //   from throwing ArrayIndexOutOfBoundsException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   recursiveLinearSearchImpl(int[], int, int).
        //   See https://diff.blue/R013 to resolve this issue.

        SearchingAlgorithms.recursiveLinearSearchImpl(new int[]{1, 1, 1, 1}, 1, -1);
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
    @Disabled("TODO: This test is incomplete")
    void testRecursiveBinarySearchImpl2() {
        // TODO: This test is incomplete.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.ArrayIndexOutOfBoundsException: Index 2 out of bounds for length 0
        //       at com.practise.algorithm.searching.SearchingAlgorithms.recursiveBinarySearchImpl(SearchingAlgorithms.java:60)
        //   In order to prevent recursiveBinarySearchImpl(int[], int, int, int)
        //   from throwing ArrayIndexOutOfBoundsException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   recursiveBinarySearchImpl(int[], int, int, int).
        //   See https://diff.blue/R013 to resolve this issue.

        SearchingAlgorithms.recursiveBinarySearchImpl(new int[]{}, 1, 1, 3);
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

