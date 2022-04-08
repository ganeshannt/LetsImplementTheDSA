package com.practise.algorithm.sorting.stable;

import com.practise.CommonUtils;

import java.util.Arrays;



/*
process
Bubble sort, also referred to as comparison sort, is a simple sorting
algorithm that repeatedly goes through the list, compares adjacent elements
and swaps them if they are in the wrong order. This is the simplest
algorithm and inefficient at the same time. Yet, it is very much necessary to
learn about it as it represents the basic foundations of sorting.

Time Complexity: O(N2)
Space Complexity: O(1)

Best Suited Scenario:
1)  most of the data structures
2)  given collection is in completely unsorted order.
3)  due to time complexity, it is not feasible large number of data set

*/

public class BubbleSort {

    public static void iterativeBubbleSort(int arr[]) {
        boolean swapped = false;
        for (int i = 0; i < arr.length; i++) {
            swapped = false;
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    CommonUtils.swapByIndex(arr, j, j + 1);
                    swapped = true;
                }
            }
//          if given array is sorted then it will come out in first iteration itself
            if (!swapped) {
                break;
            }
        }
        CommonUtils.printArray(arr);
    }

    public static void main(String Args[]) {
        int arr[] = {23, 21, 4, 2, 54, 67, 86, 67, 34, 7, 6, 43, 8, 9, 665, 346, 97, 546, 675, 25, 76};
        int unsorted_arr[] = arr.clone();
        iterativeBubbleSort(arr);
        Arrays.sort(unsorted_arr);
        assert arr.equals(unsorted_arr);
    }
}
