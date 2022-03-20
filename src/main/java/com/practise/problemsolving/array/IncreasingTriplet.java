package com.practise.problemsolving.array;

public class IncreasingTriplet {

    public static void main(String[] args) {
        IncreasingTriplet element = new IncreasingTriplet();
        int arr[] = {1, 2, 2, 1, 5};
        element.bestApproach(arr);
    }

    private void avarageApproach(int arr[]) {
        if (arr.length < 3) {
            return;
        }
        int min_index = 0;
        int low = -1;
        int mid = -1;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] <= arr[min_index]) {
                min_index = i;
            } else if (mid == -1) {
                low = min_index;
                mid = i;
            } else if (arr[i] <= arr[mid]) {
                low = min_index;
                mid = i;
            } else {
                System.out.println("true");
                break;
            }
        }
        System.out.println("false");
    }

    private void bestApproach(int arr[]) {
        if (arr.length < 3) {
            return;
        }
        int i = Integer.MAX_VALUE;
        int j = Integer.MAX_VALUE;
        for (int val : arr) {
            if (i > val) {
                i = val;
            } else if (j > val) {
                j = val;
            } else {
                System.out.println("true");
                break;
            }
        }
        System.out.println("false");
    }
}
