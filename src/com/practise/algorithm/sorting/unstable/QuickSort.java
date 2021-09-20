package com.practise.algorithm.sorting.unstable;

import com.practise.PrintOutput;

public class QuickSort {

    // Utility function to swap values
    private static void swap(int[] arr, int i, int j) {
        if (i != j) {
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
    }

    // Quick sort partitioning
    private static int quickSortPartition(int arr[], int start, int end) {
        int i = start - 1;
        int piviot = arr[end];
        for (int j = start; j <= end - 1; j++) {
            if (arr[j] < piviot) {
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, end);
        return (i + 1);
    }

    private static void recursiveQuickSort(int arr[], int start, int end) {
        if (start < end) {
            int pi = quickSortPartition(arr, start, end);
            recursiveQuickSort(arr, start, pi - 1);
            recursiveQuickSort(arr, pi + 1, end);
            PrintOutput.printArray(arr);
        }
    }

    // Wrapper class for quick sort
    private static void QuickSortImp(int arr[]) {
        recursiveQuickSort(arr, 0, arr.length - 1);
    }

    public static void main(String[] args) {
        int arr2[] = { 23, 21, 4, 2, 54, 67, 86, 67, 34, 7, 6, 43, 8, 9, 665, 346, 97, 546, 675, 25, 76 };
        int arr[] = { 9, 6, 5, 0, 8, 2 };
        QuickSortImp(arr2);
    }
}
