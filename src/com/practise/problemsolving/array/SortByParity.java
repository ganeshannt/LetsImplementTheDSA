package com.practise.problemsolving.array;

public class SortByParity {

    private void firstApproach(int[] arr) {
        if (arr.length == 0 || arr.length == 1) {
            System.out.println("empty array");
            return;
        }

        int first = 0;
        int second = 1;
        while (second < arr.length) {
            if (arr[second] % 2 != 0 || first == second) {
                second++;
            } else if (arr[first] % 2 == 0) {
                first++;
            } else if (arr[first] % 2 != 0 && arr[second] % 2 == 0) {
                swap(arr, first, second);
                first++;
                second++;
            }
        }
        for (int i : arr) {
            System.out.println(i);
        }
    }

    private void swap(int arr[], int first, int second) {
        int temp = arr[first];
        arr[first] = arr[second];
        arr[second] = temp;
    }

    public static void main(String[] args) {
        SortByParity element = new SortByParity();
        int arr[] = { 5 };
        // Output: [2,1,1,0]
        element.firstApproach(arr);
    }
}
