package com.practise.ds.array.problem;

import com.practise.commons.Utils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/*

Name - How Many Numbers Are Smaller Than the Current Number
Link - https://leetcode.com/problems/how-many-numbers-are-smaller-than-the-current-number/

 */
public class SmallerThanCurrentNumber {

    public static void main(String[] args) {
        SmallerThanCurrentNumber element = new SmallerThanCurrentNumber();
        int arr[] = {8, 1, 2, 2, 3};
        // Output: [2,1,1,0]
        element.bestApproach(arr);

    }


    /*
    Time Complexity - O(nlogn)
    Space Complexity - o(n)
    Note - sort given array
    */
    private void firstApproach(int[] arr) {
        int temparr[] = arr.clone();
        Arrays.sort(temparr);
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < temparr.length; i++) {
            map.putIfAbsent(temparr[i], i);
        }

        for (int i = 0; i < arr.length; i++) {
            arr[i] = map.get(arr[i]);
            System.out.println(arr[i]);
        }
    }


    /*
    Time Complexity - o(n)
    Space Complexity - o(temparr)
    Note - go through constraints and prefix-sum is a key
    */
    private void bestApproach(int[] arr) {
        int temparr[] = new int[101];
//      traverse input array and update increment temp array value by 1
        for (int i : arr) {
            temparr[i] = (temparr[i] != 0) ? temparr[i] + 1 : 1;
        }

//      calculate prefix-sum
        for (int i = 1; i < temparr.length; i++) {
            temparr[i] = temparr[i - 1] + temparr[i];
        }

//      update input array depends on temp array value
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (arr[i] == 0) ? 0 : temparr[arr[i] - 1];
        }

        Utils.printArray(arr);
    }
}