package com.practise.algorithm.sorting.problem;

/*
Name - Number of 1 Bits
Link - https://leetcode.com/problems/number-of-1-bits/
Condition - If this function is called many times, how would you optimize it?
*/
public class NumberOfBits {

    public static void main(String[] args) {
        NumberOfBits bits = new NumberOfBits();
        int n = 00000000000000000000000000001011;
        bits.approach1(n);
    }


    /*
    Time Complexity -  O(n)
    Space Complexity - o(1)
    Note - Bit manipulation
     */
    private static void approach1(int n) {
        int count = 0;
        while (n != 0) {
            count += (n % 2) & 1;
            n >>= 1;
        }
        System.out.println(count);
    }
}