package com.practise.ds.array.problem;

import com.practise.commons.Utils;


/*

Name - Rotate Array
Link - https://leetcode.com/problems/rotate-array/

 */

public class RotateArrayKtimes {

    public static void main(String[] args) {
        RotateArrayKtimes element = new RotateArrayKtimes();
        int arr[] = {1, 2, 3, 4, 5};
        element.bruteForceApproach(arr, 3);
    }

    /*
    Time Complexity - O(n*k) where k is number of rotation
    Space Complexity - o(1)
    Note - rotate k times
    */
    private void bruteForceApproach(int[] arr, int k) {
        for (int i = 0; i < k; i++) {
            rotateOnce(arr);
            Utils.printArray(arr);
        }
    }

    //    n-2 to 0 is the key
    private void rotateOnce(int arr[]) {
        int temp = arr[arr.length - 1];
        for (int i = arr.length - 2; i >= 0; i--) {
            arr[i + 1] = arr[i];
        }
        arr[0] = temp;
    }

    /*
    Time Complexity - O(k) + O(n-k) + O(n) =>O(n)
    Space Complexity - o(1)
    Note - reverse last k elements and then reverse n-k elements and then reverse n elements
    */
    private void bestApproach(int[] arr, int k) {
        int n = arr.length;
        if (n == 0 || n < 2) {
            return;
        }
//       edge case to cover if k is more than n
        k = k % n;

        reverse(arr, n - k, n - 1);
        reverse(arr, 0, n - k - 1);
        reverse(arr, 0, n - 1);
        for (int i : arr) {
            System.out.println(i);
        }
    }

    private void reverse(int arr[], int low, int high) {
        while (low < high) {
            Utils.swapByIndex(arr, low, high);
            low++;
            high--;
        }
    }
}
