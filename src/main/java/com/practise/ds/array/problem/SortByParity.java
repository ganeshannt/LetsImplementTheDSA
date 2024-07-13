package com.practise.ds.array.problem;


import com.practise.commons.Utils;

/*

Name - Sort Array By Parity
Link - https://leetcode.com/problems/sort-array-by-parity/

 */
public class SortByParity {

    public static void main(String[] args) {
        SortByParity element = new SortByParity();
        int arr[] = {5};
        // Output: [2,1,1,0]
        element.firstApproach(arr);
    }

    /*
    Time Complexity - O(nlogn)
    Space Complexity - o(n)
    Note - Two pointer method
    */
    private void firstApproach(int[] arr) {
        if (arr.length == 0 || arr.length == 1) {
            System.out.println("empty array");
            return;
        }

        int first = 0;
        int second = 1;
        while (second < arr.length) {
            if (arr[second] % 2 != 0 || first == second) {
                second++;
            } else if (arr[first] % 2 == 0) {
                first++;
            } else if (arr[first] % 2 != 0 && arr[second] % 2 == 0) {
                Utils.swapByIndex(arr, first, second);
                first++;
                second++;
            }
        }
        Utils.printArray(arr);
    }
}
