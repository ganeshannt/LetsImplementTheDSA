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
                end = mid - 1;
            } else if ((arr[start] > target && target > arr[mid]) || (arr[start] < arr[mid] && target < arr[mid])) {
                start = mid;
            }
        }
        System.out.println(-1);
    }

    private void secondApproach(int[] arr, int target) {
        int pivot = arr.length - 1;
        int start = 0;
        int end = arr.length - 1;
        int mid = 0;
        while (start < end) {
            mid = (start + end) / 2;
            if (arr[mid] > arr[mid + 1]) {
                pivot = mid;
                System.out.println("pivot" + pivot);
                break;
            }
            if (arr[start] < arr[mid]) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        start = 0;
        end = arr.length - 1;
        mid = pivot;
        while (start < end) {
            if (arr[mid] == target) {
                System.out.println(mid);
                break;
            }
            if (arr[start] <= target && arr[mid] >= target) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
            mid = (start + end) / 2;
        }
        System.out.println(-1);
    }

    private void bestApproach(int[] arr, int target) {
        int start = 0;
        int end = arr.length - 1;
        int mid = 0;
        while (start <= end) {
            mid = (start + end) / 2;
            if (arr[mid] == target) {
                System.out.println(mid);
                break;
            } else if (arr[start] <= arr[mid]) {
                if (arr[start] <= target && arr[mid] >= target) {
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
            } else {
                if (arr[end] >= target && arr[mid] <= target) {
                    start = mid + 1;
                } else {
                    end = mid - 1;
                }
            }
        }
        System.out.println(-1);
    }

    public static void main(String[] args) {
        SearchInRotatedSortedArray element = new SearchInRotatedSortedArray();
        int arr[] = { 4, 5, 6, 7, 0, 1, 2 };
        element.bestApproach(arr, 6);
    }

}
