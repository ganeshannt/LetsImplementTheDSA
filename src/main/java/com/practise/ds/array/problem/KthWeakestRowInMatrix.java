package com.practise.ds.array.problem;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.PriorityQueue;

;

/*

Name - Increasing Triplet Subsequence
Link - https://leetcode.com/problems/increasing-triplet-subsequence/

 */

public class KthWeakestRowInMatrix {

    private void priorityQueueBasedApproach(int[][] arr, int k) {
        int row = arr.length;
        int col = arr[0].length;
        int count = 0;
        PriorityQueue<int[]> priorityQueue = new PriorityQueue<>((a, b) -> a[0] != b[0] ? b[0] - a[0] : b[1] - a[1]);
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (arr[i][j] == 1)
                    count++;
                else
                    break;
            }
            priorityQueue.offer(new int[]{count, i});
            count = 0;
        }

        while (priorityQueue.size() > k) {
            priorityQueue.poll();
        }

        int result[] = new int[k];

        while (k > 0) {
            result[--k] = priorityQueue.poll()[1];
        }

        for (int i : result) {
            System.out.println(i);
        }
    }

    private int[] arrayBasedApproach(int[][] arr, int k) {
        int row = arr.length;
        int col = arr[0].length;
        int count = 0;
        int arr2[] = new int[row];

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (arr[i][j] == 1)
                    count++;
                else
                    break;
            }
            arr2[i] = (count * 1000) + i;
            count = 0;
        }
        Arrays.sort(arr2);

        int result[] = new int[k];
        while (k > 0) {
            k--;
            result[k] = arr2[k] % 1000;
        }

        for (int i : result) {
            System.out.println(i);
        }
        return result;
    }

    // public static void main(String[] args) {
    // KthWeakestRowInMatrix matrix = new KthWeakestRowInMatrix();
    // int[][] arr = { { 1, 1, 0, 0, 0 }, { 1, 1, 1, 1, 0 }, { 1, 0, 0, 0, 0 }, { 1,
    // 1, 0, 0, 0 }, { 1, 1, 1, 1, 1 } };
    // matrix.priorityQueueBasedApproach(arr, 3);
    // }

    @Test
    public void testArrayBasedApproach() {
        int[][] arr = {{1, 1, 0, 0, 0}, {1, 1, 1, 1, 0}, {1, 0, 0, 0, 0}, {1, 1, 0, 0, 0}, {1, 1, 1, 1, 1}};
        Assertions.assertArrayEquals(new int[]{2, 0, 3}, arrayBasedApproach(arr, 3));
    }
}
