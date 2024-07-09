package com.practise.problemsolving.string;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/*
Name - Group Anagrams
Link - https://leetcode.com/problems/group-anagrams/
*/
public class RemoveAllDuplicates {

    private String bruteForceApproach(String s) {
        char[] chararr = s.toCharArray();
        int letterarr[] = new int[26];
        int letter = 0;
        char[] resultarr = new char[26];
        int count = 0;
        for (int i = 0; i < chararr.length; i++) {
            letter = chararr[i] - 97;
            System.out.println(letter);
            if (letterarr[letter] == 0) {
                letterarr[letter] = 1;
                resultarr[count++] = chararr[i];
            }
        }
        String result = new String(resultarr);
        return result.trim();
    }

    private String removeAllAdjecentDuplicatesBruteForce(String s) {
        if (s.length() == 1)
            return s;
        char[] chararr = s.toCharArray();
        int j = 0;
        int i = 0;
        while (i < chararr.length - 1) {
            j = i + 1;
            while (chararr[j] == '0' && j < chararr.length - 1) {
                j++;
            }
            if (chararr[i] == chararr[j] && chararr[i] != '0') {
                chararr[i] = '0';
                chararr[j] = '0';
                i = 0;
            } else {
                i++;
            }
        }

        StringBuilder builder = new StringBuilder();

        for (int k = 0; k < chararr.length; k++) {
            if (chararr[k] != '0') {
                builder.append(chararr[k]);
            }
        }

        return builder.toString().trim();
    }

    private String removeAllAdjecentDuplicatesUsingStack(String s) {
        if (s.length() == 1)
            return s;
        Stack<Character> charStack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (!charStack.isEmpty()) {
                if (c == charStack.peek()) {
                    charStack.pop();
                } else {
                    charStack.push(c);
                }
            } else {
                charStack.push(c);
            }
        }
        StringBuilder builder = new StringBuilder();
        if (!charStack.isEmpty()) {
            while (0 < charStack.size()) {
                builder.append(charStack.pop());
            }
        }
        return builder.reverse().toString().trim();
    }

    // tc - o(n)
    // sc - o(n)
    private int firstUniqueCharacterBruteForceApprach(String s) {
        char[] chararr = s.toCharArray();
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < chararr.length; i++) {
            if (map.containsKey(chararr[i])) {
                chararr[map.get(chararr[i])] = '0';
                chararr[i] = '0';
            } else {
                map.put(chararr[i], i);
            }
        }
        for (int i = 0; i < chararr.length; i++) {
            if (chararr[i] != '0') {
                return i;
            }
        }
        return -1;
    }

    // Time complexity : O(N) since we go through the string of length
    // N two times.
    // Space complexity : O(1) because English alphabet contains 26
    // letters.
    private int firstUniqueCharacterBestApproach(String s) {
        HashMap<Character, Integer> count = new HashMap<Character, Integer>();
        int n = s.length();
        // build hash map : character and how often it appears
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            count.put(c, count.getOrDefault(c, 0) + 1);
        }

        // find the index
        for (int i = 0; i < n; i++) {
            if (count.get(s.charAt(i)) == 1)
                return i;
        }
        return -1;
    }

    @Test
    public void testRemoveAllDuplicates() {
        RemoveAllDuplicates allDuplicates = new RemoveAllDuplicates();
        // String input = "ganeshannagarajan";
        // String output = "ganeshrj";
        // Assertions.assertEquals(output, allDuplicates.bruteForceApproach(input));
        // String input2 = "azxxxzy";
        // String output2 = "azxzy";
        // Assertions.assertEquals(output2,
        // allDuplicates.removeAllAdjecentDuplicatesUsingStack(input2));
        String input3 = "loveleetcode";
        Assertions.assertEquals(2, allDuplicates.firstUniqueCharacterBruteForceApprach(input3));
    }
}
