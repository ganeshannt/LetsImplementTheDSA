package com.practise.problemsolving.array;

import java.util.HashMap;
import java.util.Map;

public class MissingNumber {

    //  Type of Solution - Better
    //  formula - nill
    //  Time Complexity - O(n)  -> used loop to put array into hashmap and checking key in hashmap so it take o(n)
    //  Space Complexity - o(n) -> hashmap used the n space

    public static void main(String[] args) {
        int arr[] = {1, 2, 4, 6, 7, 9, 8, 3, 10};
        MissingNumber missingNumber = new MissingNumber();
        // int miss = missingNumber.findMissingNumberImpl1(arr);
        int miss = missingNumber.findMissingNumberImpl2(arr);
        System.out.println(miss);
    }


    //  Type of Solution - best
    //  formula - n(n+1)/2  -> calculate sum of number from 0 to n
    //  Time Complexity - O(n)  -> used loop to calculate sum of given array so it take o(n)
    //  Space Complexity - o(1) -> no extra spaces used

    public int findMissingNumberImpl1(int arr[]) {
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

    public int findMissingNumberImpl2(int arr[]) {
        int n = arr.length;
        int actualsum = (n * (n + 1)) / 2;
        int arrSum = 0;
        for (int i : arr) {
            arrSum += i;
        }
        return (actualsum - arrSum);
    }
}