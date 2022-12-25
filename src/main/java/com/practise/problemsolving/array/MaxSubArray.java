package com.practise.problemsolving.array;

/**
 * @author Ganeshan Nagarajan
 * @since 22/12/22
 */


/*

Name - Maximum SubArray
Link - https://leetcode.com/problems/maximum-subarray/

 */

public class MaxSubArray {

    public static void main(String[] args) {
        MajorityElement element = new MajorityElement();
        int arr[] = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println(bestApproach(arr));
    }


    /*
    Time Complexity - o(n)
    Space Complexity - o(1)
    Note - kadane's algorithm
    */

    private static int bestApproach(int[] arr) {
        if (arr.length == 0) {
            return 0;
        }

        int curSum = arr[0], maxSum = arr[0];

        for (int i = 1; i < arr.length; i++) {
            if (curSum > 0) {
                curSum += arr[i];
            } else {
                curSum = arr[i];
            }
            maxSum = Math.max(curSum, maxSum);
        }
        return maxSum;
    }


}
