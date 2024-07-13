package com.practise.algorithm.sorting.problem;

/**
 * @author Ganeshan Nagarajan
 * @since 19/12/23
 */

public class SearchInPosition {

    public static void main(String[] args) {
        int arr[] = {1, 3, 4, 6};
        int target = 2;
        SearchInPosition searchInPosition = new SearchInPosition();
        System.out.println(searchInPosition.binarySearchSolution(arr, target));
    }

    private int binarySearchSolution(int[] arr, int target) {
        int start = 0;
        int end = arr.length;
        int mid = 0;

        if (arr.length == 0) {
            return 0;
        }

        if (arr[0] >= target) {
            return 0;
        }

        while (start < end) {
            mid = (start + end) / 2;
            if (arr[mid] == target) {
                return mid;
            }
            if (arr[mid] < target) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }

        if (mid < arr.length / 2) {
            return mid;
        }

        return mid + 1;
    }

}
