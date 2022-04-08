package com.practise;

import java.util.List;

public class CommonUtils {

    public static void printArray(int arr[]) {
        System.out.print("[ ");
        for (int i : arr) {
            System.out.print(i + " ");
        }
        System.out.print(" ]");
        System.out.println();
    }

    public static void printList(List<Integer> list) {
        System.out.print("[ ");
        for (int val :
                list) {
            System.out.println(val);
        }
        System.out.print(" ]");
        System.out.println();
    }

    public static void swapByIndex(int arr[], int first_index, int second_index) {
        int temp = arr[first_index];
        arr[first_index] = arr[second_index];
        arr[second_index] = temp;
    }

    public static void swapByIndex(List<Integer> list, int first_index, int second_index) {
        int temp = list.get(first_index);
        list.set(first_index, list.get(second_index));
        list.set(second_index, temp);
    }
}
