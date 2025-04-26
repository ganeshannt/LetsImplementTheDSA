package com.practise.ds.tuf.basic;

import java.util.ArrayList;
import java.util.Vector;

public class RecursionDsa {


    public static void main(String[] args) {

        RecursionDsa solution = new RecursionDsa();
        // Example number
        int num = 529;

        // Call the addDigits method and print the result
        int result = solution.fib(6);
        System.out.println("Sum of digits: " + result);  // Expected output: 7

    }

    public int NnumbersSum(int N) {
        if (N == 1) return 1;
        return NnumbersSum(N - 1) + N;
    }

    public long factorial(int n) {
        if (n == 0) return 1;
        return factorial(n - 1) * n;
    }

    public int arraySum(int[] nums) {
        return sum(nums, 0);
    }

    private int sum(int[] nums, int left) {
        if (left == nums.length) return 0;
        return nums[left] + sum(nums, left + 1);
    }

    public Vector<Character> reverseString(Vector<Character> s) {
        reverseStringChar(s, 0, s.size() - 1);
        return s;
    }

    private void reverseStringChar(Vector<Character> s, int start, int end) {
        if (start >= end) return;
        Character temp = s.get(start);
        s.set(start, s.get(end));
        s.set(end, temp);
        reverseStringChar(s, start + 1, end - 1);
    }

    public boolean palindromeCheck(String s) {
        return recursivePalindromeCheck(s, 0, s.length() - 1);
    }

    private boolean recursivePalindromeCheck(String s, int start, int end) {
        if (start > end) return true;
        if (s.charAt(start) != s.charAt(end)) return false;
        return recursivePalindromeCheck(s, start + 1, end - 1);
    }

    public boolean checkPrime(int num) {
        return recursivePrimeCheck(num, 2);
    }

    private boolean recursivePrimeCheck(int num, int i) {
        if (i > num / 2) return true;
        if (num % i == 0) return false;
        return recursivePrimeCheck(num, i + 1);
    }

    public int[] reverseArray(int[] nums) {
        recursiveReverseArray(nums, 0, nums.length - 1);
        return nums;
    }

    private void recursiveReverseArray(int[] nums, int start, int end) {
        if (start > end) return;
        int temp = nums[start];
        nums[start] = nums[end];
        nums[end] = temp;
        recursiveReverseArray(nums, start + 1, end - 1);
    }

    public boolean isSorted(ArrayList<Integer> nums) {
        return recursiveSortedCheck(nums, 0, nums.size() - 1);
    }

    private boolean recursiveSortedCheck(ArrayList<Integer> nums, int i, int length) {
        if (i > length - 1) return true;
        if (nums.get(i) > nums.get(i + 1)) return false;
        return recursiveSortedCheck(nums, i + 1, nums.size() - 1);
    }

    public int addDigits(int num) {
        if (num < 10) return num;
        int sum = 0;
        while (num > 0) {
            sum += num % 10;
            num = num / 10;
        }
        return addDigits(sum);
    }

    //    multi-level recursion
    public int fib(int n) {
        if (n <= 1) return n;
        return fib(n - 1) + fib(n - 2);
    }
}
