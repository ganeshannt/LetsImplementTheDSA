package com.practise.playground.interview;


/*
 * ip: ahjkahabbcdf
 * out: ahjk
 *
 * ip: abcdefahijk , cdefghzxy
 * out:
 * 1. remove duplicates
 *
 *
 * accept String
 * longest substring without duplicates
 *
 * */


import java.util.HashSet;
import java.util.Set;

public class Quvia {

    static String longestSubString(String input) {
        String result = null;

        Set<Character> characterSet = new HashSet<>(input.length());
        int longestPairSoFar = 0;
        for (int i = 0; i < input.length(); i++) {

            for (int j = i; j < input.length(); j++) {
                char c = input.charAt(j);
                if (characterSet.contains(c)) {
                    if ((j - i) > longestPairSoFar) {
                        result = input.substring(i, j);
                        longestPairSoFar = j - i;
                    }
                    characterSet.clear();
                    break;
                } else {
                    characterSet.add(c);
                }
            }

            if (!characterSet.isEmpty() && longestPairSoFar < (input.length() - i)) {
                result = input.substring(i);
                break;
            }
        }
        return result;
    }


    /*
     * input: [-2, 1, -3, 4, -1, 2, 1, -5, 4]
     * ouput: 6
     *
     * input: [-2, 1, -3, 4, -1, -4, 2, 1, 4, -3]
     * ouput: 6
     *
     * input: [-2, -3, -1, -4, 2, 1, 4, -15]
     * ouput: 6
     *
     * input: [-2, -3, -1, -4]
     * ouput: -1
     *
     * input: [1,2,3]
     * ouput: 6
     *
     * input: [-1,1]
     * ouput: 1
     *
     * */


    static int maxSumInSubArr(int arr[]) {
        int maxSum = Integer.MIN_VALUE;
        if (arr.length == 1) return arr[0];

        for (int i = 0; i < arr.length; i++) {
            int localSum = arr[i];
            maxSum = Math.max(maxSum, localSum);
            for (int j = i + 1; j < arr.length; j++) {
                localSum = localSum + arr[j];
                maxSum = Math.max(maxSum, localSum);
            }
        }
        return maxSum;
    }


//    static int maxSumInSubArr(int arr[]) {
//        int result = 0;
//
//        if (arr.length == 1) return arr[0];
//
//        int maxNegative = Integer.MAX_VALUE;
//
//
//
//
//
//
//
//
//        return result;
//    }


    public static void main(String[] args) {
//        System.out.println(longestSubString("cdefghzxy"));
//        System.out.println(longestSubString("abcdefahijk"));
//        System.out.println(longestSubString("ahjkahabbcdf"));


        //System.out.println(maxSumInSubArr(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4})); //6
        //System.out.println(maxSumInSubArr(new int[]{-2, -3, -1, -4})); // -1
        //System.out.println(maxSumInSubArr(new int[]{1, 2, 3})); //6
        System.out.println(maxSumInSubArr(new int[]{-1, 1})); //1
    }
}
