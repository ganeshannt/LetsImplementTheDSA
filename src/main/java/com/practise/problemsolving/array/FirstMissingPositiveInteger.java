package com.practise.problemsolving.array;

import java.util.Arrays;


/*

Name - First Missing Positive
Link - https://leetcode.com/problems/first-missing-positive/

 */
public class FirstMissingPositiveInteger {

    public static void main(String[] args) {
        int arr[] = {1, 2, 0};
        FirstMissingPositiveInteger firstMissingPositiveInteger = new FirstMissingPositiveInteger();
        System.out.println(firstMissingPositiveInteger.arrayBasedApproach(arr));
    }

    /*
    Time Complexity - o(nlogn) + o(n) => o(nlogn)
    Space Complexity - o(1)
    Note - o(n) solution only acceptable
     */
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

    /*
    Time Complexity - o(n)
    Space Complexity - o(1)
    Note - Use input array as a hash map and rebuild array such a way that element of an array
           should be 0 to n-1. here replace negative and <n element with 1.
     */
    private int arrayBasedApproach(int arr[]) {

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 1) {
                break;
            } else if (i == arr.length - 1) {
                return 1;
            }
        }

//        here replace negative and greater than n element with 1.
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] <= 0 || arr[i] > arr.length) {
                arr[i] = 1;
            }
        }

        int val = 0;
//      consider a[i] as index and negate the index element value
        for (int i = 0; i < arr.length; i++) {
            val = Math.abs(arr[i]);
            if (val == arr.length) {
                arr[0] = -Math.abs(arr[0]);
            } else {
                arr[val] = -Math.abs(arr[val]);
            }
        }
//  index that has positive element is not present so that's the first missing positive
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > 0) {
                return i;
            }
        }

        if (arr[0] > 0) {
            return arr.length;
        }
//  given array is increasing sequence then we have to return next sequence number
        return arr.length + 1;
    }
}
