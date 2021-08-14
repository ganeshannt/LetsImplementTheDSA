package com.practise.problemsolving.searchingandsorting;

public class FindPeakElement {

    //  Type of Solution - best
    //  formula - nil
    //  Time Complexity - O(logn)  -> customized binary tree approach
    //  Space Complexity - o(1) -> no extra spaces used
    private void approach1(int[] arr) {
        int left = 0;
        int right = arr.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] < arr[mid + 1]) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        System.out.println(left);
    }

    public static void main(String[] args) {
        FindPeakElement element = new FindPeakElement();
        int arr[] = { 2, 1 };
        element.approach1(arr);
    }

}
