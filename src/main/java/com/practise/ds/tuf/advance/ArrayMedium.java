package com.practise.ds.tuf.advance;

import java.util.*;

public class ArrayMedium {


    public static void main(String[] args) {
        int[][] arr = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};

        // Create an instance of the Solution class
        ArrayMedium sol = new ArrayMedium();

        // Rotate the matrix
        sol.rotateMatrix(arr);

        // Print the rotated matrix
        System.out.println("Rotated Image");
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }

    public ArrayList<Integer> leaders(int[] nums) {

        ArrayList<Integer> leaderList = new ArrayList<>();

        if (nums.length == 1) {
            leaderList.add(nums[0]);
            return leaderList;
        }

        int max = nums[nums.length - 1];

        leaderList.add(max);
        for (int i = nums.length - 2; i >= 0; i--) {
            if (nums[i] > max) {
                max = nums[i];
                leaderList.add(max);
            }
        }
        Collections.reverse(leaderList);

        return leaderList;
    }

    public int[] twoSum(int[] nums, int target) {

        int[] result = new int[2];

        Map<Integer, Integer> twoSumMap = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            if (twoSumMap.containsKey(target - nums[i])) {
                result[1] = i;
                result[0] = twoSumMap.get(target - nums[i]);
            } else {
                twoSumMap.put(nums[i], i);
            }
        }
        return result;
    }

    public List<List<Integer>> threeSumBruteForce(int[] nums) {
        Set<List<Integer>> resultSet = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                for (int k = j + 1; k < nums.length; k++) {
                    if (nums[i] + nums[j] + nums[k] == 0) {
                        List<Integer> result = new ArrayList<>(List.of(i, j, k));
                        result.sort(Comparator.naturalOrder());
                        resultSet.add(result);
                    }
                }
            }
        }
        return new ArrayList<>(resultSet);
    }

    public List<List<Integer>> threeSumBetter(int[] nums) {
        Set<List<Integer>> resultSet = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            HashSet<Integer> numSet = new HashSet<>();
            for (int j = i + 1; j < nums.length; j++) {
                int k = Math.negateExact(nums[i] + nums[j]);
                if (numSet.contains(k)) {
                    List<Integer> result = new ArrayList<>(List.of(nums[i], nums[j], k));
                    result.sort(Comparator.naturalOrder());
                    resultSet.add(result);
                }
                numSet.add(nums[j]);
            }
        }
        return new ArrayList<>(resultSet);
    }

    public List<List<Integer>> threeSumOptimal(int[] nums) {
        Set<List<Integer>> resultSet = new HashSet<>();

        Arrays.sort(nums);

        for (int i = 0; i < nums.length - 2; i++) { // Start from i = 0
            if (i > 0 && nums[i] == nums[i - 1]) continue; // Skip duplicates for i

            int j = i + 1;
            int k = nums.length - 1;

            while (j < k) {
                int sum = nums[i] + nums[j] + nums[k];
                if (sum == 0) {
                    resultSet.add(new ArrayList<>(List.of(nums[i], nums[j], nums[k])));

                    while (j < k && nums[j] == nums[j + 1]) j++; // Skip duplicates for j
                    while (j < k && nums[k] == nums[k - 1]) k--; // Skip duplicates for k

                    j++; // Move j forward
                    k--; // Move k backward
                } else if (sum < 0) {
                    j++; // Increment j to increase the sum
                } else {
                    k--; // Decrement k to decrease the sum
                }
            }
        }
        return new ArrayList<>(resultSet);
    }

    public List<List<Integer>> fourSumBruteForce(int[] nums, int target) {
        Set<List<Integer>> resultSet = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                for (int k = j + 1; k < nums.length; k++) {
                    for (int l = k + 1; l < nums.length; l++) {
                        if (nums[i] + nums[j] + nums[k] + nums[l] == target) {
                            List<Integer> result = new ArrayList<>(List.of(nums[i], nums[j], nums[k], nums[l]));
                            result.sort(Comparator.naturalOrder());
                            resultSet.add(result);
                        }
                    }
                }
            }
        }
        return new ArrayList<>(resultSet);
    }

    public List<List<Integer>> fourSumBetter(int[] nums, int target) {
        Set<List<Integer>> resultSet = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                HashSet<Integer> numSet = new HashSet<>();
                for (int k = j + 1; k < nums.length; k++) {
                    int l = target - (nums[i] + nums[j] + nums[k]);
                    if (numSet.contains(l)) {
                        List<Integer> result = new ArrayList<>(List.of(nums[i], nums[j], nums[k], l));
                        result.sort(Comparator.naturalOrder());
                        resultSet.add(result);
                    }
                    numSet.add(nums[k]);
                }
            }
        }
        return new ArrayList<>(resultSet);
    }


    // kadane's algorithm

    public List<List<Integer>> fourSumOptimal(int[] nums, int target) {
        Set<List<Integer>> resultSet = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                HashSet<Integer> numSet = new HashSet<>();
                for (int k = j + 1; k < nums.length; k++) {
                    int l = target - (nums[i] + nums[j] + nums[k]);
                    if (numSet.contains(l)) {
                        List<Integer> result = new ArrayList<>(List.of(nums[i], nums[j], nums[k], l));
                        result.sort(Comparator.naturalOrder());
                        resultSet.add(result);
                    }
                    numSet.add(nums[k]);
                }
            }
        }
        return new ArrayList<>(resultSet);
    }

    public int maxSubArray(int[] nums) {
        if (nums.length == 0) return 0;

        int curSum = nums[0];
        int maxSum = nums[0];

        for (int i = 1; i < nums.length; i++) {
            if (curSum > 0) {
                curSum += nums[i];
            } else {
                curSum = nums[i];
            }
            maxSum = Math.max(curSum, maxSum);
        }
        return maxSum;
    }

    public List<Integer> spiralOrder(int[][] matrix) {

        List<Integer> resultList = new ArrayList<>();

        int left = 0;
        int right = matrix[0].length - 1; // column value
        int top = 0;
        int bottom = matrix.length - 1; // row value

        while (top <= bottom && left <= right) {

            // left -> right
            for (int i = left; i <= right; i++) {
                resultList.add(matrix[left][i]);
            }
            top++;

            // right -> bottom
            for (int i = top; i <= bottom; i++) {
                resultList.add(matrix[i][right]);
            }
            right--;

            // right -> left
            if (top <= bottom) { // single row/uneven rows and column case
                for (int i = right; i >= left; i--) {
                    resultList.add(matrix[bottom][i]);
                }
                bottom--;
            }

            // bottom -> top
            if (left <= right) { // single row/uneven rows and column case
                for (int i = bottom; i >= top; i--) {
                    resultList.add(matrix[i][left]);
                }
                left++;
            }
        }
        return resultList;
    }

    public int[] rearrangeArray(int[] nums) {

        int[] result = new int[nums.length];

        int positivePointer = 0;
        int negativePointer = 0;
        int count = 0;

        while (positivePointer < nums.length && negativePointer < nums.length) {
            while (positivePointer < nums.length && nums[positivePointer] < 0) {
                positivePointer++;
            }

            while (negativePointer < nums.length && nums[negativePointer] > 0) {
                negativePointer++;
            }

            result[count++] = nums[positivePointer++];
            result[count++] = nums[negativePointer++];
        }

        return result;
    }

    public void rotateMatrix(int[][] matrix) {
        if (matrix.length == 0 || matrix.length == 1) return;
        int[][] temp = new int[matrix.length][matrix[0].length];

        int row = matrix.length;
        int col = matrix[0].length;

        for (int i = 0; i < row; i++) {
            int k = 0;
            for (int j = col - 1; j >= 0; j--) {
                temp[i][k++] = matrix[j][i];
            }
        }

        System.arraycopy(temp, 0, matrix, 0, temp.length);

    }
}

