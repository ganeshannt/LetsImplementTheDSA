package com.practise.algorithm.sorting.unstable;

import com.practise.commons.Utils;

/*

Process
This algorithm is an in-place comparison-based algorithm that divided the list into two parts, the sorted part at the left end and the unsorted part at the right end.
Initially, the sorted part is empty and the unsorted part is the entire list.
The smallest element is selected from the unsorted array and swapped with the leftmost element, and that element becomes a part of the sorted array.
This process continues moving unsorted array boundary by one element to the right.

Time Complexity: O(N2)
Space Complexity: O(1)

Best Suited Scenario:
1)  Array data structures
2)  given collection is in completely unsorted order.
3)  due to time complexity, it is not feasible large number of data set

 */

public class SelectionSort {

    public static void iterativeSelectionSort(int[] arr) {
        int min, min_index;
        for (int i = 0; i < arr.length - 1; i++) {
            min = arr[i];
            min_index = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (min > arr[j]) {
                    min = arr[j];
                    min_index = j;
                }
            }
            if (i != min_index) {
                Utils.swapByIndex(arr, i, min_index);
            }
        }
        Utils.printArray(arr);
    }

    public static void main(String[] args) {
        int arr[] = {23, 21, 4, 2, 54, 67, 86, 67, 34, 7, 6, 43, 8, 9, 665, 346, 97, 546, 675, 25, 76};
        iterativeSelectionSort(arr);
    }
}
