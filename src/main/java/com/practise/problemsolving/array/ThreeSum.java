package com.practise.problemsolving.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/*

Name - 3Sum
Link - https://leetcode.com/problems/3sum/
Condition - Notice that the solution set must not contain duplicate triplets

 */
public class ThreeSum {

    public static void main(String[] args) {
        ThreeSum matrix = new ThreeSum();
        int[] arr = {-1, 0, 1, 2, -1, -4};
        matrix.bruteForce(arr);
    }

    /*
    Time Complexity - o(n)^3
    Space Complexity - o(1)
    Note - calculate three values that result of sum should be zero
    */
    private void bruteForce(int[] arr) {
        if (arr.length == 0 || arr.length == 1)
            return;

        ArrayList<List<Integer>> finalList = new ArrayList<>();
        ArrayList<Integer> arrayList = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                for (int k = j + 1; k < arr.length; k++) {
                    if (arr[i] + arr[j] + arr[k] == 0) {
                        arrayList.add(0, arr[i]);
                        arrayList.add(1, arr[j]);
                        arrayList.add(2, arr[k]);
                        if (!finalList.contains(arrayList)) {
                            finalList.add(arrayList);
                        }
                    }
                }
            }
        }
    }

    /*
    Time Complexity - o(n)^2
    Space Complexity - o(1)
    Note - Sort the array, iterate through the list, and use another two pointers to approach the target.
    */
    private void secondApproach(int arr[]) {
        Arrays.sort(arr);
        int j = 0;
        int sum = 0;
        List<List<Integer>> finalList = new ArrayList<>();
        for (int i = 0; i < arr.length - 1; i++) {
            j = i + 1;
            int r = arr.length - 1;
            while (j < r) {
                sum = arr[i] + arr[j] + arr[r];
                if (sum == 0) {
                    List<Integer> list = new ArrayList<>();
                    list.add(arr[i]);
                    list.add(arr[j]);
                    list.add(arr[r]);
                    finalList.add(list);
                    j++;
                    r--;
                } else if (sum < 0) {
                    j++;
                } else {
                    r--;
                }
            }
        }
        // get unique element from array list by using set
        finalList = new ArrayList<>(new HashSet<>(finalList));
    }
}
