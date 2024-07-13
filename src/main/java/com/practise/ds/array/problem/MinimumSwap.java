package com.practise.ds.array.problem;

/**
 * @author Ganeshan Nagarajan
 * @since 25/12/22
 */


/*

Name - Minimum swaps required to bring all elements less than or equal to k together
Link - https://practice.geeksforgeeks.org/problems/minimum-swaps-required-to-bring-all-elements-less-than-or-equal-to-k-together4847/1?utm_source=gfg&utm_medium=article&utm_campaign=bottom_sticky_on_article

 */

public class MinimumSwap {

    public static void main(String[] args) {
        int arr[] = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        int k = 4;
        System.out.println(bestApproach(arr, k));
    }


    /*
    Time Complexity - o(n)
    Space Complexity - o(1)
    Note - Two pointer + sliding window
    */
    private static int bestApproach(int[] arr, int k) {
        int less_than_eq_k = 0;
        int greater_than_k = 0;

        for (int n : arr) {
            if (n <= k) {
                less_than_eq_k++;
            }
        }

        for (int i = 0; i < less_than_eq_k; i++) {
            if (arr[i] > k) {
                greater_than_k++;
            }
        }

        int ans = greater_than_k;

        for (int i = 0, j = less_than_eq_k; j < arr.length; j++, i++) {
            if (arr[i] > k) {
                greater_than_k--;
            }
            if (arr[j] > k) {
                greater_than_k++;
            }
            ans = Math.min(ans, greater_than_k);
        }
        return ans;
    }
}
