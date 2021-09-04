package com.practise.problemsolving.array;

public class NumberOfInversion {

    private void firstApproach(int[] arr) {
        int inversion_count = 0;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i - 1] > arr[i]) {
                inversion_count += i;
            }
        }
        System.out.println(inversion_count);
    }

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
                count += (mid-i + 1);
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

    public static void main(String[] args) {
        int arr[] = { 1, 9, 6, 4, 5 };
        // 1, 2, 4, 6, 7, 9, 8, 3, 10
        NumberOfInversion inversion = new NumberOfInversion();
        // inversion.firstApproach(arr);
        inversion.bestApproach(arr);
    }

}
