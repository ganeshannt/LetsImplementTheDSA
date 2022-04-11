package com.practise.problemsolving.array;

import java.util.HashMap;
import java.util.Map;

/*

Name - Missing Number
Link - https://leetcode.com/problems/missing-number/

 */

public class MissingNumber {

    public static void main(String[] args) {
        int arr[] = {1, 2, 4, 6, 7, 9, 8, 3, 10};
        MissingNumber missingNumber = new MissingNumber();
        int miss = missingNumber.bestApproach(arr);
        System.out.println(miss);
    }


    /*
    Time Complexity - O(n)  -> used loop to put array into hashmap and checking key in hashmap, so it takes o(n)
    Space Complexity - o(n) -> hashmap used the n space
    Note - use hashmap
    */
    public int firstApproach(int arr[]) {
        Map<Integer, Integer> arrmap = new HashMap<Integer, Integer>();
        for (int i : arr) {
            arrmap.put(i, 1);
        }
        for (int i = 1; i <= arr.length; i++) {
            if (!arrmap.containsKey(i)) {
                return i;
            }
        }
        return -1;
    }


    /*
    Time Complexity - O(n)  -> used loop to calculate sum of given array, so it takes o(n)
    Space Complexity - o(1) -> no extra spaces used
    Note - n(n+1)/2  -> calculate sum of number from 0 to n
   */
    public int bestApproach(int arr[]) {
        int n = arr.length;
        int actualsum = (n * (n + 1)) / 2;
        int arrSum = 0;
        for (int i : arr) {
            arrSum += i;
        }
        return (actualsum - arrSum);
    }
}