package com.practise.algorithm.sorting.impl.stable;

import com.practise.commons.Utils;

import java.util.Arrays;

/**
 * BubbleSort is a simple comparison-based sorting algorithm that repeatedly steps through the list,
 * compares adjacent elements, and swaps them if they are in the wrong order.
 * This process is repeated until the list is sorted.
 * <p>
 * Time Complexity: O(N^2)
 * Space Complexity: O(1)
 * <p>
 * Best Suited Scenario:
 * 1) Most data structures.
 * 2) Collections that are in completely unsorted order.
 * 3) Not suitable for large datasets due to time complexity.
 */
public class BubbleSort {

    /**
     * Performs the iterative bubble sort on the provided array.
     *
     * @param arr the array to be sorted
     */
    public static void iterativeBubbleSort(int[] arr) {
        boolean swapped;
        int n = arr.length;

        for (int i = 0; i < n - 1; i++) {
            swapped = false;

            // Last i elements are already sorted
            for (int j = 0; j < n - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    Utils.swapByIndex(arr, j, j + 1);
                    swapped = true;
                }
            }

            // If no two elements were swapped, the array is sorted
            if (!swapped) {
                break;
            }
        }

        Utils.printArray(arr);
    }

    /**
     * LambdaTester method for testing the bubble sort implementation.
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        int[] arr = {23, 21, 4, 2, 54, 67, 86, 67, 34, 7, 6, 43, 8, 9, 665, 346, 97, 546, 675, 25, 76};
        int[] unsortedArr = arr.clone();

        iterativeBubbleSort(arr);
        Arrays.sort(unsortedArr); // Using Java's built-in sort for comparison

        // Assert that the sorted array is equal to the built-in sorted array
        assert Arrays.equals(arr, unsortedArr) : "The array is not sorted correctly.";
    }
}
