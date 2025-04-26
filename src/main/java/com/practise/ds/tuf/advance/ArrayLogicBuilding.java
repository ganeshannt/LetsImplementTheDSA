package com.practise.ds.tuf.advance;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import static com.practise.commons.Utils.printArray;

public class ArrayLogicBuilding {


    public static void main(String[] args) {
        int[] nums = {0, 0, 3, 3, 5, 6};
        int k = 8;

        System.out.println("Initial array: ");
        printArray(nums);

        // Create an instance of the Solution class
        ArrayLogicBuilding solution = new ArrayLogicBuilding();

        /* Function call to rotate the
        array to the left by k places */
//        solution.intersectionArray(new int[]{1, 2, 2, 3, 5}, new int[]{1, 2, 7});
        solution.unionArray(new int[]{3, 4, 6, 7, 9, 9}, new int[]{1, 5, 7, 8, 8});

        System.out.println("Array after rotating elements by " + k + " places: ");
        printArray(nums);
    }

    public int secondLargestElement(int[] nums) {

        if (nums.length == 1) return -1;

        int max = Integer.MIN_VALUE;
        int second = Integer.MIN_VALUE;

        for (int num : nums) {
            if (num > max) {
                second = max;
                max = num;
            } else if (num < max && second < num) {
                second = num;
            }
        }
        return (second == Integer.MIN_VALUE) ? -1 : second;
    }

    public int findMaxConsecutiveOnes(int[] nums) {
        int max = 0;
        int count = 0;
        for (int num : nums) {
            if (num == 1) {
                count++;
                max = Math.max(max, count);
            } else {
                count = 0;
            }
        }
        return max;
    }

    public void rotateArrayByKTimes(int[] nums, int k) {

        if (nums.length == 1) return;

        // edge case: if k is more than nums.length
        int n = nums.length; // Size of Array
        k = k % n; // To avoid unnecessary rotations

        int rotateTimes = k;

        int[] temp = new int[rotateTimes];

        for (int i = 0; i < rotateTimes; i++) {
            temp[i] = nums[i];
        }

        int j = 0;
        for (int i = rotateTimes; i < nums.length; i++) {
            nums[j++] = nums[i];
        }

        int i = 0;
        while (j < nums.length) {
            nums[j++] = temp[i++];
        }
    }

    public int removeDuplicates(int[] nums) {
        if (nums.length == 1) return 0;

        int j = 1;
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] != nums[i + 1]) {
                if (i + 1 != j) {
                    nums[j] = nums[i + 1];
                }
                j++;
            }
        }
        return j;
    }

    public void moveZeroes(int[] nums) {
        int j = nums.length - 1;
        for (int i = nums.length - 1; i >= 0; i--) {
            if (nums[i] == 0) {
                if (i != j) {
                    int index = i;
                    while (index < j) {
                        int temp = nums[index];
                        nums[index] = nums[index + 1];
                        nums[index + 1] = temp;
                        index++;
                    }
                }
                j--;
            }
            printArray(nums);
        }
    }

    public int missingNumber(int[] nums) {
        int n = nums.length;
        int total = n * (n + 1) / 2;
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += nums[i];
        }
        return total - sum;
    }

    public int[] unionArrayBrute(int[] nums1, int[] nums2) {
        Set<Integer> num1Set = new HashSet<>();

        for (int num : nums1) num1Set.add(num);
        for (int num : nums2) num1Set.add(num);

        return num1Set.stream().sorted().mapToInt(a -> a).toArray();

    }

    public int[] unionArray(int[] nums1, int[] nums2) {
        int i = 0;
        int j = 0;

        ArrayList<Integer> unionList = new ArrayList<>();

        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] < nums2[j] && ((unionList.isEmpty() || unionList.get(unionList.size() - 1) != nums1[i]))) {
                unionList.add(nums1[i++]);
            } else if (nums1[i] > nums2[j] && (unionList.isEmpty() || unionList.get(unionList.size() - 1) != nums2[j])) {
                unionList.add(nums2[j++]);
            } else {
                if ((unionList.isEmpty() || unionList.get(unionList.size() - 1) != nums1[i] || unionList.get(unionList.size() - 1) != nums2[j])) {
                    unionList.add(nums1[i]);
                }
                i++;
                j++;
            }
        }

        while (i < nums1.length) {
            if (nums1[i] != nums1[i - 1] && (unionList.get(unionList.size() - 1) != nums1[i])) {
                unionList.add(nums1[i]);
            }
            i++;
        }

        while (j < nums2.length) {
            if (nums2[j] != nums2[j - 1] && (unionList.get(unionList.size() - 1) != nums2[j])) {
                unionList.add(nums2[j]);
            }
            j++;
        }
        return unionList.stream().mapToInt(a -> a).toArray();
    }

    public int[] intersectionArray(int[] nums1, int[] nums2) {
        Set<Integer> num1Set = new HashSet<>();
        Set<Integer> num2Set = new HashSet<>();

        for (int num : nums1) num1Set.add(num);
        for (int num : nums2) num2Set.add(num);

        num1Set.retainAll(num2Set);

        return num1Set.stream().sorted().mapToInt(a -> a).toArray();
    }
}
