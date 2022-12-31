package com.practise.problemsolving.patterns;

/**
 * @author Ganeshan Nagarajan
 * @since 25/12/22
 */


/*
Name - 459. Repeated Substring Pattern
Link - https://leetcode.com/problems/repeated-substring-pattern/

Input: s = "abcabcabcabc"
Output: true
Explanation: It is the substring "abc" four times or the substring "abcabc" twice.

 */


public class RepeatedSubstringPattern {

    public static void main(String[] args) {

        System.out.println(initialApproach("abcabcabcabc"));
    }

    private static boolean initialApproach(String s) {
        String s2 = s + s;
        int index = s2.indexOf(s, 1);
        return index < s.length();
    }
}
