package com.practise.problemsolving.array;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Stack;

public class MergeInterval {

    private void firstApproach(int[][] arr) {
        if (arr.length == 1)
            return;

        // sort array depends on 0th element in each array set
        Arrays.sort(arr, (a, b) -> a[0] - b[0]);
        Stack<int[]> stack = new Stack<>();

        for (int i = 0; i < arr.length; i++) {
            if (stack.isEmpty()) {
                stack.push(arr[i]);
            } else {
                int current[] = stack.pop();
                if (current[1] >= arr[i][0]) {
                    current[1] = Math.max(arr[i][1], current[1]);
                    stack.push(current);
                } else {
                    stack.push(current);
                    stack.push(arr[i]);
                }
            }
        }

        Iterator<int[]> stackIterator = stack.iterator();
        int output[][] = new int[stack.size()][2];
        int count = 0;
        while (stackIterator.hasNext()) {
            output[count++] = stackIterator.next();
        }
        for (int[] is : output) {
            for (int i : is) {
                System.out.println(i);
            }
        }
    }

    public static void main(String[] args) {
        MergeInterval interval = new MergeInterval();
        int[][] arr = { { 1, 4 }, { 4, 5 } };
        interval.firstApproach(arr);
    }
}
