package com.practise.problemsolving.array;

import java.util.ArrayList;
import java.util.List;

public class RotateArrayKtimes {

    private void avarageApproach(int[] arr, int k) {
        int temp[] = new int[arr.length - 1];
        int rotateIndex = arr.length - k;
        int i = 0;
        while (rotateIndex < arr.length) {
            temp[i++] = arr[rotateIndex++];
        }
        rotateIndex = 0;
        while (i < temp.length) {
            temp[i++] = arr[rotateIndex++];
        }
        arr = temp;
        for (int element : arr) {
            System.out.println(element);
        }
    }

    private void approach2(int[] arr, int k) {
        int temp = 0;
        int val = 0;
        while (k > 0) {
            temp = arr[arr.length - 1];
            int v = 0;
            for (int i = 0; i < arr.length - 1; i++) {
                v = arr[i + 1];
                arr[i + 1] = arr[i];

            }
            arr[0] = temp;
            k--;
        }
        for (int element : arr) {
            System.out.println(element);
        }
    }

    private void bestApproach3(int[] arr, int k) {
        int n = arr.length;
        reverse(arr, n - k, n - 1);
        reverse(arr, 0, n - k - 1);
        reverse(arr, 0, n - 1);
        for (int i : arr) {
            System.out.println(i);
        }
    }

    private void reverse(int arr[], int low, int high) {

        while (low < high) {
            swap(arr, low, high);
            low++;
            high--;
        }
    }

    private void swap(int arr[], int first, int second) {
        int temp = arr[first];
        arr[first] = arr[second];
        arr[second] = temp;
    }

    public void rotateLeft(int d, List<Integer> arr) {
        // Write your code here
        int n = arr.size();
        d = n-d;
        reverseList(arr, n - d, n - 1);
        reverseList(arr, 0, n - d - 1);
        reverseList(arr, 0, n - 1);
    }

    private void reverseList(List<Integer> arr, int low, int high) {
        while (low < high) {
            swapList(arr, low, high);
            low++;
            high--;
        }
    }

    private void swapList(List<Integer> arr, int first, int second) {
        int temp = arr.get(first);
        arr.set(first, arr.get(second));
        arr.set(second, temp);
    }

    public static void main(String[] args) {
        RotateArrayKtimes element = new RotateArrayKtimes();
        int arr[] = { 1, 2, 3, 4, 5 };
        element.bestApproach3(arr, 3);
        List<Integer> arrList = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            arrList.add(i, arr[i]);
        }
        element.rotateLeft(4,arrList);
    }
}
