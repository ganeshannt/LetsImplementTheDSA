package com.practise.playground.interview;


import java.util.HashSet;
import java.util.Set;

/*
*
* Write a program to find Length of the smallest sub-string consisting of maximum distinct characters

Input : "AABBBCBB"
* MAX : ABC
* Substring: ABBBC

Output : 5

Input : "AABBBCBBAC"

* MAX: ABC
* SUBSTRING:  BAC
*
Output : 3
*
*
* */
public class HyreTest {


    public static void main(String[] args) {
        String s = "AABBBCBB";

        int result = 0;

        Set<Character> uniqueStrSet = new HashSet<>();

        for (int i = 0; i < s.length(); i++) {
            uniqueStrSet.add(s.charAt(i));
        }

        int start = 0;
        int count = 0;


//        Input : "AABBBCBB"

        for (int i = 0; i < s.length(); i++) {
            if (uniqueStrSet.isEmpty()) {

            }
            if (uniqueStrSet.contains(s.charAt(i))) {
                uniqueStrSet.remove(s.charAt(i));
            }
        }

    }
}
