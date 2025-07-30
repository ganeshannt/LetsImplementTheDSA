package com.practise.ds.tuf.advance;

import java.util.*;

public class Hashing {

    public static void main(String[] args) {
        int[] a = {-5, -3, 0, -9, -6, 1, 5, -7, -1, 0, 3, 5, 9};

        // Create an instance of solution class
        Hashing solution = new Hashing();
        // Function call for finding longest consecutive sequence
        int ans = solution.subarraySum(a, 6);
        System.out.println("The longest consecutive sequence is " + ans);
    }

    public int longestConsecutive(int[] nums) {
        Arrays.sort(nums);

        int max = 1;
        int cur = 1;

        for (int i = 0; i < nums.length - 1; i++) {
            int diff = nums[i + 1] - nums[i];
            if (diff == 1) {
                cur++;
            } else if (nums[i + 1] != nums[i]) {
                cur = 1;
            }
            max = Math.max(max, cur);
        }
        return max;
    }

    public int longestConsecutiveBest(int[] nums) {
        int max = 1;
        Set<Integer> numSet = new HashSet<>();

        // remove duplicates
        for (int num : nums) {
            numSet.add(num);
        }

        for (int num : numSet) {
            // if previous is not there, then there is no sequence for it
            if (!numSet.contains(num - 1)) {

                int cur = 1;
                int currentNumber = num;

                while (numSet.contains(currentNumber + 1)) {
                    currentNumber++;
                    cur++;
                }
                max = Math.max(cur, max);
            }
        }

        return max;
    }

    public int longestSubarrayBest(int[] nums, int k) {
        int maxLength = 0;
        int prefixSum = 0;
        Map<Integer, Integer> sumMap = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            prefixSum += nums[i];
            if (prefixSum == k) {
                maxLength = Math.max(maxLength, i + 1);
            } else if (sumMap.containsKey(prefixSum - k)) {
                int index = sumMap.get(prefixSum - k);
                maxLength = Math.max(maxLength, i - index);
            }
            sumMap.putIfAbsent(prefixSum, i); // to handle zeros. when zero is present, the pre-sum value will not change.
        }
        return maxLength;
    }

    public int subarraySum(int[] nums, int k) {
        int subArrayCount = 0;
        int prefixSum = 0;
        Map<Integer, Integer> sumMap = new HashMap<>();

        sumMap.put(0, 1);

        for (int num : nums) {
            prefixSum += num;

            if (sumMap.containsKey(prefixSum - k)) {
                subArrayCount += sumMap.get(prefixSum - k);
            }
            sumMap.put(prefixSum, sumMap.getOrDefault(prefixSum, 0) + 1);
        }
        return subArrayCount;
    }

    public int longestSubarray(int[] nums, int k) {
        int max = 0;
        for (int i = 0; i < nums.length; i++) {
            int j = i;
            int sum = 0;
            while (j < nums.length) {
                sum += nums[j++];
                if (sum == k) break;
            }
            if (sum == k) {
                max = Math.max(j - i, max);
            }
        }
        return max;
    }

    //https://leetcode.com/problems/count-number-of-pairs-with-absolute-difference-k/description/
    public int countKDifference(int[] nums, int k) {

        int result = 0;

        Map<Integer, Integer> map = new HashMap<>();

        for (int num : nums) {
            if (map.containsKey(num - k)) {
                result += map.get(num - k);
            }
            if (map.containsKey(num + k)) {
                result += map.get(num + k);
            }
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        return result;
    }


    public int thirdMax(int[] nums) {

        int firstMax = Integer.MIN_VALUE;
        int secondMax = Integer.MIN_VALUE;
        int thirdMax = Integer.MIN_VALUE;

        for (int num : nums) {
            if (firstMax == num || secondMax == num || thirdMax == num) continue;
            if (firstMax < num) {
                thirdMax = secondMax;
                secondMax = firstMax;
                firstMax = num;
            } else if (secondMax < num) {
                thirdMax = secondMax;
                secondMax = num;
            } else if (thirdMax < num) {
                thirdMax = num;
            }
        }

        if (secondMax == Integer.MIN_VALUE || thirdMax == Integer.MIN_VALUE) return firstMax;

        return thirdMax;


    }

    /*
    * link: https://leetcode.com/problems/continuous-subarray-sum/description/
    * */

    public boolean checkSubarraySum(int[] nums, int k) {
        Map<Integer, Integer> remainderAndIndexMap = new HashMap<>();
        remainderAndIndexMap.put(0, -1);

        int sum = 0;
        int remainder;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            remainder = sum % k;

            if (remainderAndIndexMap.containsKey(remainder)) {
                if ((i - (remainderAndIndexMap.get(remainder))) > 1) {
                    return true;
                }
            } else {
                remainderAndIndexMap.put(remainder, i);
            }
        }
        return false;
    }

}
