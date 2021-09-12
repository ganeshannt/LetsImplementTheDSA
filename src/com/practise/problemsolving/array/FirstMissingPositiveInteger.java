package com.practise.problemsolving.array;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

public class FirstMissingPositiveInteger {

    // tc - o(nlogn) + o(n) => o(nlogn)
    // sc - o(1)
    private int bruteForceApproach(int arr[]) {
        Arrays.sort(arr);
        int result = 1;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == result) {
                result += 1;
            }
        }
        return result;
    }

    // tc - o(n)
    // sc - o(1)
    private int arrayBasedApproach(int arr[]) {

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 1) {
                break;
            } else if (i == arr.length - 1) {
                return 1;
            }
        }

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] <= 0 || arr[i] > arr.length) {
                arr[i] = 1;
            }
        }

        int val = 0;

        for (int i = 0; i < arr.length; i++) {
            val = Math.abs(arr[i]);
            if (val == arr.length) {
                arr[0] = -Math.abs(arr[0]);
            } else {
                arr[val] = -Math.abs(arr[val]);
            }
        }

        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > 0) {
                return i;
            }
        }

        if (arr[0] > 0) {
            return arr.length;
        }
        return arr.length + 1;
    }

    @Test
    public void testFirstMissingPositiveInteger() {
        int arr[] = { 1, 2, 0 };
        FirstMissingPositiveInteger firstMissingPositiveInteger = new FirstMissingPositiveInteger();
        Assert.assertEquals(3, firstMissingPositiveInteger.arrayBasedApproach(arr));
    }
}
