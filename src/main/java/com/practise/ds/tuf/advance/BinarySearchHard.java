package com.practise.ds.tuf.advance;

public class BinarySearchHard {

    public static void main(String[] args) {
        BinarySearchHard solution = new BinarySearchHard();
        int[] arr = {7, 7, 7, 7, 13, 11, 12, 7};
        int ans = solution.findPeakElement(arr);

        if (ans == -1) {
            System.out.println("We cannot make m bouquets.");
        } else {
            System.out.println("We can make bouquets on day " + ans);
        }
    }

    public int findPeakElement(int[] arr) {
        int start = 0;
        int end = arr.length - 1;

        while (start < end) {
            int mid = start + (end - start) / 2;
            if (arr[mid] < arr[mid + 1]) {
                start = mid + 1;
            } else {
                end = mid;
            }
        }
        return start;
    }

    public int kthElement(int[] a, int[] b, int k) {
        int i = 0;
        int j = 0;
        int result = 0;

        while (i < a.length && j < b.length && (i + j) < k) {
            if (a[i] < b[j]) {
                result = a[i];
                i++;
            } else {
                result = b[j];
                j++;
            }
        }

        while ((i + j) < k && i < a.length) {
            result = a[i];
            i++;
        }

        while ((i + j) < k && j < b.length) {
            result = b[j];
            j++;
        }

        return result;
    }
}