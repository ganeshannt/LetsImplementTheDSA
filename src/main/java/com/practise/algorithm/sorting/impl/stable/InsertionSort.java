package com.practise.algorithm.sorting.impl.stable;

import com.practise.commons.Utils;

import java.util.Arrays;

/**
 * Insertion Sort - In-place, stable, comparison-based sorting algorithm.
 * <p>
 * Time Complexity: O(N^2) in the average and worst case.
 * Best Case: O(N) if the array is already sorted.
 * Space Complexity: O(1) - In-place algorithm.
 * <p>
 * Best Suited Scenarios:
 * 1. Small or partially sorted data sets.
 * 2. Arrays or linked lists.
 * 3. Inefficient for large datasets due to quadratic time complexity.
 */
public class InsertionSort {

    /**
     * Iterative Insertion Sort.
     *
     * @param arr The array to be sorted.
     */
    public static void iterativeInsertionSort(int[] arr) {
        // Start from the second element and iterate over the array
        for (int i = 1; i < arr.length; i++) {
            int key = arr[i];
            int j = i - 1;
            // Move elements greater than the key one position ahead to make space
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }
            // Insert the key at the correct position
            arr[j + 1] = key;
        }
        Utils.printArray(arr);
    }

    //    my approach
    public static void iterativeInsertionSort0(int[] arr) {
        // Start from the second element and iterate over the array
        for (int i = 1; i < arr.length; i++) {
            for (int j = i; j > 0; j--) {
                if (arr[j - 1] > arr[j]) {
                    Utils.swapByIndex(arr, j - 1, j); // Swap
                } else {
                    break; // Break if no swap is needed
                }
            }
        }
        Utils.printArray(arr);
    }


    /**
     * Recursive Insertion Sort.
     *
     * @param arr The array to be sorted.
     */
    public static void recursiveInsertionSort(int[] arr) {
        recursiveInsertionSortImpl(arr, arr.length);
        Utils.printArray(arr); // Assuming Utils has a method for printing the array
    }

    /**
     * Helper function for Recursive Insertion Sort.
     *
     * @param arr The array to be sorted.
     * @param n   The current length being sorted recursively.
     */
    private static void recursiveInsertionSortImpl(int[] arr, int n) {
        // Base case: If array has only one element, return
        if (n <= 1) {
            return;
        }

        // Sort the first n-1 elements
        recursiveInsertionSortImpl(arr, n - 1);

        // Insert the nth element at its correct position
        int key = arr[n - 1];
        int j = n - 2;

        while (j >= 0 && arr[j] > key) {
            arr[j + 1] = arr[j];
            j--;
        }
        arr[j + 1] = key;
    }

    /**
     * Main method to test sorting methods.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {

        int[] arr = {6, 2, 5, 5, 4, 8, 1, 3, 0, -10, -1, 12};
        var unsortedArr = arr.clone();
        Arrays.sort(unsortedArr);

        iterativeInsertionSort(arr);
        iterativeInsertionSort0(arr);
        recursiveInsertionSort(arr);

        assert Arrays.equals(arr, unsortedArr) : "The array is not sorted correctly.";
    }
}
