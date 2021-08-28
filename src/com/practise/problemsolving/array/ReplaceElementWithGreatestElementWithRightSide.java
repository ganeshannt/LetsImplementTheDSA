package com.practise.problemsolving.array;

public class ReplaceElementWithGreatestElementWithRightSide {

    private void firstApproach(int[] arr) {
        int max_index = 0;
        for (int i = 0; i < arr.length - 1;) {
            max_index = largestElementInRange(arr, i + 1, arr.length);
            if (i < max_index) {
                while (i < max_index) {
                    arr[i] = arr[max_index];
                    i += 1;
                }
            } else {
                i += 1;
            }
        }
        arr[arr.length - 1] = -1;
        for (int i : arr) {
            System.out.println(i);
        }
    }

    private int largestElementInRange(int arr[], int start, int last) {
        int max = start;
        while (start < last) {
            if (arr[max] < arr[start]) {
                max = start;
            }
            start++;
        }
        return max;
    }

    public static void main(String[] args) {
        ReplaceElementWithGreatestElementWithRightSide element = new ReplaceElementWithGreatestElementWithRightSide();
        int arr[] = { 400};
        // int index[] = { 18,6,6,6,1,-1 };
        // m = element.hashTableApproach(arr);
        element.firstApproach(arr);
        // element.moveahead(arr, 1, 4);
    }

}
