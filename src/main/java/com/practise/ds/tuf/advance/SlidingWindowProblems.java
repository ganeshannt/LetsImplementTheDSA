package com.practise.ds.tuf.advance;

/**
 * @author Ganeshan Nagarajan
 * @since 06/08/25
 */


public class SlidingWindowProblems {

    /*
     * https://takeuforward.org/plus/dsa/sliding-window-and-2-pointer/constant-window/maximum-points-you-can-obtain-from-cards-
     * */
    public static int maxScore(int[] cardScore, int k) {
        int n = cardScore.length - 1;
        int maxScore = 0;
        int left = 0;
        int right = n;
        while (left < k) {
            maxScore += cardScore[left++];
        }

        if (left == cardScore.length) {
            return maxScore;
        } else {
            left = k - 1;
        }


        int globalMax = maxScore;

        while (right > n - k) {
            maxScore += cardScore[right--];
            maxScore -= cardScore[left--];
            globalMax = Math.max(maxScore, globalMax);
        }
        return globalMax;
    }

    public static void main(String[] args) {
        System.out.println(maxScore(new int[]{1, 2, 3, 4, 5, 6}, 3));
    }
}
