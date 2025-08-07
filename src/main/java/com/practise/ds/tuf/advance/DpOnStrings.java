package com.practise.ds.tuf.advance;

/**
 * @author Ganeshan Nagarajan
 * @since 06/08/25
 * <p>
 * Dynamic Programming on Strings — Common Problems
 */
public class DpOnStrings {

    /**
     * Problem: Longest Common Subsequence
     * Leetcode: https://leetcode.com/problems/longest-common-subsequence/
     * <p>
     * Approach:
     * - Use a 2D DP table where dp[i][j] represents the length of LCS
     * between first i characters of str1 and first j characters of str2.
     * - If characters match: 1 + dp[i-1][j-1]
     * - Else: max(dp[i-1][j], dp[i][j-1])
     * <p>
     * Time Complexity: O(m * n)
     * Space Complexity: O(m * n)
     */
    public int longestCommonSubsequence(String str1, String str2) {
        int m = str1.length();
        int n = str2.length();

        int[][] dp = new int[m + 1][n + 1]; // DP table with base case 0s

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    // If characters match, include this character in LCS
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {
                    // Take the maximum excluding one character at a time
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[m][n]; // Final result in bottom-right cell
    }

    /**
     * Problem: Longest Common Substring
     * Not directly on Leetcode but common in interviews
     * <p>
     * Approach:
     * - Use a 2D DP table where dp[i][j] represents the length of the
     * longest common substring ending at str1[i-1] and str2[j-1].
     * - Only if characters match: dp[i][j] = 1 + dp[i-1][j-1]
     * - Track maximum length in a separate variable.
     * <p>
     * Time Complexity: O(m * n)
     * Space Complexity: O(m * n)
     */
    public int longestCommonSubstring(String str1, String str2) {
        int m = str1.length();
        int n = str2.length();

        int maxLcs = 0;
        int[][] dp = new int[m + 1][n + 1]; // Base case: 0s

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    // Increase substring length if characters match
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                    maxLcs = Math.max(maxLcs, dp[i][j]);
                }
                // No need for else, as dp[i][j] defaults to 0
            }
        }
        return maxLcs;
    }

    /**
     * Problem: Longest Palindromic Subsequence
     * Leetcode: https://leetcode.com/problems/longest-palindromic-subsequence/
     * <p>
     * Approach:
     * - Reverse the input string and compute LCS between the original and reversed strings.
     * - The LCS will be the longest palindromic subsequence.
     * <p>
     * Time Complexity: O(n * n)
     * Space Complexity: O(n * n)
     */
    public int longestPalindromicSubsequence(String s1) {
        String s2 = new StringBuilder(s1).reverse().toString();
        int m = s1.length();
        int n = s2.length();

        int[][] dp = new int[m + 1][n + 1]; // Base case: 0s

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    // Matching characters, count toward palindrome
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {
                    // Otherwise take max excluding one character
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[m][n];
    }

    public static void main(String[] args) {
        // Sample usage can be added here for testing
    }
}
