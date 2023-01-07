package com.practise.problemsolving.array;


/*

Name - Missing Two Number
Link - https://leetcode.com/problems/missing-number/

 */
public class MissingTwoNumbers {

    public static void main(String[] args) {
        int arr[] = {1, 3, 5, 6};
        bestApproach(arr);
    }

    private static void bestApproach(int[] arr) {
        int xor_value = arr[0];
        for (int i = 1; i < arr.length - 2; i++)
            xor_value ^= arr[i];

        for (int i = 1; i <= arr.length; i++)
            xor_value ^= i;

        System.out.println(xor_value);

        int set_bit = xor_value & ~(xor_value - 1);

        System.out.println(set_bit);

        int x = 0;
        int y = 0;

        for (int i = 0; i < arr.length - 2; i++) {
            if ((arr[i] & set_bit) > 0)

                /* XOR of first set in arr[] */
                x = x ^ arr[i];
            else
                /* XOR of second set in arr[] */
                y = y ^ arr[i];
        }

        for (int i = 1; i <= arr.length; i++) {
            if ((i & set_bit) > 0)

                /*
                 * XOR of first set in arr[] and {1, 2, ...n }
                 */
                x = x ^ i;
            else
                /*
                 * XOR of second set in arr[] and {1, 2, ...n }
                 */
                y = y ^ i;
        }
        System.out.println(x);
        System.out.println(y);
    }

}
