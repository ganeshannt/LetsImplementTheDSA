package com.practise.ds.tuf.advance;

import com.practise.commons.Utils;

import java.util.ArrayDeque;

public class StackAndQueueFaq {



    /*
     * https://takeuforward.org/plus/dsa/stack-and-queues/faqs/sliding-window-maximum
     * */

    public static int[] maxSlidingWindowBf(int[] arr, int k) {
        int[] result = new int[arr.length - k + 1];

        for (int i = 0; i < arr.length - k + 1; i++) {
            int max = Integer.MIN_VALUE;
            for (int j = i; j < i + k; j++) {
                max = Math.max(max, arr[j]);
            }
            result[i] = max;
        }
        return result;
    }



    public static int[] maxSlidingWindow(int[] arr, int k) {
        int[] result = new int[arr.length - k + 1];
        ArrayDeque<Integer> deque = new ArrayDeque<>();

        int resultIndex = 0;

        for (int i = 0; i < arr.length; i++) {

            if (!deque.isEmpty() && deque.peekLast() <= i - k) {
                deque.pollLast();
            }

            while (!deque.isEmpty() && arr[i] > arr[deque.peek()]) {
                deque.pop();
            }

            deque.push(i);

            if (i >= (k - 1) && !deque.isEmpty()) {
                result[resultIndex++] = arr[deque.peekLast()];
            }
        }
        return result;

    }


    public int[] stockSpan(int[] arr, int n) {

        return null;
    }




    public static void main(String[] args) {
        Utils.printArray(maxSlidingWindow(new int[]{1, 3, -1, -3, 5, 3, 6, 7}, 3));
    }


}
