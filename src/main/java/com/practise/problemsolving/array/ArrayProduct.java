package com.practise.problemsolving.array;



/*

Name - Product of Array Except Self
Link - https://leetcode.com/problems/product-of-array-except-self/

 */

public class ArrayProduct {

    public static void main(String[] args) {
        int arr[] = {1, 2, 3, 4};
        ArrayProduct arrayProduct = new ArrayProduct();
        arrayProduct.firstApproach(arr);
    }

    /*
    Time Complexity - o(n)
    Space Complexity - o(n)
    Note - skip first element while calculate left and skip last element while calculate right
     */
    private void firstApproach(int[] arr) {
        int left[] = new int[arr.length];
        int right[] = new int[arr.length];

        left[0] = 1;
        for (int i = 0; i < arr.length - 1; i++) {
            left[i + 1] = left[i] * arr[i];
        }

        right[arr.length - 1] = 1;
        for (int i = arr.length - 1; i > 0; i--) {
            right[i - 1] = right[i] * arr[i];
        }

        int output[] = new int[arr.length];

        for (int i = 0; i < output.length; i++) {
            output[i] = left[i] * right[i];
        }

        for (int i : output) {
            System.out.println(i);
        }

    }

    /*
    Time Complexity - o(n)
    Space Complexity - o(1)
    Note - optimized version of first approach
     */
    private void bestApproach(int[] arr) {
        int output[] = new int[arr.length];

        output[0] = 1;
        for (int i = 0; i < arr.length - 1; i++) {
            output[i + 1] = output[i] * arr[i];
        }

        int right = 1;
        for (int i = arr.length - 1; i > 0; i--) {
            right *= arr[i];
            output[i - 1] = output[i - 1] * right;
        }

        for (int i : output) {
            System.out.println(i);
        }
    }
}
