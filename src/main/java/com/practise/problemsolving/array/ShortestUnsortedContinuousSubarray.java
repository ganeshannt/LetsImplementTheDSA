package com.practise.problemsolving.array;

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

    private void firstApproach(int[] arr) {
        int start = arr.length - 1;
        int end = 0;
        int j = 0;
        for (int i = 0; i < arr.length - 1; i++) {
            j = i + 1;
            if (arr[i] >= arr[j]) {
                if (start == arr.length - 1 && arr[i] > arr[j]) {
                    start = i;
                }
                if (start != arr.length - 1 && arr[start] == arr[j]) {
                    start = i;
                }
                end = j;
            }
        }
        start = (start == arr.length - 1) ? 0 : (end - start) + 1;
        System.out.println(start);
    }

    public int bestApproach(int[] nums) {
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        boolean flag = false;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < nums[i - 1])
                flag = true;
            if (flag)
                min = Math.min(min, nums[i]);
        }
        flag = false;
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
