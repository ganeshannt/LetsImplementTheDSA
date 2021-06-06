package com.algorithm.sorting.stable;

public class BubbleSort {

    /*
    Bubble sort, also referred to as comparison sort, is a simple sorting algorithm that repeatedly goes through the list, compares adjacent elements and swaps them if they are in the wrong order. This is the most simplest algorithm and inefficient at the same time.
    Yet, it is very much necessary to learn about it as it represents the basic foundations of sorting.
     */

    public static void iterativeBubbleSort(int arr[]){
        int key,val;
        for (int i = 0; i < arr.length-1; i++) {
            key = i;
            for (int j = 1; j < arr.length; j++) {
                    if(arr[key] >arr[j]) {
                        val = arr[key];
                        arr[key] = arr[j];
                        arr[j] = val;
                    }
            }
        }
        printArray(arr);
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
        iterativeBubbleSort(arr);
    }
}
