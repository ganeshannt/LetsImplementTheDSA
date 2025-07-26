package com.practise.ds.array.problem;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Problem: Median of Two Sorted Arrays
 * 
 * Description: Given two sorted arrays nums1 and nums2 of size m and n respectively,
 * find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).
 * 
 * LeetCode: https://leetcode.com/problems/median-of-two-sorted-arrays/
 * 
 * Approach: Two approaches are implemented:
 * 1. Merge sort approach - Merge the two arrays and find the median
 * 2. Binary search approach - Use binary search to find the median in logarithmic time
 * 
 * Time Complexity: O(m+n) for merge sort approach, O(log(min(m,n))) for binary search approach
 * Space Complexity: O(m+n) for merge sort approach, O(1) for binary search approach
 * 
 * Hint: For the optimal solution, think about binary search on the smaller array
 */
public class MedianOfTwoSortedArray {

    /**
     * Merge sort approach to find the median of two sorted arrays.
     * Merges the two arrays and then finds the median of the merged array.
     * 
     * Time Complexity: O(m+n) - One pass through both arrays
     * Space Complexity: O(m+n) - Uses an additional array to store the merged result
     * 
     * @param arr1 First sorted array
     * @param arr2 Second sorted array
     * @return The median of the two sorted arrays
     */
    private double mergeSortApproach(int[] arr1, int[] arr2) {
        int i = 0;
        int j = 0;
        int count = 0;
        int result[] = new int[(arr1.length) + (arr2.length)];
        while (i < arr1.length && j < arr2.length) {
            if (arr1[i] < arr2[j]) {
                result[count++] = arr1[i++];
            } else {
                result[count++] = arr2[j++];
            }
        }

        while (i < arr1.length) {
            result[count++] = arr1[i++];
        }
        while (j < arr2.length) {
            result[count++] = arr2[j++];
        }

        if (result.length % 2 == 0) {
            Double median = (double) (result[result.length / 2] + result[(result.length / 2) - 1]);
            return median / 2;
        }
        return result[result.length / 2];
    }


    /**
     * Binary search approach to find the median of two sorted arrays.
     * This is the optimal approach with logarithmic time complexity.
     * 
     * Time Complexity: O(log(min(m,n))) - Binary search on the smaller array
     * Space Complexity: O(1) - Uses constant extra space
     * 
     * Note: This implementation is incomplete and serves as a template for the binary search approach.
     * 
     * @param arr1 First sorted array
     * @param arr2 Second sorted array
     * @return The median of the two sorted arrays
     */
    private double binarySearchBasedApproach(int arr1[], int arr2[]) {
        int j = arr2.length / 2;
        int i = ((arr1.length + arr2.length) / 2) - j;
        while (i >= 0 && j >= 0) {
            if (arr1[i] < arr2[j + 1]) {

            } else if (arr1[i + 1] > arr2[j]) {

            } else {

            }
        }
        return 0;
    }

    @Test
    public void testMedianOfTwoSortedArray() {
        int arr1[] = {1, 2};
        int arr2[] = {3, 4};
        Assertions.assertEquals(2.5, mergeSortApproach(arr1, arr2), 2.4);

        int arr3[] = {2};
        int arr4[] = {};

        Assertions.assertEquals(2, mergeSortApproach(arr3, arr4), 2);
    }
}