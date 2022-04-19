package com.practise.problemsolving.searchingandsorting;

import com.practise.commons.Utils;

import java.util.HashMap;
import java.util.Map;

/*
Name - Sort Colors
Link - https://leetcode.com/problems/sort-colors/
Condition - Could you come up with a one-pass algorithm using only constant extra space?
*/
public class SortZerosAndOnes {

    public static void main(String[] args) {
        int arr[] = {1, 2, 0};
        SortZerosAndOnes sortZerosAndOnes = new SortZerosAndOnes();
        sortZerosAndOnes.singlePassSolution(arr);
    }


    /*
     Time Complexity -  O(n)
     Space Complexity - O(n)
     Note - Hash Map
      */
    public static void doublePassSolution(int arr[]) {
        Map<Integer, Integer> countMap = new HashMap<Integer, Integer>();
        for (int i : arr) {
            if (countMap.containsKey(i)) {
                countMap.put(i, (countMap.get(i)) + 1);
            } else {
                countMap.put(i, 1);
            }
        }
        int j = 0;

        for (Map.Entry<Integer, Integer> set : countMap.entrySet()) {
            for (int i = 0; i < set.getValue(); i++) {
                arr[j++] = set.getKey();
            }
        }
    }


    /*
    Time Complexity -  O(n)
    Space Complexity - O(1)
    Note - Three pointer technique
     */
    public static void singlePassSolution(int arr[]) {
        int c = 0, l = 0;
        int h = arr.length - 1;
        while (c <= h) {
            if (arr[c] == 0) {
                if (c != l) {
                    Utils.swapByIndex(arr, c, l);
                }
                c++;
                l++;
            } else if (arr[c] == 1) {
                c++;
            } else if (arr[c] == 2) {
                Utils.swapByIndex(arr, c, h);
                h--;
            }
        }
        Utils.printArray(arr);
    }
}