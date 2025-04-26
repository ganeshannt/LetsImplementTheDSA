package com.practise.ds.tuf.advance;

import java.util.Arrays;

public class BinarySearchOnAnswer {

    public static void main(String[] args) {
        BinarySearchOnAnswer solution = new BinarySearchOnAnswer();
        int[] arr = {7, 7, 7, 7, 13, 11, 12, 7};

        int n = arr.length;

        // Number of flowers per bouquet
        int k = 3;

        // Number of bouquets needed
        int m = 2;

        int ans = solution.roseGarden(arr, k, m);

        if (ans == -1) {
            System.out.println("We cannot make m bouquets.");
        } else {
            System.out.println("We can make bouquets on day " + ans);
        }
    }

    public int NthRoot(int N, int M) {
        int start = 1;
        int end = M;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            int value = (int) Math.pow(mid, N);
            if (Math.pow(mid, N) == M) {
                return mid;
            } else if (M < value) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return -1;
    }

    public int smallestDivisor(int[] nums, int limit) {

        if (limit < nums.length)
            return -1;

        int start = 1;
        int end = Arrays.stream(nums).max().getAsInt();
        while (start <= end) {
            int mid = start + (end - start) / 2;
            int sum = 0;
            for (int num : nums) {
                sum += Math.ceil((double) num / (double) mid);
            }
            if (sum <= limit) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return start;
    }

    public int minimumRateToEatBananas(int[] nums, int h) {
        if (h < nums.length) return -1;

        int start = 1;
        int end = Arrays.stream(nums).max().getAsInt();

        while (start <= end) {
            int mid = start + (end - start) / 2;
            int sum = 0;
            for (int num : nums) {
                sum += Math.ceil((double) num / (double) mid);
            }
            if (sum <= h) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return start;
    }

    public int roseGarden(int[] nums, int k, int m) {
        if (nums.length < Math.multiplyExact(m, k)) return -1;

        int start = Arrays.stream(nums).min().getAsInt();
        int end = Arrays.stream(nums).max().getAsInt();

        int ans = -1;

        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (bouquetsSoFar(nums, mid, m, k)) {
                ans = mid;
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return ans;
    }

    private boolean bouquetsSoFar(int[] nums, int mid, int m, int k) {
        int count = 0;
        int bouquetsSoFar = 0;

        for (int num : nums) {
            if (mid >= num) {
                count += 1;
            } else {
                bouquetsSoFar += Math.floorDiv(count, k);
                if (bouquetsSoFar > m) {
                    break;
                }
                count = 0;
            }
        }

        if (count != 0) {
            bouquetsSoFar += Math.floorDiv(count, k);
        }
        return bouquetsSoFar >= m;
    }
}