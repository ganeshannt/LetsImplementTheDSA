package com.practise.ds.array.problem;

import com.practise.commons.Utils;

/*

Name - Sort Colors
Link - https://leetcode.com/problems/sort-colors/
Condition - You must solve this problem without using the library's sort function
Follow up: Could you come up with a one-pass algorithm using only constant extra space?

 */
public class SortColorCode {

    public static void main(String[] args) {
        SortColorCode element = new SortColorCode();
        int arr[] = {0, 0, 1, 1, 2, 1, 2};
        // Output: [2,1,1,0]
        element.firstApproach(arr);
    }

    /*
    Time Complexity - O(n)
    Space Complexity - o(1)
    Note - Slightly modified two pointer method
    */
    private void firstApproach(int[] arr) {
        int low = 0;
        int current = 0;
        int high = arr.length - 1;
        while (current < high) {
            if (arr[current] == 0) {
                if (current != low) {
                    Utils.swapByIndex(arr, current, low);
                }
                low++;
                current++;
            } else if (arr[current] == 1) {
                current++;
            } else if (arr[current] == 2) {
                Utils.swapByIndex(arr, current, high);
                high--;
            }
        }
        Utils.printArray(arr);
    }
}
