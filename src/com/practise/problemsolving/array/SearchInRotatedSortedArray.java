package com.practise.problemsolving.array;

public class SearchInRotatedSortedArray {

    private void firstApproach(int[] arr, int target) {
        int start = 0;
        int end = arr.length;
        int mid = 0;
        while (start <= end) {
            mid = (start + end) / 2;
            if (arr[mid] == target) {
                System.out.println(mid);
                break;
            }
            if ((arr[start] <= target && target < arr[mid]) || (arr[start] > arr[mid] && target > arr[mid])) {
                end = mid-1;
            } else if ((arr[start] > target && target > arr[mid]) || (arr[start] < arr[mid] && target < arr[mid])) {
                start = mid;
            }
        }
        System.out.println(-1);
    }


    private void secondApproach(int[] arr, int target) {
        
    }

    public static void main(String[] args) {
        SearchInRotatedSortedArray element = new SearchInRotatedSortedArray();
        int arr[] = { 4, 5, 6, 7, 0, 1, 2 };
        element.firstApproach(arr, 6);
    }

}
