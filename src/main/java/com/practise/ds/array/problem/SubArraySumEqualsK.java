package com.practise.ds.array.problem;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Ganeshan Nagarajan
 * @since 04/01/23
 */


/*

Name - Sub Array Sum Equals K
Link - https://leetcode.com/problems/subarray-sum-equals-k/

 */

public class SubArraySumEqualsK {


    public static void main(String[] args) {
        int arr[] = {-1, -1, 1};
        System.out.println(bestApproach(arr, 0));
    }

    /*
    Time Complexity - O(n)
    Space Complexity - o(n)
    Note - prefix sum technique
    */

    public static int bestApproach(int[] arr, int k) {
        int count = 0;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        int sum = 0;
        for (int num : arr) {
            sum += num;
            if (map.containsKey(sum - k)) {
                count += map.get(sum - k);
            }
            if (map.containsKey(sum)) {
                map.put(sum, map.get(sum) + 1);
            } else {
                map.put(sum, 1);
            }
        }
        return count;
    }
}
