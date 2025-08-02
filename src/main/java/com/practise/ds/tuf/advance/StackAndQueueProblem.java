package com.practise.ds.tuf.advance;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

public class StackAndQueueProblem {

    public boolean isValidParanthesis(String str) {
        ArrayDeque<Character> arrayDeque = new ArrayDeque<>();

        for (char param : str.toCharArray()) {

            if (!arrayDeque.isEmpty()
                    && ((param == ')' && arrayDeque.peek() == '(')
                    || (param == ']' && arrayDeque.peek() == '[')
                    || (param == '}' && arrayDeque.peek() == '{'))) {
                arrayDeque.pop();
            } else {
                arrayDeque.addFirst(param);
            }
        }
        return arrayDeque.isEmpty();
    }

    /*
     * https://www.naukri.com/code360/problems/next-greater-element_670312
     * */
    public int[] nextGreaterElements(int[] arr) {
        int[] result = new int[arr.length];
        ArrayDeque<Integer> arrayDeque = new ArrayDeque<>();

        for (int i = arr.length - 1; i >= 0; --i) {
            while (!arrayDeque.isEmpty() && arr[i] >= arrayDeque.peek()) {
                arrayDeque.removeFirst();
            }
            result[i] = (arrayDeque.isEmpty()) ? -1 : arrayDeque.peek();
            arrayDeque.addFirst(arr[i]);
        }
        return result;
    }

    public List<Integer> countSmaller(int[] arr) {
        List<Integer> resultList = new ArrayList<>(arr.length);
        ArrayDeque<Integer> arrayDeque = new ArrayDeque<>();

        for (int i = arr.length - 1; i >= 0; --i) {
            while (!arrayDeque.isEmpty() && arr[i] <= arrayDeque.peek()) {
                arrayDeque.removeFirst();
            }
            resultList.add((arrayDeque.isEmpty()) ? -1 : arrayDeque.peek());
            arrayDeque.addFirst(arr[i]);
        }
        return resultList;
    }

    public int[] nextGreaterElements2(int[] arr) {
        int[] result = new int[arr.length];
        int[] hypoArr = new int[arr.length * 2];

        System.arraycopy(arr, 0, hypoArr, 0, arr.length);
        System.arraycopy(arr, 0, hypoArr, arr.length, arr.length);

        ArrayDeque<Integer> arrayDeque = new ArrayDeque<>();

        for (int i = hypoArr.length - 1; i >= hypoArr.length / 2; --i) {
            while (!arrayDeque.isEmpty() && hypoArr[i] >= arrayDeque.peek()) {
                arrayDeque.removeFirst();
            }
            arrayDeque.addFirst(hypoArr[i]);
        }


        for (int i = (hypoArr.length / 2) - 1; i >= 0; --i) {
            while (!arrayDeque.isEmpty() && hypoArr[i] >= arrayDeque.peek()) {
                arrayDeque.removeFirst();
            }
            result[i] = (arrayDeque.isEmpty()) ? -1 : arrayDeque.peek();
            arrayDeque.addFirst(hypoArr[i]);
        }
        return result;
    }

    public int[] nextGreaterElements2SpaceOptimized(int[] arr) {
        int n = arr.length;
        int output[] = new int[n];
        ArrayDeque<Integer> deque = new ArrayDeque<>();

        for (int i = (2 * n) - 1; i >= 0; --i) {
            while (!deque.isEmpty() && arr[deque.peekFirst()] <= arr[i % n]) {
                deque.removeFirst();
            }
            output[i % n] = (deque.isEmpty()) ? -1 : arr[deque.peekFirst()];
            deque.addFirst(i % n);
        }

        return output;
    }

    public int[] asteroidCollision(int[] asteroids) {
        ArrayDeque<Integer> asteroidsQueue = new ArrayDeque<>();
        for (int asteroid : asteroids) {
            if (asteroidsQueue.isEmpty() || asteroid >= 0 || asteroidsQueue.peek() < 0) {
                asteroidsQueue.addFirst(asteroid);
            } else {
                while (!asteroidsQueue.isEmpty()
                        && (asteroidsQueue.peek() > 0 && asteroidsQueue.peek() + asteroid < 0)) {
                    asteroidsQueue.removeFirst();
                }

                if (asteroidsQueue.isEmpty() || (asteroidsQueue.peek() < 0)) {
                    asteroidsQueue.addFirst(asteroid);
                }

                if (asteroidsQueue.peek() + asteroid == 0) {
                    asteroidsQueue.removeFirst();
                }
            }
        }
        int[] result = new int[asteroidsQueue.size()];

        int counter = asteroidsQueue.size() - 1;
        for (Integer a : asteroidsQueue) {
            result[counter--] = a;
        }
        return result;
    }

