package com.practise.ds.array.problem;

import com.practise.commons.Utils;

/**
 * Problem: Product of Array Except Self
 * 
 * Description: Given an array nums of n integers where n > 1, return an array output such that 
 * output[i] is equal to the product of all the elements of nums except nums[i].
 * 
 * LeetCode: https://leetcode.com/problems/product-of-array-except-self/
 * 
 * Approach: Multiple approaches are implemented:
 * 1. Using separate left and right product arrays
 * 2. Using a single output array and a right variable
 * 3. Using a more readable approach with pre and post variables
 * 
 * Time Complexity: O(n) for all approaches
 * Space Complexity: O(n) for first approach, O(1) for optimized approaches (excluding output array)
 * 
 * Hint: Think about calculating products from left to right and then from right to left
 */
public class ArrayProduct {

    public static void main(String[] args) {
        int arr[] = {1, 2, 3, 4};
        ArrayProduct arrayProduct = new ArrayProduct();
        arrayProduct.firstApproach(arr);
    }

    /**
     * First approach using separate left and right product arrays.
     * 
     * Time Complexity: O(n) - Three passes through the array
     * Space Complexity: O(n) - Uses two additional arrays of size n
     * 
     * @param arr Input array
     */
    private void firstApproach(int[] arr) {
        int[] left = new int[arr.length];
        int[] right = new int[arr.length];

        left[0] = 1;
        for (int i = 0; i < arr.length - 1; i++) {
            left[i + 1] = left[i] * arr[i];
        }

        right[arr.length - 1] = 1;
        for (int i = arr.length - 1; i > 0; i--) {
            right[i - 1] = right[i] * arr[i];
        }

        int[] output = new int[arr.length];

        for (int i = 0; i < output.length; i++) {
            output[i] = left[i] * right[i];
        }

        Utils.printArray(output);

    }

    /**
     * Optimized approach using a single output array and a right variable.
     * 
     * Time Complexity: O(n) - Two passes through the array
     * Space Complexity: O(1) - Uses only one additional variable (excluding output array)
     * 
     * @param arr Input array
     */
    private void bestApproach(int[] arr) {
        int[] output = new int[arr.length];

        output[0] = 1;
        for (int i = 0; i < arr.length - 1; i++) {
            output[i + 1] = output[i] * arr[i];
        }

        int right = 1;
        for (int i = arr.length - 1; i > 0; i--) {
            right *= arr[i];
            output[i - 1] = output[i - 1] * right;
        }
        Utils.printArray(output);
    }

    /**
     * Most readable optimized approach using pre and post variables.
     * 
     * Time Complexity: O(n) - Two passes through the array
     * Space Complexity: O(1) - Uses only two additional variables (excluding output array)
     * 
     * @param arr Input array
     */
    private void bestAndReadableApproach(int[] arr) {
        int[] output = new int[arr.length];

        int n = arr.length;
        int pre = 1;
        int post = 1;

        for (int i = 0; i < n; i++) {
            output[i] = 1;
        }

        for (int i = 0; i < n; i++) {
            output[i] *= pre;
            pre *= arr[i];
        }

        for (int i = n - 1; i >= 0; i--) {
            output[i] *= post;
            post *= arr[i];
        }

        Utils.printArray(output);
    }

}
