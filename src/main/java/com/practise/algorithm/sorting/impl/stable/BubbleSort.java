package com.practise.algorithm.sorting.impl.stable;

import com.practise.commons.Utils;

import java.util.Arrays;

/**
 * Algorithm: Bubble Sort
 * 
 * Description: BubbleSort is a simple comparison-based sorting algorithm that repeatedly steps through the list,
 * compares adjacent elements, and swaps them if they are in the wrong order.
 * This process is repeated until the list is sorted.
 * 
 * Category: Stable sorting algorithm
 * 
 * Time Complexity: 
 * - Best Case: O(n) when array is already sorted
 * - Average Case: O(n²)
 * - Worst Case: O(n²)
 * 
 * Space Complexity: O(1) - In-place sorting algorithm
 * 
 * Best Suited Scenarios:
 * 1) Educational purposes to understand sorting concepts
 * 2) Small datasets where simplicity is more important than efficiency
 * 3) Nearly sorted arrays where only a few elements are out of place
 * 
 * Not Suitable For:
 * 1) Large datasets due to quadratic time complexity
 * 2) Performance-critical applications
 * 
 * Visualization: https://visualgo.net/en/sorting
 */
public class BubbleSort {

    /**
     * Performs the iterative bubble sort on the provided array.
     * This implementation includes an optimization that breaks the loop if no swaps are made in a pass,
     * which improves performance for nearly sorted arrays.
     *
     * Time Complexity: 
     * - Best Case: O(n) when array is already sorted (no swaps in first pass)
     * - Average Case: O(n²)
     * - Worst Case: O(n²) when array is sorted in reverse order
     *
     * Space Complexity: O(1) - Only uses a few variables regardless of input size
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
