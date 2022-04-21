package com.practise.problemsolving.string;

import org.junit.Assert;
import org.junit.Test;

/*
Name - Reverse Words in a String
Link - https://leetcode.com/problems/reverse-words-in-a-string
*/
public class ReverseWord {

    private String bruteForceApproach(String s) {
        String[] arr = s.split(" ");
        StringBuilder builder = new StringBuilder();
        for (int i = arr.length - 1; i >= 0; i--) {
            if (!arr[i].equals("")) {
                builder.append(arr[i]);
                builder.append(" ");
            }
        }
        return builder.toString().trim();
    }

    @Test
    public void testReverseWord() {
        ReverseWord reverseWord = new ReverseWord();
        String input = "a good   example";
        String output = "example good a";
        Assert.assertEquals(output, reverseWord.bruteForceApproach(input));
    }
}
