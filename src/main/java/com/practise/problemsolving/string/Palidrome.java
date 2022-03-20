package com.practise.problemsolving.string;

import org.junit.Test;

import java.util.Stack;

import static org.junit.jupiter.api.Assertions.assertEquals;

;

public class Palidrome {

    private boolean validPalindromeBruteForceApproach(String s) {
        Stack<Character> stack = new Stack<>();
        char charrarr[] = new char[s.length()];
        int count = 0;
        for (char c : s.toLowerCase().toCharArray()) {
            if (c > 96 && c < 123) {
                charrarr[count++] = c;
            }
        }

        if (charrarr.length - 1 < 2)
            return true;

        charrarr = new String(charrarr).trim().toCharArray();

        for (int i = 0; i < charrarr.length; i++) {
            char character = charrarr[i];
            if (stack.isEmpty()) {
                stack.push(character);
            } else {
                if (stack.peek() == character) {
                    stack.pop();
                } else {
                    if (i == charrarr.length / 2 && stack.peek() != character) {
                        continue;
                    }
                    stack.push(character);
                }
            }
        }
        if (stack.size() == 0) {
            return true;
        }
        return false;
    }

    private boolean validPalindromeBestApproach(String s) {
        int i = 0;
        int j = s.length() - 1;
        while (i < j) {
            while (i < j && !Character.isLetterOrDigit(s.charAt(i))) {
                i++;
            }
            while (i < j && !Character.isLetterOrDigit(s.charAt(j))) {
                j--;
            }

            if (Character.toLowerCase(s.charAt(i++)) != Character.toLowerCase(s.charAt(j--))) {
                return false;
            }
        }
        return true;
    }

    private boolean validPalindrome2BruteForceApproach(String s) {
        int i = 0;
        int j = s.length() - 1;
        boolean flag = false;
        while (i < j) {
            if (s.charAt(i) != s.charAt(j)) {
                if (s.charAt(i + 1) == s.charAt(j) && !flag) {
                    i++;
                    flag = true;
                    continue;
                }
                if (s.charAt(i) == s.charAt(j - 1) && !flag) {
                    j--;
                    flag = true;
                    continue;
                }
                return false;
            }
            i++;
            j--;
        }
        return true;
    }

    @Test
    public void testPalidrome() {
        Palidrome palidrome = new Palidrome();
        String input = "A man, a plan, a canal: Panama";
        // assertEquals(true, palidrome.validPalindromeBestApproach(input));
        input = "aguokepatgbnvfqmgmlcupuufxoohdfpgjdmysgvhmvffcnqxjjxqncffvmhvgsymdjgpfdhooxfuupuculmgmqfvnbgtapekouga";
        assertEquals(true, palidrome.validPalindrome2BruteForceApproach(input));
    }
}
