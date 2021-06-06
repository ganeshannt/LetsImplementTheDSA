package com.algorithm.sorting.stable;

public class InsertionSort {


    public static void iterativeInsertionSort(int arr[]) {
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
        printArray(arr);
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
        printArray(arr);
    }

    public static void recursiveInsertionSort(int arr[]) {
        recursiveInsertionSortImpl(arr, arr.length);
    }


    public static void printArray(int arr[]) {
        System.out.print("[ ");
        for (int i : arr) {
            System.out.print(i + " ");
        }
        System.out.print(" ]");
    }

    public static void main(String Args[]) {
        int arr[] = {23, 21, 4, 2, 54, 67, 86, 67, 34, 7, 6, 43, 8, 9, 665, 346, 97, 546, 675, 25, 76};
//        iterativeInsertionSort(arr);
        recursiveInsertionSort(arr);
    }

}
