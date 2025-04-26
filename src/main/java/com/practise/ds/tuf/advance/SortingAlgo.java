package com.practise.ds.tuf.advance;

import java.util.Arrays;

public class SortingAlgo {


    public static void main(String[] args) {
        SortingAlgo solution = new SortingAlgo();
        System.out.println(Arrays.toString(solution.mergeSort(new int[]{3, 4, 5, 6, 2, 3})));
    }

    public int[] selectionSort(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (nums[j] < nums[minIndex]) {
                    minIndex = j;
                }
            }
            int temp = nums[minIndex];
            nums[minIndex] = nums[i];
            nums[i] = temp;
        }
        return nums;
    }

    public int[] bubbleSort(int[] nums) {
        int n = nums.length;
        boolean isSwapped = false;

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {

                if (nums[j] > nums[j + 1]) {
                    isSwapped = true;
                    int temp = nums[j];
                    nums[j] = nums[j + 1];
                    nums[j + 1] = temp;
                }
            }
            if (!isSwapped) break;
        }
        return nums;
    }

    public int[] insertionSort(int[] nums) {
        int n = nums.length;

        for (int i = 1; i < n; i++) {
            for (int j = i; j > 0; j--) {
                if (nums[j] < nums[j - 1]) {
                    int temp = nums[j];
                    nums[j] = nums[j - 1];
                    nums[j - 1] = temp;
                } else {
                    break; // corner case to improve performance
                }
            }
        }
        return nums;
    }

    public int[] mergeSort(int[] nums) {
        return recursiveMergeSort(nums, 0, nums.length - 1);
    }

    private int[] recursiveMergeSort(int[] nums, int start, int end) {
        if (start < end) {
            int mid = start + (end - start) / 2;
            recursiveMergeSort(nums, start, mid);
            recursiveMergeSort(nums, mid + 1, end);
            mergeProcedure(nums, start, mid, end);
        }
        return nums;
    }

    private void mergeProcedure(int[] nums, int start, int mid, int end) {
        int i = start;
        int j = mid + 1;
        int index = 0;

        int[] temp = new int[end - start + 1];

        while (i <= mid && j <= end) {
            if (nums[i] <= nums[j]) {
                temp[index++] = nums[i++];
            } else {
                temp[index++] = nums[j++];
            }
        }

        while (i <= mid) {
            temp[index++] = nums[i++];
        }

        while (j <= end) {
            temp[index++] = nums[j++];
        }

//        copy to an original array
        for (int k = start; k <= end; k++) {
            nums[k] = temp[k - start];
        }


        System.arraycopy(temp, 0, nums, start, temp.length);
    }
}
