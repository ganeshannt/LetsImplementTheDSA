package com.practise.ds.array.problem;

import java.util.ArrayList;
import java.util.List;

/*

Name - Create Target Array in the Given Order
Link - https://leetcode.com/problems/create-target-array-in-the-given-order/

 */
public class CreateTargetArray {

    public static void main(String[] args) {
        CreateTargetArray element = new CreateTargetArray();
        int arr[] = {0, 1, 2, 3, 4};
        int index[] = {0, 1, 2, 2, 1};
        // m = element.hashTableApproach(arr);
        element.bestApproach(arr, index);
        // element.moveahead(arr, 1, 4);
    }


    /*
    Time Complexity [best] - o(n)
    Time Complexity [worst] - o(n*n)
    Space Complexity - o(1)
    Note - Without arraylist solution
     */
    private void bestApproach(int[] arr, int[] index) {
        int i = 0;
        while (i < index.length) {
            if (i == index[i]) {
                i++;
            } else if (i > index[i]) {
                moveAhead(arr, index[i], arr[i], i);
                i++;
            }
        }
        for (int k : arr) {
            System.out.println(k);
        }
    }

    private void moveAhead(int arr[], int index, int value, int current_index) {
        int temp = arr[index];
        arr[index] = value;
        index += 1;
        while (index <= current_index) {
            value = arr[index];
            arr[index] = temp;
            temp = value;
            index++;
        }
    }

    /*
    Time Complexity [best] - o(n)
    Time Complexity [worst] - o(n*n)
    Space Complexity - o(n)
    Note - if arraylist is allowed to use, very simple to get a solution
     */
    private void simpleApproach(int[] arr, int[] index) {
        List<Integer> target = new ArrayList<>(arr.length - 1);
        for (int i = 0; i < arr.length; i++) {
            target.add(index[i], arr[i]);
        }

        for (int i = 0; i < arr.length; i++) {
            arr[i] = target.get(i);
        }
    }
}
