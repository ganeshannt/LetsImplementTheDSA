package com.practise.algorithm.sorting.impl.unstable;

import com.practise.commons.Utils;

/*
Process:
Selection Sort is an in-place comparison-based sorting algorithm that divides the list into a sorted and unsorted section.
Initially, the sorted part is empty and the unsorted part is the entire array.
In each iteration, the smallest element from the unsorted part is selected and swapped with the leftmost unsorted element.
This process continues until the array is fully sorted.

Time Complexity: O(N^2)
Space Complexity: O(1)

Best Suited Scenario:
1) Array data structures
2) Completely unsorted arrays
3) Small datasets (inefficient for large datasets due to O(N^2) time complexity)
*/

public class SelectionSort {

    public static void selectionSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;

            // Find the minimum element in the unsorted part
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }

            // Swap the found minimum element with the first unsorted element
            if (minIndex != i) {
                Utils.swapByIndex(arr, i, minIndex);
            }
        }

        // Print the sorted array
        Utils.printArray(arr);
    }

    public static void main(String[] args) {
        int[] arr = {23, 21, 4, 2, 54, 67, 86, 67, 34, 7, 6, 43, 8, 9, 665, 346, 97, 546, 675, 25, 76};
        selectionSort(arr);
    }
}
