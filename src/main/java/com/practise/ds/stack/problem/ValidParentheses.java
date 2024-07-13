package com.practise.ds.stack.problem;

import java.util.Stack;

/**
 * @author Ganeshan Nagarajan
 * @since 09/01/23
 */


    /*
    Name - Valid Parentheses
    Link - https://leetcode.com/problems/valid-parentheses/
    Condition - Given a string s containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid
    hint - use stack
    */

public class ValidParentheses {

    public static void main(String[] args) {
        String s = "{()}";
        System.out.println(firstApproach(s));
    }

    private static boolean firstApproach(String s) {
        Stack<Character> stack = new Stack<>();
        char c;
        for (int i = 0; i < s.length(); i++) {
            c = s.charAt(i);
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
                continue;
            }

            if (stack.isEmpty()) {
                return false;
            }

            switch (c) {
                case ')':
                    if (stack.pop() != '(') return false;
                    break;
                case '}':
                    if (stack.pop() != '{') return false;
                    break;
                case ']':
                    if (stack.pop() != '[') return false;
                    break;
                default:
                    return false;
            }
        }

//        corner case: if string have only one close parentheses char "}"
        if (!stack.isEmpty()) {
            return false;
        }

        return true;
    }

}
