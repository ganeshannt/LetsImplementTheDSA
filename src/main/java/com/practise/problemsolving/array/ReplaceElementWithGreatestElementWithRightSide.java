package com.practise.problemsolving.array;


import com.practise.commons.Utils;

/*

Name - Replace Elements with The Greatest Element on Right Side
Link - https://leetcode.com/problems/replace-elements-with-greatest-element-on-right-side/

 */
public class ReplaceElementWithGreatestElementWithRightSide {

    public static void main(String[] args) {
        ReplaceElementWithGreatestElementWithRightSide element = new ReplaceElementWithGreatestElementWithRightSide();
        int arr[] = {56903, 18666, 60499, 57517, 26961};
        // int index[] = { 18,6,6,6,1,-1 };
        // m = element.hashTableApproach(arr);
        element.bestApproach(arr);
        // element.moveahead(arr, 1, 4);
    }


    /*
    Time Complexity - O(n^2)
    Space Complexity - o(1)
    Note - find max and replace current element with max
    */
    private void bruteForceApproach(int[] arr) {

        for (int i = 0; i < arr.length; i++) {
            int max = Integer.MIN_VALUE;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] > max) {
                    max = arr[j];
                }
            }
            arr[i] = max;
        }
        arr[arr.length - 1] = -1;
        Utils.printArray(arr);
    }


    /*
    Time Complexity - O(n)
    Space Complexity - o(1)
    Note - reverse traversing is a key
    */
    private void bestApproach(int[] arr) {
        int max = -1;
        int current_element = 0;
        for (int i = arr.length - 1; i >= 0; i--) {
            current_element = arr[i];
            arr[i] = max;
            if (current_element > max) {
                max = current_element;
            }
        }
        Utils.printArray(arr);
    }

}
