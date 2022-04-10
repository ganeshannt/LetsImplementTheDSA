package com.practise.algorithm.sorting.unstable;

import com.practise.commons.Utils;

/*

Time Complexity ( best and average cases): O(NlogN), O(N2) - worst case
Space Complexity: O(N)

Comparison with Merge Sort
    With quick sort, there is no need to use the auxiliary array that we used in the merge sort approach.
    This reduces the space complexity and choosing a random pivot from array improves the time complexity as well.
*/


public class QuickSort {

    // Quick sort partitioning
    private static int quickSortPartition(int arr[], int start, int end) {
        int i = start - 1;
        int pivot = arr[end];
        for (int j = start; j <= end - 1; j++) {
            if (arr[j] < pivot) {
                i++;
                Utils.swapByIndex(arr, i, j);
            }
        }
        Utils.swapByIndex(arr, i + 1, end);
        return (i + 1);
    }

    private static void recursiveQuickSort(int arr[], int start, int end) {
        if (start < end) {
            int pi = quickSortPartition(arr, start, end);
            recursiveQuickSort(arr, start, pi - 1);
            recursiveQuickSort(arr, pi + 1, end);
            Utils.printArray(arr);
        }
    }

    // Wrapper class for quick sort
    private static void QuickSortImp(int arr[]) {
        recursiveQuickSort(arr, 0, arr.length - 1);
    }

    public static void main(String[] args) {
        int arr2[] = {23, 21, 4, 2, 54, 67, 86, 67, 34, 7, 6, 43, 8, 9, 665, 346, 97, 546, 675, 25, 76};
        int arr[] = {9, 6, 5, 0, 8, 2};
        QuickSortImp(arr2);
    }
}
