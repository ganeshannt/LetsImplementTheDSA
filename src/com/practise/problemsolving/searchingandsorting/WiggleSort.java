package com.practise.problemsolving.searchingandsorting;

import java.util.Arrays;

public class WiggleSort {

    public void approach1(int arr[]) {
        Arrays.sort(arr);
        for (int i = 0; i < arr.length - 1; i++) {
            if (i % 2 != 0) {
                swap(arr, i, i + 1);
                i++;
            }
        }
        for (int i : arr) {
            System.out.println(i);
        }
    }

    public void approach2(int arr[]) {
        for (int i = 0; i < arr.length - 1; i++) {
            if (i % 2 != 0) {
                if (arr[i - 1] > arr[i]) {
                    swap(arr, i - 1, i);
                }
                if (arr[i + 1] > arr[i]) {
                    swap(arr, i + 1, i);
                }
            }
        }
        for (int i : arr) {
            System.out.println(i);
        }
    }

    private void swap(int arr[], int first, int second) {
        int temp = arr[first];
        arr[first] = arr[second];
        arr[second] = temp;
    }

    public static void main(String[] args) {
        WiggleSort sort = new WiggleSort();
        int arr[] = { 3,5,2,1,6,4 };
        sort.approach2(arr);
    }
}
