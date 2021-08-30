package com.practise.problemsolving.array;

public class SortColorCode {

    private void firstApproach(int[] arr) {
        int low = 0;
        int current = 0;
        int high = arr.length - 1;
        while (current < high) {
            if (arr[current] == 0) {
                if (current != low) {
                    swapByUsingIndex(arr, current, low);
                }
                low++;
                current++;
            } else if (arr[current] == 1) {
                current++;
            } else if (arr[current] == 2) {
                swapByUsingIndex(arr, current, high);
                high--;
            }
        }
        for (int i : arr) {
            System.out.println(i);
        }
    }

    private void swapByUsingIndex(int[] arr, int first, int second) {
        int temp = arr[first];
        arr[first] = arr[second];
        arr[second] = temp;
    }

    public static void main(String[] args) {
        SortColorCode element = new SortColorCode();
        int arr[] = { 0, 0, 1, 1, 2, 1, 2 };
        // Output: [2,1,1,0]
        element.firstApproach(arr);
    }

}
