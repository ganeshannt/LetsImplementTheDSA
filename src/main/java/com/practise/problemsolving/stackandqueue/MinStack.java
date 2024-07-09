package com.practise.problemsolving.stackandqueue;

import java.util.Stack;

/**
 * @author Ganeshan Nagarajan
 * @since 09/01/23
 */


    /*
    Name - Min Stack
    Link - https://leetcode.com/problems/min-stack/
    Condition - Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.
    formula -  push => 2*n-min
               pop => pre_min = (2*current_min)-(top element)
    */

public class MinStack {

    private Stack<Long> stack;
    private Long min;


    public MinStack() {
        this.stack = new Stack<>();
        min = Long.MAX_VALUE;
    }

    public static void main(String[] args) {
        int[] arr = {4, 5, 2, 8, 1, 3};
        MinStack minStack = new MinStack();
        for (int n : arr) {
            minStack.push(n);
            System.out.println(minStack.getMin());
        }
    }

    public void push(int x) {
        Long val = Long.valueOf(x);
        if (stack.isEmpty()) {
            stack.push(val);
            min = val;
        } else if (min > val) {
            stack.push(2 * val - min);
            min = val;
        } else {
            stack.push(val);
        }
    }

    public void pop() {
        if (stack.peek() < getMin()) {
            min = (2 * getMin()) - stack.peek();
        }
        stack.pop();
    }

    public Long top() {
        if (stack.peek() < getMin()) {
            return getMin();
        } else return stack.peek();
    }

    public Long getMin() {
        return this.min;
    }
}
