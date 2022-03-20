package com.practise.ds.random;


public class MergeSort {

    public static void main(String[] args) {
        MergeSort mergeSort = new MergeSort();
        int arr[] = {1, 234, 45, 3453, 45345, 2354, 34545, 43, 2, 34, 435345, 23};
        mergeSort.recursiveMerge(arr, 0, arr.length - 1);
        for (int i : arr) {
            System.out.println(i);
        }
    }

    private void recursiveMerge(int[] arr, int start, int end) {

        if (start < end) {
            int mid = (end + start) / 2;
            recursiveMerge(arr, start, mid);
            recursiveMerge(arr, mid + 1, end);
            mergeProcedure(arr, start, end, mid);
        }
    }

    private void mergeProcedure(int[] arr, int start, int end, int mid) {
        int i = start;
        int j = mid + 1;
        int index = 0;
        int temparr[] = new int[end - start + 1];

        while (i <= mid && j <= end) {
            if (arr[i] <= arr[j]) {
                temparr[index] = arr[i];
                i++;
            } else {
                temparr[index] = arr[j];
                j++;
            }
            index++;
        }

        while (i <= mid) {
            temparr[index++] = arr[i];
            i++;
        }
        while (j <= end) {
            temparr[index++] = arr[j];
            j++;
        }

        for (int k = start; k <= end; k++) {
            arr[k] = temparr[k - start];
        }
    }

}
