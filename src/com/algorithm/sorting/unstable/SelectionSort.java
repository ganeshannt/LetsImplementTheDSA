package com.algorithm.sorting.unstable;
import com.algorithm.sorting.PrintOutput;

public class SelectionSort {
    
    public static void iterativeSelectionSort(int[] arr) {
        int min,min_index;
        for (int i = 0; i < arr.length-1; i++) {
            min = arr[i];
            min_index = i;
            for (int j = i+1; j < arr.length; j++) {
                if(min>arr[j]){
                    min = arr[j];
                    min_index = j;
                }
            }
            if(i!=min_index){
                arr[min_index] = arr[i] + arr[min_index];
                arr[i] = arr[min_index] - arr[i];
                arr[min_index] = arr[min_index]-arr[i];
            }
        }
        PrintOutput.printArray(arr);
    }

    public static void main(String[] args) {
        int arr[] = { 23, 21, 4, 2, 54, 67, 86, 67, 34, 7, 6, 43, 8, 9, 665, 346, 97, 546, 675, 25, 76 };
        iterativeSelectionSort(arr);
    }
}
