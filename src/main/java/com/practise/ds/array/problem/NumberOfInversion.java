package com.practise.ds.array.problem;


/*

Name - Global and Local Inversions
Link - https://leetcode.com/problems/global-and-local-inversions/

 */

public class NumberOfInversion {

    public static void main(String[] args) {
        int arr[] = {1, 0, 2};
        // 1, 2, 4, 6, 7, 9, 8, 3, 10
        NumberOfInversion inversion = new NumberOfInversion();
        // inversion.firstApproach(arr);
        inversion.firstApproach(arr);
    }

    /*
    Time Complexity - o(n)
    Space Complexity - o(1)
    Note - The difference between local and global is global also include nonadjacent i and j, so simplify the question to for every i, find in range 0 to i-2, see if there is a element larger than A[i], if it exist, we can return false directly. and we can maintain a variable max for the linear implementation.
    */
    private void firstApproach(int[] arr) {
        int max = -1;
        for (int i = 0; i < arr.length - 2; i++) {
            max = Math.max(max, arr[i]);
            if (max > arr[i + 2])
                System.out.println("local and global inversions aren't same");
        }
        System.out.println("local and global inversions are same");
    }


    /*
    Time Complexity - o(nlogn)
    Space Complexity - o(n)
    Note - used merge sort to count inversion. we are counting inversion while comparing two elements to merge
    */

    private void bestApproach(int[] arr) {
        int inversions = (int) mergeSort(arr, 0, arr.length - 1);
        System.out.println(inversions);
    }

    private long mergeSort(int[] nums, int start, int end) {
        long inversion = 0;
        if (start < end) {
            int mid = start + (end - start) / 2;
            inversion += mergeSort(nums, start, mid);
            inversion += mergeSort(nums, mid + 1, end);
            inversion += mergeProcedure(nums, start, mid, end);
        }
        return inversion;
    }

    private long mergeProcedure(int[] nums, int start, int mid, int end) {
        int i = start;
        int j = mid + 1;
        long inversion = 0L;
        int k = 0;

        int[] temp = new int[end - start + 1];

        while (i <= mid && j <= end) {
            if (nums[i] <= nums[j]) {
                temp[k++] = nums[i++];
            } else {
                temp[k++] = nums[j++];
                inversion += (mid - i + 1);
            }
        }

        while (i <= mid) {
            temp[k++] = nums[i++];
        }

        while (j <= end) {
            temp[k++] = nums[j++];
        }

        // Copy the sorted elements back to the original array
        System.arraycopy(temp, 0, nums, start, temp.length);

        return inversion;
    }


}
