package com.practise.problemsolving.stackandqueue;

import com.practise.commons.Utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * @author Ganeshan Nagarajan
 * @since 09/01/23
 */


    /*
    Name - Next Greater Element I
    Link - https://leetcode.com/problems/next-greater-element-i/
    Condition - The next greater element of some element x in an array is the first greater element that is to the right of x in the same array.
    Hint - use stack to find next greatest element
    */

public class NextGreaterElementI {

    public static void main(String[] args) {
        int[] arr1 = {4, 1, 2};
        int[] arr2 = {1, 3, 4, 2};
        Utils.printArray(firstApproach(arr1, arr2));
    }

    public static int[] firstApproach(int[] arr1, int[] arr2) {
        Map<Integer, Integer> minMap = new HashMap<>();
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < arr2.length; i++) {
            if (stack.isEmpty()) {
                stack.push(arr2[i]);
            } else if (stack.peek() < arr2[i]) {
                while (!stack.isEmpty() && stack.peek() < arr2[i]) {
                    minMap.putIfAbsent(stack.pop(), arr2[i]);
                }
                stack.push(arr2[i]);
            } else {
                stack.push(arr2[i]);
            }
        }

        for (int i = 0; i < arr1.length; i++) {
            if (minMap.containsKey(arr1[i])) {
                arr1[i] = minMap.get(arr1[i]);
            } else {
                arr1[i] = -1;
            }
        }
        return arr1;
    }


    public static int[] optimizedApproach(int[] arr1, int[] arr2) {
        Map<Integer, Integer> minMap = new HashMap<>();
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < arr2.length; i++) {
            while (!stack.isEmpty() && stack.peek() < arr2[i]) {
                minMap.put(stack.pop(), arr2[i]);
            }
            stack.push(arr2[i]);
        }

        for (int i = 0; i < arr1.length; i++) {
            if (minMap.containsKey(arr1[i])) {
                arr1[i] = minMap.get(arr1[i]);
            } else {
                arr1[i] = -1;
            }
        }

        return arr1;
    }
}
