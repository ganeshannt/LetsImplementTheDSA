package com.practise.playground;

public class InterviewTest {

    public double findMaxAverage(int[] nums, int k) {
        int sum = 0;
        for (int i = 0; i < k; i++) {
            sum += nums[i];
        }

        int maxSum = sum;

        for (int i = k; i < nums.length; i++) {
            sum -= nums[i - k];
            sum += nums[i];
            if (sum > maxSum) {
                maxSum = sum;
            }
        }
        return (double) maxSum / k;

    }


    public static int minSubArrayLen(int target, int[] nums) {
        int minLen = Integer.MAX_VALUE;
        int currentSum = 0;
        int start = 0;

        for (int end = 0; end < nums.length; end++) {
            currentSum += nums[end];
            while (currentSum >= target) {
                int windowSize = end - start + 1;
                minLen = Math.min(windowSize, minLen);
                currentSum -= nums[start++];
            }
        }

        return minLen == Integer.MAX_VALUE ? 0 : minLen;

    }


    public static void main(String[] args) {
        //minSubArrayLen(7, new int[]{2, 3, 1, 2, 4, 3});
        System.out.println(Runtime.getRuntime().availableProcessors());
    }


}
