package com.practise.problemsolving.searchingandsorting;

import com.practise.commons.Utils;

import java.util.Arrays;

/*
Name - wiggle sort
Link - https://leetcode.com/problems/wiggle-sort/
*/
public class WiggleSort {

    public static void main(String[] args) {
        WiggleSort sort = new WiggleSort();
        int arr[] = {3, 5, 2, 1, 6, 4};
        sort.approach2(arr);
    }

    /*
     Time Complexity -  O(n)
     Space Complexity - O(n)
     Note - Hash Map
      */
    public void approach1(int arr[]) {
        Arrays.sort(arr);
        for (int i = 0; i < arr.length - 1; i++) {
            if (i % 2 != 0) {
                Utils.swapByIndex(arr, i, i + 1);
                i++;
            }
        }
        Utils.printArray(arr);
    }

    /*
     Time Complexity -  O(n)
     Space Complexity - O(n)
     Note - Hash Map
      */
    public void approach2(int arr[]) {
        for (int i = 0; i < arr.length - 1; i++) {
            if (i % 2 != 0) {
                if (arr[i - 1] > arr[i]) {
                    Utils.swapByIndex(arr, i - 1, i);
                }
                if (arr[i + 1] > arr[i]) {
                    Utils.swapByIndex(arr, i + 1, i);
                }
            }
        }
        Utils.printArray(arr);
    }
}