    public int[] nextSmallerElement(int[] arr) {
        int[] result = new int[arr.length];
        ArrayDeque<Integer> arrayDeque = new ArrayDeque<>();

        for (int i = arr.length - 1; i >= 0; i--) {
            while (!arrayDeque.isEmpty() && arr[i] <= arr[arrayDeque.peek()]) {
                arrayDeque.removeFirst();
            }
            result[i] = (arrayDeque.isEmpty()) ? arr.length : arrayDeque.peek();
            arrayDeque.addFirst(i);
        }
        return result;
    }

    public int[] previousSmallerOrEqualElement(int[] arr) {
        int[] result = new int[arr.length];
        ArrayDeque<Integer> arrayDeque = new ArrayDeque<>();

        for (int i = 0; i < arr.length; i++) {
            while (!arrayDeque.isEmpty() && arr[i] < arr[arrayDeque.peek()]) {
                arrayDeque.removeFirst();
            }
            result[i] = (arrayDeque.isEmpty()) ? -1 : arrayDeque.peek();
            arrayDeque.addFirst(i);
        }
        return result;
    }


    public int[] previousGreaterOrEqualElement(int[] arr) {
        return null;
    }

    public int sumSubarrayMins(int[] arr) {

        int[] nse = nextSmallerElement(arr);
        int[] psee = previousSmallerOrEqualElement(arr);
        int n = arr.length;
        int mod = (int) 1e9 + 7; // Mod value
        int sum = 0;

        for (int i = 0; i < n; i++) {
            int left = i - psee[i];
            int right = nse[i] - i;
            long freq = left * right * 1L;

            // Contribution due to current element
            int val = (int) ((freq * arr[i]) % mod);
            // Updating the sum
            sum = (sum + val) % mod;
        }

        return sum;
    }


    public int sumSubarrayMaxs(int[] arr) {

        int[] nse = nextGreaterElements(arr);
        int[] psee = previousGreaterOrEqualElement(arr);
        int n = arr.length;
        int mod = (int) 1e9 + 7; // Mod value
        int sum = 0;

        for (int i = 0; i < n; i++) {
            int left = i - psee[i];
            int right = nse[i] - i;
            long freq = left * right * 1L;

            // Contribution due to current element
            int val = (int) ((freq * arr[i]) % mod);
            // Updating the sum
            sum = (sum + val) % mod;
        }
        return sum;
    }


    /*
     * https://takeuforward.org/plus/dsa/stack-and-queues/monotonic-stack/sum-of-subarray-ranges
     * */
    public long subArrayRanges(int[] nums) {
        return (long) sumSubarrayMaxs(nums) - sumSubarrayMins(nums);
    }


    public long subArrayRangesBf(int[] nums) {
        long result = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            int min = nums[i];
            int max = nums[i];
            for (int j = i + 1; j < nums.length; j++) {
                min = Math.min(min, nums[j]);
                max = Math.max(max, nums[j]);

                result += max - min;
            }
        }
        return result;
    }


    /*
     * https://takeuforward.org/plus/dsa/stack-and-queues/monotonic-stack/remove-k-digits
     * */
    public String removeKdigits(String nums, int k) {
        if (nums.length() <= k) return "0";

        ArrayDeque<Character> arrayDeque = new ArrayDeque<>();
        arrayDeque.push(nums.charAt(0));

        for (int i = 1; i < nums.length(); i++) {
            while (!arrayDeque.isEmpty() && k > 0 && nums.charAt(i) < arrayDeque.peek()) {
                arrayDeque.pop();
                k--;
            }
            arrayDeque.push(nums.charAt(i));
        }

        while (k > 0) {
            arrayDeque.pop();
            k--;
        }
        while (!arrayDeque.isEmpty() && arrayDeque.peekLast() == '0') {
            arrayDeque.removeLast();
        }

        StringBuilder sb = new StringBuilder();
        while (!arrayDeque.isEmpty()) {
            sb.append(arrayDeque.pop());
        }

        return sb.isEmpty() ? "0" : sb.reverse().toString();
    }


    public static void main(String[] args) {
        StackAndQueueProblem solution = new StackAndQueueProblem();
        solution.subArrayRangesBf(new int[]{4, -2, -3, 4, 1});
    }


}
