package com.practise.problemsolving.searchingandsorting;


    /*
    Name - Find Peak Element
    Link - https://leetcode.com/problems/find-peak-element/
    Condition - You must write an algorithm that runs in O(log n) time.
    */

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class FindPeakElement {

    /*
    Time Complexity -  O(log n)
    Space Complexity - o(1)
    Note - customized binary search approach
     */
    private static int bestApproach(int[] arr) {
        int left = 0;
        int right = arr.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] < arr[mid + 1]) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }

    @Test
    public void testFindPeakElement() {
        int[] arr = new int[]{1, 2, 1, 3, 5, 6, 4};
        Assertions.assertEquals(5, FindPeakElement.bestApproach(arr));
    }

}
