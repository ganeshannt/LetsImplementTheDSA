package com.practise;

public class PrintOutput {

    public static void printArray(int arr[]) {
        System.out.print("[ ");
        for (int i : arr) {
            System.out.print(i + " ");
        }
        System.out.print(" ]");
    }
}
