package com.practise;

import com.practise.ds.linkedlist.SingleLinkedList;

public class PrintOutput {



    public static void printArray(int arr[]) {
        System.out.print("[ ");
        for (int i : arr) {
            System.out.print(i + " ");
        }
        System.out.print(" ]");
    }

    public void printLinkedList() {        
        int i = 0;
        while (head != null) {
            System.out.println(i + " -> " + head.value);
            head = head.next;
        }
    }
}
