package com.practise.problemsolving.array;

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

    public static void main(String[] args) {
        RotateArrayKtimes element = new RotateArrayKtimes();
        int arr[] = { 1, 2, 3, 4, 5, 6, 7 };
        element.bestApproach3(arr, 3);
    }
}
