package com.practise.problemsolving.stackandqueue;

import java.util.ArrayList;
import java.util.Stack;

/**
 * @author Ganeshan Nagarajan
 * @since 10/01/23
 */



    /*
    Name - Online Stock Span
    Link - https://leetcode.com/problems/online-stock-span/
    Hint - use stack to find next greatest element
    */

public class StockSpan {

    Stack<Integer> stack;
    ArrayList<Integer> arrayList;

    int index;

    public static void main(String[] args) {

        StockSpan stockSpanner = new StockSpan();

//        int[][] arr = {{}, {100}, {80}, {60}, {70}, {60}, {75}, {85}};
        int[][] arr = {{}, {100}, {100}};

        for (int[] a : arr) {
            for (int n : a) {
                System.out.println(stockSpanner.next(n));
            }
        }
    }

    public StockSpan() {
        this.stack = new Stack<>();
        arrayList = new ArrayList<>();
        index = 0;
    }

    public int next(int price) {
        arrayList.add(index, price);
        int output = 1;
        if (stack.isEmpty()) {
            stack.push(index);
        } else if (arrayList.get(stack.peek()) > price) {
            output = index - stack.peek();
            stack.push(index);
        } else if (arrayList.get(stack.peek()) <= price) {
            while (!stack.isEmpty() && arrayList.get(stack.peek()) <= price) {
                stack.pop();
            }
            if (!stack.isEmpty()) {
                output = index - stack.peek();
            } else {
                output = index + 1;
            }
            stack.push(index);
        }
        index++;
        return output;
    }


    /*
    Time Complexity -  O(n)
    Space Complexity - o(n)
    Note - think about stack use case
     */
    public int optimizedVersionOfNext(int price) {
        arrayList.add(index, price);
        int output;

        while (!stack.isEmpty() && arrayList.get(stack.peek()) <= price) {
            stack.pop();
        }

        if (!stack.isEmpty()) {
            output = index - stack.peek();
        } else {
            output = index + 1;
        }
        stack.push(index++);
        return output;
    }
}
