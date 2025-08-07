package com.practise.ds.tuf.advance;

/**
 * @author Ganeshan Nagarajan
 * @since 06/08/25
 * <p>
 * Dynamic Programming on Strings — Common Problems
 * https://takeuforward.org/plus/dsa/dynamic-programming/dp-on-strings/longest-common-subsequence
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


    /**
     * Problem: Minimum Insertion Steps to Make a String Palindrome
     * Leetcode: https://leetcode.com/problems/minimum-insertion-steps-to-make-a-string-palindrome/
     * <p>
     * Approach:
     * - To make a string palindrome with minimum insertions, we can:
     * -> Identify the Longest Palindromic Subsequence (LPS) in the string.
     * -> Insert characters to mirror the remaining characters (those not in LPS).
     * - Formula: minInsertions = s.length() - LPS(s)
     * - To compute LPS, we calculate the Longest Common Subsequence between the string and its reverse.
     * <p>
     * Time Complexity: O(n^2), where n is the length of the input string
     * Space Complexity: O(n^2) for the DP table
     */
    public int minInsertion(String s) {
        return s.length() - longestPalindromicSubsequence(s);
    }

//    public int minOperations(String str1, String str2) {
//        int lcs = longestCommonSubsequence(str1, str2);
//        int str1Diff = str1.length() - lcs;
//        int strDiff = str2.length() - str1.length();
//        return (str1Diff * 2) + strDiff;
//    }


    /**
     * Problem: Minimum operations (insertions + deletions) to make str1 equal to str2
     * Related Leetcode: https://leetcode.com/problems/edit-distance/ (if substitution is also allowed)
     * OR
     * Related Custom Variant: If only insertion and deletion are allowed
     * <p>
     * Approach:
     * - Find the Longest Common Subsequence (LCS) of str1 and str2.
     * - Keep the LCS part and:
     * -> Delete the remaining characters from str1 (str1.length - LCS)
     * -> Insert the missing characters from str2 (str2.length - LCS)
     * - Total operations = deletions + insertions
     * <p>
     * Time Complexity: O(m * n)
     * Space Complexity: O(m * n)
     */
    public int minOperations(String str1, String str2) {
        int lcs = longestCommonSubsequence(str1, str2);
        int deletions = str1.length() - lcs;
        int insertions = str2.length() - lcs;
        return deletions + insertions;
    }


    /**
     * Problem: Shortest Common Supersequence
     * Leetcode: https://leetcode.com/problems/shortest-common-supersequence/
     * <p>
     * Approach:
     * - First find LCS to avoid duplicating common characters
     * - Then construct supersequence by including all characters from both strings while maintaining order
     * <p>
     * Time Complexity: O(m * n)
     * Space Complexity: O(m * n) + O(m + n) (for result construction)
     */
    public String shortestCommonSupersequence(String str1, String str2) {
        int m = str1.length(), n = str2.length();
        int[][] dp = new int[m + 1][n + 1];

        // Build LCS DP table
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (str1.charAt(i - 1) == str2.charAt(j - 1))
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                else
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
            }
        }

        // Build Shortest Common Supersequence from LCS DP table
        int i = m;
        int j = n;
        StringBuilder sb = new StringBuilder();

        while (i > 0 && j > 0) {
            if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                sb.append(str1.charAt(i - 1));
                i--;
                j--;
            } else if (dp[i - 1][j] > dp[i][j - 1]) {
                sb.append(str1.charAt(i - 1));
                i--;
            } else {
                sb.append(str2.charAt(j - 1));
                j--;
            }
        }

        while (i > 0) sb.append(str1.charAt(--i));
        while (j > 0) sb.append(str2.charAt(--j));

        return sb.reverse().toString(); // Reverse to get correct order
    }

    public static void main(String[] args) {
        // Sample usage can be added here for testing
    }
}
