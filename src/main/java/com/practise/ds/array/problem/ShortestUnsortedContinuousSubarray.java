package com.practise.ds.array.problem;

import java.util.Arrays;
import java.util.Stack;

/*

Name - Shortest Unsorted Continuous Subarray
Link - https://leetcode.com/problems/shortest-unsorted-continuous-subarray/
condition - Can you solve it in O(n) time complexity?

 */
public class ShortestUnsortedContinuousSubarray {

    public static void main(String[] args) {
        ShortestUnsortedContinuousSubarray element = new ShortestUnsortedContinuousSubarray();
        int arr[] = {2, 6, 4, 8, 10, 9, 15};

        // { 1, 3, 2, 2, 2 };
        // [1,2,3,3,3]
        // int index[] = { 18,6,6,6,1,-1 };
        // m = element.hashTableApproach(arr);
        element.bestApproach(arr);
        // element.moveahead(arr, 1, 4);
    }


    /*
    Time Complexity - O(nlogn) Sorting takes O(nlogn) time
    Space Complexity - o(n) We are making copy of original array.
    Note - Make a copy. sort copied array and compare the values
    */
    private void bruteForceApproach(int[] arr) {
        int[] arr2 = arr.clone();
        Arrays.sort(arr2);
        int start = arr.length;
        int end = 0;
        int sub_count = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != arr2[i]) {
                start = Math.min(start, i);
                end = Math.max(end, i);
            }
        }
        System.out.println(end - start >= 0 ? end - start + 1 : 0);
    }


    /*
    Time Complexity - O(n) Stack of size n is filled
    Space Complexity - o(n) Stack size grows up-to n.
    Note - make use of stack properties
    */
    private void secondApproach(int[] arr) {
        int start = arr.length;
        int end = 0;
        int count = 0;
        Stack<Integer> stack = new Stack<>();
//      find starting index of unsorted sub array
        for (int i = 0; i < arr.length; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] > arr[i])
                start = Math.min(stack.pop(), i);
            stack.push(i);
        }

        stack.clear();

//      find ending index of unsorted sub array
        for (int i = arr.length - 1; i >= 0; i--) {
            while (!stack.isEmpty() && arr[stack.peek()] < arr[i])
                end = Math.max(stack.pop(), i);
            stack.push(i);
        }
//      calculate the difference
        count = (end - start >= 0 ? end - start + 1 : 0);
        System.out.println(count);
    }


    /*
    Time Complexity - O(n)
    Space Complexity - o(1)
    Note - make use of stack properties
    */
    public int bestApproach(int[] nums) {
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        boolean flag = false;
//      find starting index of unsorted sub array
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < nums[i - 1])
                flag = true;
            if (flag)
                min = Math.min(min, nums[i]);
        }

        flag = false;
//      find ending index of unsorted sub array
        for (int i = nums.length - 2; i >= 0; i--) {
            if (nums[i] > nums[i + 1])
                flag = true;
            if (flag)
                max = Math.max(max, nums[i]);
        }

        int l, r;

        for (l = 0; l < nums.length; l++) {
            if (min < nums[l])
                break;
        }
        for (r = nums.length - 1; r >= 0; r--) {
            if (max > nums[r])
                break;
        }
        return r - l < 0 ? 0 : r - l + 1;
    }
}
