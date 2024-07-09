package com.practise.problemsolving.searchingandsorting;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/*
Name - Kth Largest Element in an Array
Link - https://leetcode.com/problems/kth-largest-element-in-an-array/
*/
public class KthLargestElement {

    /*
    Time Complexity - O(nlogn)
    Space Complexity - o(1)
    Note - sort the array and return n-k index value
    */
    public static int bruteForceApproach1(int arr[], int k) {
        Arrays.sort(arr);
        return arr[arr.length - k];
    }

    /*
    Time Complexity - O(klogn)
    Space Complexity - o(n-k)
    Note - Heap data structure. efficient if k is large.
    */
    public static int bruteForceApproach2(int arr[], int k) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
        for (int i : arr) {
            maxHeap.add(i);
        }
        for (int i = 0; i < k - 1; i++) {
            maxHeap.remove();
        }
        return maxHeap.peek();
    }

    /*
    Time Complexity - best case - O(n) worst case - O(n^2)
    Space Complexity - o(1)
    Note - Modified quick sort
    */
    public static int bestApproach(int arr[], int k) {
        return 0;
    }

    @Test
    public void testFindPeakElement() {
        int arr[] = {3, 2, 1, 5, 6, 4};
        Assertions.assertEquals(5, KthLargestElement.bruteForceApproach2(arr, 2));
    }
}