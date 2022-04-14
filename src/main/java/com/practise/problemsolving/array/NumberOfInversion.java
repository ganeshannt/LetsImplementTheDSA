package com.practise.problemsolving.array;


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
        int inversions = mergeSort(arr, 0, arr.length - 1);
        System.out.println(inversions);
    }

    private int mergeSort(int[] arr, int start, int end) {
        int inversion = 0;
        if (start < end) {
            int mid = (end + start) / 2;
            inversion += mergeSort(arr, start, mid);
            inversion += mergeSort(arr, mid + 1, end);
            inversion += mergeProcedure(arr, start, end, mid);
        }
        return inversion;
    }

    private int mergeProcedure(int[] arr, int start, int end, int mid) {
        int i = start;
        int j = mid + 1;
        int index = 0;
        int count = 0;
        int temparr[] = new int[end - start + 1];
        while (i <= mid && j <= end) {
            if (arr[i] <= arr[j]) {
                temparr[index++] = arr[i++];
            } else {
                count += (mid - i + 1);
                temparr[index++] = arr[j++];
            }
        }

        while (i <= mid) {
            temparr[index++] = arr[i++];
        }
        while (j <= end) {
            temparr[index++] = arr[j++];
        }
        return count;
    }

}
