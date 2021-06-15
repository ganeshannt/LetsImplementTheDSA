package com.algorithm.sorting.stable;

import com.algorithm.sorting.PrintOutput;

public class MergeSort {

    private static int[] sortArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    arr[j] = arr[j] + arr[j + 1];
                    arr[j + 1] = arr[j] - arr[j + 1];
                    arr[j] = arr[j] - arr[j + 1];
                }
            }
        }
        PrintOutput.printArray(arr);
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

        for (int i = 0, j = 0, k = 0; i < firstarr.length && j < secondarr.length;) {
            if (firstarr[i] < secondarr[j]) {
                arr[k] = firstarr[i];
                i += 1;
            } else {
                arr[k] = secondarr[j];
                j += 1;
            }
            k += 1;
        }
        PrintOutput.printArray(arr);
    }


    public static void mergeProcedure(int arr[], int start, int end, int mid) {
        int i, j, temp_index = 0;
        if (arr[mid] > arr[mid - 1]) {
            return;
        }

        i = start;
        j = mid;

        int[] temparr = new int[end - start];

        while (i < mid && j < end) {
            if (arr[i] < arr[j]) {
                temparr[temp_index] = arr[i];
                i += 1;
            } else {
                temparr[temp_index] = arr[j];
                j += 1;
            }
            temp_index += 1;
        }

        while (i < mid) {
            temparr[temp_index] = arr[i];
            i += 1;
            temp_index += 1;
        }

        while (j < end) {
            temparr[temp_index] = arr[j];
            j += 1;
            temp_index += 1;
        }
        System.out.println();
        PrintOutput.printArray(temparr);
    }

    public static void recursiveMergeSort(int arr[], int start, int end) {
        if ((end - start) < 2) {
            return;
        }

        int mid = (end + start) / 2;
        recursiveMergeSort(arr, start, mid);
        recursiveMergeSort(arr, mid, end);
        mergeProcedure(arr, start, end, mid);
    }

    public static void main(String Args[]) {
        int arr[] = { 23, 21, 4, 2, 54, 67, 86, 67, 34, 7, 6, 43, 8, 9, 665, 346, 97, 546, 675, 25, 76 };
        int arr2[] = { 9, 6, 5, 0, 8, 2 };
        // iterativeSelectionSort(arr2);
        recursiveMergeSort(arr, 0, arr.length);
    }

}
