package com.practise.problemsolving.searchingandsorting;

public class KthSmallestElement {

    public static void main(String[] args) {
        KthSmallestElement element = new KthSmallestElement();
        int arr[] = {3, 2, 3, 1, 2, 4, 5, 5, 6};
        element.approach1(arr, 4);
    }

    //  Type of Solution - worst
    //  formula - nil
    //  Time Complexity - O(n)^2  -> used two loop to arrange elements
    //  Space Complexity - o(1) -> no extra spaces used
    public void approach1(int arr[], int k) {
        int smallest_index = 0;
        for (int i = 0; i < arr.length; i++) {
            smallest_index = i;
            for (int j = i; j < arr.length; j++) {
                if (arr[smallest_index] > arr[j]) {
                    smallest_index = j;
                }
            }
            if (arr[i] != arr[smallest_index]) {
                swapUsingIndex(arr, i, smallest_index);
            }
        }
        for (int i : arr) {
            System.out.println(i);
        }
        System.out.println("value =>" + arr[arr.length - k]);
    }

    private void swapUsingIndex(int arr[], int first, int second) {
        int temp = arr[first];
        arr[first] = arr[second];
        arr[second] = temp;
    }
}