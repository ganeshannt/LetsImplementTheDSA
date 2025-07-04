package com.practise.ds.string.problem;

import java.util.HashSet;
import java.util.Set;


/*
* https://leetcode.com/problems/longest-substring-without-repeating-characters/description/
* */
public class LongestSubstringWithoutRepeat {



    /*
    * TC: O(n^2)
    * SC: O(input size)
    * */
    public int lengthOfLongestSubstringBruteForce(String input) {

        int longestPairSoFar = 0;

        if (input == null || input.isEmpty()) return longestPairSoFar;

        Set<Character> characterSet = new HashSet<>(input.length());

        for (int i = 0; i < input.length(); i++) {

            for (int j = i; j < input.length(); j++) {
                char c = input.charAt(j);
                if (characterSet.contains(c)) {
                    longestPairSoFar = Math.max(longestPairSoFar, (j - i));
                    characterSet.clear();
                    break;
                } else {
                    characterSet.add(c);
                }
            }

            if (!characterSet.isEmpty() && longestPairSoFar < (input.length() - i)) {
                longestPairSoFar = (input.length() - i);
                break;
            }
        }
        return longestPairSoFar;
    }




    public static void main(String[] args) {

    }
}
