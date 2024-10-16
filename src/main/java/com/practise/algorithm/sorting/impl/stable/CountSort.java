package com.practise.algorithm.sorting.impl.stable;

import com.practise.commons.Utils;

public class CountSort {

    /**
     * A simple implementation of counting sort assuming input values range from 0-9.
     * <p>
     * Time Complexity: O(N + K)
     * - O(N) to count occurrences in the input array.
     * - O(K) to build the sorted array, where K is the range of input values (0-9 here).
     * <p>
     * Space Complexity: O(K)
     * - O(K) additional space used for the count array.
     *
     * @param arr The array to be sorted (with values ranging between 0-9).
     */
    public static void iterativeSimpleCountSort(int[] arr) {
        int[] count = new int[10]; // Array to store count of elements (0-9).

        // Count the occurrences of each element in the input array.
        for (int i = 0; i < arr.length; i++) {
            count[arr[i]]++;
        }

        // Debugging: Print the count array
        for (int i = 0; i < count.length; i++) {
            System.out.println("Element " + i + " occurs " + count[i] + " times.");
        }

        // Rebuild the original array using the count array
        int index = 0;
        for (int i = 0; i < count.length; i++) {
            while (count[i] > 0) { // Place 'i' count[i] times in the array.
                arr[index++] = i;
                count[i]--;
            }
        }

        // Print the sorted array
        System.out.println("Sorted Array:");
        Utils.printArray(arr);
    }

    /**
     * A more general implementation of counting sort that can handle larger ranges.
     * <p>
     * Time Complexity: O(N + K)
     * - O(N) for counting occurrences.
     * - O(K) for reconstructing the sorted array.
     * <p>
     * Space Complexity: O(K)
     * - O(K) additional space for the count array, where K is the range of input values.
     *
     * @param arr The array to be sorted.
     */
    public static void iterativeCountSort(int[] arr) {
        // Step 1: Find the maximum element in the array to determine the range.
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }

        // Step 2: Create a count array of size max+1 (range of elements 0 to max)
        int[] count = new int[max + 1];

        // Step 3: Count occurrences of each element in the input array.
        for (int i = 0; i < arr.length; i++) {
            count[arr[i]]++;
        }

        // Step 4: Rebuild the sorted array using the count array.
        int index = 0;
        for (int i = 0; i < count.length; i++) {
            while (count[i] > 0) {
                arr[index++] = i;
                count[i]--;
            }
        }

        // Print the sorted array
        System.out.println("Sorted Array:");
        Utils.printArray(arr);
    }

    public static void main(String[] args) {
        int[] arr = {23, 21, 4, 2, 54, 67, 86, 67, 34, 7, 6, 43, 8, 9, 665, 346, 97, 546, 675, 25, 76};
        int[] arr2 = {1, 2, 2, 3, 6, 5, 4, 7, 8, 7, 7, 6, 8, 9, 3};

        // Simple Count Sort (for range 0-9)
        System.out.println("Before Simple Count Sort:");
        Utils.printArray(arr2);
        iterativeSimpleCountSort(arr2);

        // General Count Sort (handles larger range)
        System.out.println("\nBefore General Count Sort:");
        Utils.printArray(arr);
        iterativeCountSort(arr);
    }
}
