package com.practise.algorithm.sorting.unstable;

import com.practise.CommonUtils;

/*
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
                CommonUtils.swapByIndex(arr, i, min_index);
            }
        }
        CommonUtils.printArray(arr);
    }

    public static void main(String[] args) {
        int arr[] = {23, 21, 4, 2, 54, 67, 86, 67, 34, 7, 6, 43, 8, 9, 665, 346, 97, 546, 675, 25, 76};
        iterativeSelectionSort(arr);
    }
}
