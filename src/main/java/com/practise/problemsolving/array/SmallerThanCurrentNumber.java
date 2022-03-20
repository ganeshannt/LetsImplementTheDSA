package com.practise.problemsolving.array;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class SmallerThanCurrentNumber {

    public static void main(String[] args) {
        SmallerThanCurrentNumber element = new SmallerThanCurrentNumber();
        int arr[] = {8, 1, 2, 2, 3};
        // Output: [2,1,1,0]
        element.bestApproach(arr);

    }

    // TC = o(nlogn)
    // SC = o(n)
    private void firstApproach(int[] arr) {
        int temparr[] = arr.clone();
        Arrays.sort(temparr);
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < temparr.length; i++) {
            map.putIfAbsent(temparr[i], i);
        }

        for (int i = 0; i < arr.length; i++) {
            arr[i] = map.get(arr[i]);
            System.out.println(arr[i]);
        }
    }

    // TC = o(n)
    // SC = o(temparr)
    private void bestApproach(int[] arr) {
        int temparr[] = new int[101];
        for (int i : arr) {
            temparr[i] = (temparr[i] != 0) ? temparr[i] + 1 : 1;
        }

        for (int i = 0; i < temparr.length; i++) {
            System.out.println("index " + i + " value " + temparr[i]);
        }

        for (int i = 1; i < temparr.length; i++) {
            temparr[i] = temparr[i - 1] + temparr[i];
        }

        for (int i = 0; i < arr.length; i++) {
            arr[i] = (arr[i] == 0) ? 0 : temparr[arr[i] - 1];
        }
        for (int i : arr) {
            System.out.println(i);
        }
    }
}