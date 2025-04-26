package com.practise.ds.tuf.advance;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArrayHard {

    public static void main(String[] args) {
        int[] nums1 = {4, 0, 0, 0, 0, 0};
        int[] nums2 = {1, 2, 3, 5, 6};
        int m = 1, n = 5;

        // Create an instance of the Solution class
        ArrayHard sol = new ArrayHard();

        // Merge nums1 and nums2
        sol.merge(nums1, m, nums2, n);

        // Output the merged arrays
        System.out.println("The merged arrays is:");
        System.out.print("nums1[] = ");
        for (int i = 0; i < m; i++) {
            System.out.print(nums1[i] + " ");
        }
    }

    public int majorityElement(int[] nums) {
        if (nums.length == 1) return nums[0];
        Arrays.sort(nums);
        return nums[nums.length / 2];
    }

    public int majorityElementOptimal(int[] nums) {
        if (nums.length == 1) return nums[0];

        int majorityElement = nums[0];

        int majCount = 1;

        for (int i = 1; i < nums.length; i++) {
            if (majorityElement == nums[i]) {
                majCount++;
            } else {
                if (majCount == 0) {
                    majorityElement = nums[i];
                } else {
                    majCount--;
                }
            }
        }
        return majorityElement;
    }

    public List<Integer> majorityElementTwo(int[] nums) {
        if (nums.length == 1) return List.of(nums[0]);
        Arrays.sort(nums);

        List<Integer> result = new ArrayList<>();

        int majorityCount = (nums.length / 3);
        int majorityElement = nums[0];
        int count = 1;

        for (int i = 1; i < nums.length; i++) {
            if (majorityElement == nums[i]) {
                count++;
            } else {
                if (count > majorityCount && !result.contains(majorityElement)) {
                    result.add(majorityElement);
                }
                count = 1;
                majorityElement = nums[i];
            }
        }
        if (count > majorityCount && !result.contains(majorityElement)) {
            result.add(majorityElement);
        }
        return result;
    }

    public int[] findMissingRepeatingNumbers(int[] nums) {
        int n = nums.length;
        int duplicate = -1;
        int sum = 0;
        int totalSum = n * (n + 1) / 2;

        // Use an integer array to track occurrences
        boolean[] seen = new boolean[n + 1]; // Indices 1 to n

        for (int num : nums) {
            if (seen[num]) {
                duplicate = num; // Found the duplicate
            } else {
                sum += num;
                seen[num] = true;
            }
        }

        // Calculate the missing element
        int missingElement = totalSum - sum;

        return new int[]{duplicate, missingElement};
    }

    public long numberOfInversionsBrute(int[] nums) {
        int inversionCount = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] > nums[j]) {
                    inversionCount++;
                }

            }
        }
        return inversionCount;
    }

    public long numberOfInversions(int[] nums) {
        return mergeSort(nums, 0, nums.length - 1);
    }

    private long mergeSort(int[] nums, int start, int end) {
        long inversion = 0;
        if (start < end) {
            int mid = (end + start) / 2;
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

        System.arraycopy(temp, 0, nums, start, temp.length);

        return inversion;

    }

    public int reversePairsBrute(int[] nums) {
        int reversePairCount = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] > 2 * nums[j]) {
                    reversePairCount++;
                }

            }
        }
        return reversePairCount;
    }

    public int maxProduct(int[] nums) {

        if (nums.length == 1) return nums[0];

        int prefix = 1;
        int sufix = 1;
        int max = 0;

        for (int i = 0; i < nums.length; i++) {

            if (prefix == 0) prefix = 1;
            if (sufix == 0) sufix = 1;

            prefix *= nums[i];
            sufix *= nums[nums.length - 1 - i];

            max = Math.max(max, Math.max(prefix, sufix));
        }
        return max;
    }

    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int left = m - 1;
        int right = 0;

        while (left >= 0 && right < n) {
            if (nums1[left] > nums2[right]) {
                int temp = nums1[left];
                nums1[left] = nums2[right];
                nums2[right] = temp;
                left--;
                right++;
            } else break;
        }

        Arrays.sort(nums1, 0, m);
        Arrays.sort(nums2);

        System.arraycopy(nums2, 0, nums1, m, nums2.length);

    }
}
