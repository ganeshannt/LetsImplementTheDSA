package com.practise.problemsolving.array;

import org.junit.Test;

public class ArrayProduct {

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

    public static void main(String[] args) {
        int arr[] = { 1, 2, 3, 4 };
        ArrayProduct arrayProduct = new ArrayProduct();
        arrayProduct.bestApproach(arr);
    }
}
