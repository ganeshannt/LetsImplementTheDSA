package com.practise.problemsolving.array;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


/*

Name - Find Numbers with Even Number of Digits
Link - https://leetcode.com/problems/find-numbers-with-even-number-of-digits/

 */
public class EvenNumberOfDigits {


    private int bruteForceApproach(int arr[]) {
        int num = 0;
        int digits = 0;
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            num = arr[i];
            digits = 0;
            while (num > 0) {
                num /= 10;
                digits++;
            }
            if (digits > 0 && digits % 2 == 0)
                count++;
        }
        return count;
    }

    /*
     * Input constraint - 0-10^5 ... so we can use this solution
     * it will not work if input range increase
     * */
    private int goodApproach(int arr[]) {
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > 9 && arr[i] < 100 || arr[i] > 999 && arr[i] < 10000 || arr[i] == 100000)
                count++;
        }
        return count;
    }


    /*
     * simple but not efficient
     * */
    private int simpleApproach(int arr[]) {
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            String s = String.valueOf(arr[i]);
            if (s.length() % 2 == 0)
                count++;
        }
        return count;
    }


    @Test
    public void testEvenNumberOfDigits() {
        EvenNumberOfDigits digits = new EvenNumberOfDigits();
        int arr[] = {555, 901, 482, 1771};
        Assertions.assertEquals(1, digits.bruteForceApproach(arr));
    }

}