package com.practise.problemsolving.array;

import org.junit.Assert;
import org.junit.Test;


/*

Name - Median of Two Sorted Arrays
Link - https://leetcode.com/problems/median-of-two-sorted-arrays/

 */

public class MedianOfTwoSortedArray {

    /*
    Time Complexity - o(mn)
    Space Complexity - o(1)
    Note - merge procedure
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


    /*
    Time Complexity - o(logn)
    Space Complexity - o(1)
    Note - thick of binary search if hint was logarithmic time complexity
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
        Assert.assertEquals(2.5, mergeSortApproach(arr1, arr2), 2.4);

        int arr3[] = {2};
        int arr4[] = {};

        Assert.assertEquals(2, mergeSortApproach(arr3, arr4), 2);
    }
}