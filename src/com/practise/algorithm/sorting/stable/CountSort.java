package com.practise.algorithm.sorting.stable;

import com.practise.PrintOutput;

public class CountSort {
    
// Assume here input range is between 0-9
    public static void iterativeSimpleCountSort(int[] arr) {
        int [] arr2 = new int[10];
        int val;
        for (int i = 0; i < arr.length; i++) {
            val = arr2[arr[i]];
            arr2[arr[i]] = val+1;
        }

        for (int i = 0; i < arr2.length; i++) {
            System.out.println("index "+i+" value "+arr2[i]);
        }

        int value = 0;
        for (int i = 0; i < arr2.length;i++) {
            if(arr2[i]!=0){
                for (int j = 0; j < arr2[i]; j++) {
                    arr[value] = i;
                    value +=1;
                }
            }
        }
        PrintOutput.printArray(arr);
    }


    public static void iterativeCountSort(int[] arr) {

    }


    public static void main(String[] args) {
        int arr[] = {23, 21, 4, 2, 54, 67, 86, 67, 34, 7, 6, 43, 8, 9, 665, 346, 97, 546, 675, 25, 76};
        int arr2[] = {1,2,2,3,6,5,4,7,8,7,7,6,8,9,3};
        iterativeSimpleCountSort(arr2);
    }

    
}
