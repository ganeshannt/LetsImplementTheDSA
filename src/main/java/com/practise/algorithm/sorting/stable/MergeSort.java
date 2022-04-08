package com.practise.algorithm.sorting.stable;

import com.practise.CommonUtils;



/*
Time Complexity (all cases): O(NlogN)
Space Complexity: O(N)

Best Suited Scenario:
	1)  Array data structures
	2)  given collection is in completely unsorted order.
    3)  due to time complexity, it is feasible for very large number of data set
*
* */
public class MergeSort {

    private static int[] sortArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    CommonUtils.swapByIndex(arr, j, j + 1);
                }
            }
        }
        CommonUtils.printArray(arr);
        return arr;
    }

    private static void iterativeSelectionSort(int[] arr) {
        int[] firstarr = new int[(arr.length / 2)];
        int[] secondarr = new int[(arr.length / 2)];

        for (int i = 0, j = 0; i < arr.length; i++) {
            if (i < (arr.length / 2)) {
                firstarr[i] = arr[i];
            } else {
                secondarr[j] = arr[i];
                j += 1;
            }
        }

        firstarr = sortArray(firstarr);
        secondarr = sortArray(secondarr);

        // merge procedure

        for (int i = 0, j = 0, k = 0; i < firstarr.length && j < secondarr.length; ) {
            if (firstarr[i] < secondarr[j]) {
                arr[k] = firstarr[i];
                i += 1;
            } else {
                arr[k] = secondarr[j];
                j += 1;
            }
            k += 1;
        }
        CommonUtils.printArray(arr);
    }

    public static void mergeProcedure(int arr[], int start, int end, int mid) {
        int i, j, temp_index = 0;
        i = start;
        j = mid + 1;
        int[] temparr = new int[end - start + 1];

        while (i <= mid && j <= end) {
            if (arr[i] <= arr[j]) {
                temparr[temp_index] = arr[i];
                i += 1;
            } else {
                temparr[temp_index] = arr[j];
                j += 1;
            }
            temp_index += 1;
        }

        while (i <= mid) {
            temparr[temp_index] = arr[i];
            i += 1;
            temp_index += 1;
        }
        while (j <= end) {
            temparr[temp_index] = arr[j];
            j += 1;
            temp_index += 1;
        }
        // copy temp to original interval
        for (i = start; i <= end; i += 1) {
            arr[i] = temparr[i - start];
        }
    }

    public static void recursiveMergeSort(int arr[], int start, int end) {
        if (start < end) {
            int mid = (end + start) / 2;
            recursiveMergeSort(arr, start, mid);
            recursiveMergeSort(arr, mid + 1, end);
            mergeProcedure(arr, start, end, mid);
        }
    }

    public static void main(String Args[]) {
        // int arr[] = { 23, 21, 4, 2, 54, 67, 86, 67, 34, 7, 6, 43, 8, 9, 665, 346, 97,
        // 546, 675, 25, 76 };
        int arr2[] = {1, 234, 45, 3453, 45345, 2354, 34545, 43, 2, 34, 435345, 23};
        // iterativeSelectionSort(arr2);
        recursiveMergeSort(arr2, 0, arr2.length - 1);
    }

}
