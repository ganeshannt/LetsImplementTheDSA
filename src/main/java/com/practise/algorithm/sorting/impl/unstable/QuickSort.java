package com.practise.algorithm.sorting.impl.unstable;

import com.practise.commons.Utils;

/**
 * QuickSort Algorithm
 * <p>
 * Time Complexity:
 * - Best case: O(NlogN)
 * - Average case: O(NlogN)
 * - Worst case: O(N^2) (when the pivot is poorly chosen and results in unbalanced partitions)
 * <p>
 * Space Complexity:
 * - O(N) in the worst case due to recursive stack space (depth of recursion is N in the worst case)
 * - O(logN) in the average case.
 * <p>
 * Comparison with Merge Sort:
 * - Quick sort is generally faster for larger datasets because of lower constant factors.
 * - Unlike Merge Sort, Quick Sort doesn’t require additional memory for auxiliary arrays,
 * improving space efficiency.
 */
public class QuickSort {

    /**
     * Partitions the array using the last element as the pivot.
     * Elements smaller than the pivot are moved to the left, and larger elements to the right.
     *
     * @param arr   The array to partition.
     * @param start The starting index of the sub-array to partition.
     * @param end   The ending index (pivot) of the sub-array to partition.
     * @return The index of the pivot after partitioning.
     */
    private static int quickSortPartition(int[] arr, int start, int end) {
        int i = start - 1; // Index of the smaller element
        int pivot = arr[end]; // Pivot element is the last element

        // Iterate over the array and compare each element with the pivot
        for (int j = start; j <= end - 1; j++) {
            if (arr[j] < pivot) {
                i++; // Increment index of smaller element
                Utils.swapByIndex(arr, i, j); // Swap elements to move smaller elements to the left
            }
        }

        // Place the pivot in the correct position
        Utils.swapByIndex(arr, i + 1, end);
        return i + 1; // Return the pivot index
    }

    /**
     * Recursive QuickSort function.
     * It partitions the array and recursively sorts the sub-arrays.
     *
     * @param arr   The array to sort.
     * @param start The starting index of the sub-array.
     * @param end   The ending index of the sub-array.
     */
    private static void recursiveQuickSort(int[] arr, int start, int end) {
        if (start < end) {
            // Partition the array and get the pivot index
            int pi = quickSortPartition(arr, start, end);

            // Recursively sort elements before and after the partition
            recursiveQuickSort(arr, start, pi - 1); // Sort the left sub-array
            recursiveQuickSort(arr, pi + 1, end); // Sort the right sub-array

            // Optionally print the array at each recursive step for debugging
            Utils.printArray(arr);
        }
    }

    /**
     * Wrapper function to simplify QuickSort call.
     *
     * @param arr The array to sort.
     */
    private static void QuickSortImp(int[] arr) {
        recursiveQuickSort(arr, 0, arr.length - 1); // Start the recursive quick sort
    }

    public static void main(String[] args) {
        // Test arrays
        int[] arr2 = {23, 21, 4, 2, 54, 67, 86, 67, 34, 7, 6, 43, 8, 9, 665, 346, 97, 546, 675, 25, 76};
        int[] arr = {9, 6, 5, 0, 8, 2};

        // Perform QuickSort
        System.out.println("Unsorted Array:");
        Utils.printArray(arr2);
        QuickSortImp(arr2); // Sorts arr2
        System.out.println("Sorted Array:");
        Utils.printArray(arr2);
    }
}
