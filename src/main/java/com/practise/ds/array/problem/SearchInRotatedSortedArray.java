package com.practise.ds.array.problem;

/*

Name - Search in Rotated Sorted Array
Link - https://leetcode.com/problems/search-in-rotated-sorted-array/
condition - You must write an algorithm with O(log n) runtime complexity.

 */
public class SearchInRotatedSortedArray {

    public static void main(String[] args) {
        SearchInRotatedSortedArray element = new SearchInRotatedSortedArray();
//        int arr[] = {4, 5, 6, 7, 0, 1, 2};
//        element.bestApproach(arr, 6);
        int arr[] = {1, 0, 1, 1, 1};
        element.bestApproach(arr, 0);
    }

    /*
    Time Complexity - O(log n)
    Space Complexity - o(1)
    Note - Binary search and even rotated array also partially sorted
    */
    private void bestApproach(int[] arr, int target) {
        int start = 0;
        int end = arr.length - 1;
        int mid = 0;
        while (start <= end) {

            // to avoid duplicates - This technique we used in three sum problem
            while (start < end && arr[start] == arr[start + 1])
                ++start;
            while (start < end && arr[end] == arr[end - 1])
                --end;

            mid = (start + end) / 2;

            if (arr[mid] == target) {
                System.out.println(mid);
                break;
            } else if (arr[start] <= arr[mid]) {
                if (arr[start] <= target && arr[mid] >= target) {
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
            } else {
                if (arr[end] >= target && arr[mid] <= target) {
                    start = mid + 1;
                } else {
                    end = mid - 1;
                }
            }
        }
        System.out.println(-1);
    }
}
