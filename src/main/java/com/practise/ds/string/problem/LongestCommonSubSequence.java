package com.practise.ds.string.problem;


public class LongestCommonSubSequence {

    /*
     * TC and SC: o(m*n)
     * Top-down approach
     * https://leetcode.com/problems/longest-common-subsequence
     * */
    public int longestCommonSubsequence(String str1, String str2) {

        int m = str1.length();
        int n = str2.length();

        int[][] dp = new int[m + 1][n + 1];

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[m][n];
    }

}
