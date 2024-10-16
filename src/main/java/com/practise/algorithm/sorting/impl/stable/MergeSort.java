package com.practise.algorithm.sorting.impl.stable;

import com.practise.commons.Utils;

/*
Time Complexity (all cases): O(NlogN)
Space Complexity: O(N)

Best Suited Scenario:
    1) Array data structures
    2) Given collection is in completely unsorted order.
    3) Efficient for very large datasets due to its O(NlogN) time complexity.
*/

public class MergeSort {

    // Recursive Merge Sort
    public static void recursiveMergeSort(int[] arr, int start, int end) {
        if (start < end) {
            int mid = start + (end - start) / 2; // To avoid overflow
            recursiveMergeSort(arr, start, mid); // Sort first half
            recursiveMergeSort(arr, mid + 1, end); // Sort second half
            merge(arr, start, mid, end); // Merge the sorted halves
        }
    }

    // Merge function to merge two sorted sub-arrays into one
    public static void merge(int[] arr, int start, int mid, int end) {
        int[] temp = new int[end - start + 1];
        int i = start, j = mid + 1, k = 0;

        // Merge the two sub-arrays into temp[]
        while (i <= mid && j <= end) {
            if (arr[i] <= arr[j]) {
                temp[k++] = arr[i++];
            } else {
                temp[k++] = arr[j++];
            }
        }

        // Copy remaining elements of the left sub-array (if any)
        while (i <= mid) {
            temp[k++] = arr[i++];
        }

        // Copy remaining elements of the right sub-array (if any)
        while (j <= end) {
            temp[k++] = arr[j++];
        }

        // Copy the sorted sub-array back into the original array
        for (i = start; i <= end; i++) {
            arr[i] = temp[i - start];
        }

        // Copy the merged array back into the original array
        System.arraycopy(temp, 0, arr, start, temp.length);
    }


    // Iterative Merge Sort
    public static void iterativeMergeSort(int[] arr) {
        int n = arr.length;

        // Size of the sub-arrays to merge, starting from 1 and doubling each iteration
        for (int currSize = 1; currSize < n; currSize *= 2) {
            // Pick starting point of different sub-arrays of current size
            for (int leftStart = 0; leftStart < n - 1; leftStart += 2 * currSize) {
                // Find mid point and right end for current sub-array
                int mid = Math.min(leftStart + currSize - 1, n - 1);
                int rightEnd = Math.min(leftStart + 2 * currSize - 1, n - 1);

                // Merge the sub-arrays
                merge(arr, leftStart, mid, rightEnd);
            }
        }
    }

    // Main method to test both recursive and iterative versions of merge sort
    public static void main(String[] args) {
        int[] arr = {1, 234, 45, 3453, 45345, 2354, 34545, 43, 2, 34, 435345, 23};

        System.out.println("Original Array:");
        Utils.printArray(arr);

        // Test Recursive Merge Sort
        recursiveMergeSort(arr, 0, arr.length - 1);
        System.out.println("Sorted Array (Recursive):");
        Utils.printArray(arr);

        // Reset the array for iterative test
        int[] arr2 = {1, 234, 45, 3453, 45345, 2354, 34545, 43, 2, 34, 435345, 23};

        // Test Iterative Merge Sort
        iterativeMergeSort(arr2);
        System.out.println("Sorted Array (Iterative):");
        Utils.printArray(arr2);
    }
}
