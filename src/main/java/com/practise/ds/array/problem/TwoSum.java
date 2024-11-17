package com.practise.ds.array.problem;

import com.practise.commons.Utils;

import java.util.HashMap;

/**
 * @author Ganeshan Nagarajan
 * @since 17/11/24
 */

public class TwoSum {
    public static void main(String[] args) {
//        var twoSumIndex = twoSumPartOne(new int[]{2, 3, 4, 5, 6}, 9);
        var twoSumIndex = twoSumPartTwo(new int[]{2, 3, 4, 5, 6}, 9);
        Utils.printArray(twoSumIndex);
    }


    /*
    Time Complexity - o(n)
    Space Complexity - o(1)
    Note - two pointer technique used to avoid hashmap space here because array is sorted
    Link - https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/description/
    */
    private static int[] twoSumPartTwo(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int total = nums[left] + nums[right];
            if (total == target) {
                return new int[]{left + 1, right + 1};
            }
            if (total < target) left++;
            else right--;
        }
        return new int[0];
    }


    /*
    Time Complexity - o(n)
    Space Complexity - o(n)
    Note - use hashmap
    Link - https://leetcode.com/problems/two-sum/description/
     */
    private static int[] twoSumPartOne(int[] nums, int target) {
        HashMap<Integer, Integer> numMap = new HashMap<>(nums.length);
        for (int i = 0; i < nums.length; i++) {
            int expected = target - nums[i];
            if (numMap.get(expected) != null && numMap.get(expected) != i) {
                return new int[]{i, numMap.get(expected)};
            }
            numMap.put(nums[i], i);
        }
        return new int[0];
    }
}
