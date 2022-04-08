package com.practise.algorithm.sorting.stable;

import com.practise.CommonUtils;

import java.util.ArrayList;
import java.util.List;

/*
 in-place comparison-based sorting algorithms.
 In this algorithm, a sub-collection is maintained which is always in order(sorted)

Time Complexity: O(N2). O(N) - if given array is already sorted
Space Complexity: O(1)

Best Suited Scenario:
1)  Array and Linked list data structures
2)  given collection is in partially unsorted order
3)  due to time complexity, it is not feasible large number of data set
 */

public class InsertionSort {

    public static void iterativeInsertionSort(int arr[]) {
//        iterate from second element to last element
        for (int i = 1; i < arr.length; i++) {
//            iterate from i to 0 th index to set given element in correct position
            for (int j = i; j > 0; j--) {
                if (arr[j - 1] > arr[j]) {
                    CommonUtils.swapByIndex(arr, j, j - 1);
                } else {
                    break;
                }
            }
        }
        CommonUtils.printArray(arr);
    }

    public static void iterativeInsertionSort0(int arr[]) {
        int i, j, key;
        for (i = 1; i < arr.length; i++) {
            key = arr[i];
            j = i - 1;
            while (j >= 0 && key < arr[j]) {
                arr[j + 1] = arr[j];
                j -= 1;
            }
            arr[j + 1] = key;
        }
        CommonUtils.printArray(arr);
    }

    public static void recursiveInsertionSortImpl(int arr[], int n) {

        if (n <= 1) {
            return;
        }

        recursiveInsertionSortImpl(arr, n - 1);
        int j = n - 2;
        int key = arr[n - 1];
        while (j >= 0 && key < arr[j]) {
            arr[j + 1] = arr[j];
            j -= 1;
        }
        arr[j + 1] = key;
        System.out.println();
        CommonUtils.printArray(arr);
    }

    public static void recursiveInsertionSort(int arr[]) {
        recursiveInsertionSortImpl(arr, arr.length);
    }

    public static void insertionSort1(int n, List<Integer> arr) {
        List<Integer> arr1 = new ArrayList<>();

        for (int i : arr) {
            System.out.println(i + " ");
        }
    }

    public static void main(String Args[]) {
        int arr[] = {23, 21, 4, 2, 54, 67, 86, 67, 34, 7, 6, 43, 8, 9, 665, 346, 97, 546, 675, 25, 76};
        iterativeInsertionSort(arr);
        //recursiveInsertionSort(arr);
    }

}
