package com.practise.algorithm.searching;

import java.util.Arrays;

//sdfsd
/*
hi .... 
*/

public class SearchingAlgorithms {

    public static int linearSearch(int arr[], int searchElement) {
        for (int i = 0; i < arr.length; i++) {
            if (searchElement == arr[i])
                return i;
        }
        return -1;
    }


    public static int recursiveLinearSearchImpl(int arr[], int searchElement, int index) {
        if (index >= arr.length)
            return -1;
        if (arr[index] == searchElement)
            return index;
        else
            return recursiveLinearSearchImpl(arr, searchElement, index + 1);
    }

    public static int recursiveLinearSearch(int arr[], int searchElement) {
        if (arr.length == 0)
            return -1;
        return recursiveLinearSearchImpl(arr, searchElement, 0);
    }


    //    It should be sorted
    public static int iterativeBinarySearch(int arr[], int searchElement) {
        int start = 0;
        int end = arr.length;
        while (start < end) {
            int mid = (start + end) / 2;
            if (arr[mid] == searchElement)
                return arr[mid];
            if (arr[mid] > searchElement)
                end = mid - 1;
            else if (arr[mid] < searchElement)
                start = mid + 1;
        }
        return -1;
    }


    public static int recursiveBinarySearchImpl(int arr[], int searchElement, int start, int end) {
        if (start >= end)
            return -1;

        int mid = (start + end) / 2;

        if (arr[mid] == searchElement)
            return mid;
        else if (arr[mid] < searchElement)
            return recursiveBinarySearchImpl(arr, searchElement, mid + 1, end);
        else
            return recursiveBinarySearchImpl(arr, searchElement, start, mid - 1);
    }

    public static int recursiveBinarySearch(int arr[], int searchElement) {
        if (arr.length == 0) {
            return -1;
        }
        return recursiveBinarySearchImpl(arr, searchElement, 0, arr.length);
    }


    public static void main(String Args[]) {
        int arr[] = {23, 21, 4, 2, 54, 67, 86, 67, 34, 7, 6, 43, 8, 9, 665, 346, 97, 546, 675, 25, 76};
        int arr1[] = {15};
//        System.out.println(linearSearch(arr, 7));
        Arrays.sort(arr);
//        System.out.println(iterativeBinarySearch(arr,2342));
//        System.out.println(recursiveBinarySearch(arr,2323));
        System.out.println(recursiveLinearSearch(arr1, 1));
    }
}