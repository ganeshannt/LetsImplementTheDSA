package com.practise.problemsolving.searchingandsorting;

import java.util.HashMap;
import java.util.Map;

public class SortZerosAndOnes {

    public static void main(String[] args) {
        int arr[] = {1, 2, 0};
        SortZerosAndOnes sortZerosAndOnes = new SortZerosAndOnes();
        sortZerosAndOnes.singlePassSolution(arr);
    }

    //  Type of Solution - best
    //  formula - nil
    //  Time Complexity - O(n)  -> used loop to arrange elements in order by using three pointers method so it take o(n)
    //  Space Complexity - o(n) -> hash map stored n elements
    public void doublePassSolution(int arr[]) {
        Map<Integer, Integer> countMap = new HashMap<Integer, Integer>();
        for (int i : arr) {
            if (countMap.containsKey(i)) {
                countMap.put(i, (countMap.get(i)) + 1);
            } else {
                countMap.put(i, 1);
            }
        }
        int j = 0;

        for (Map.Entry<Integer, Integer> set : countMap.entrySet()) {
            for (int i = 0; i < set.getValue(); i++) {
                arr[j++] = set.getKey();
            }
        }
    }

    //  Type of Solution - best
    //  formula - nil
    //  Time Complexity - O(n)  -> used loop to arrange elements in order by using three pointers method so it take o(n)
    //  Space Complexity - o(1) -> no extra spaces used
    public void singlePassSolution(int arr[]) {
        int c = 0, l = 0;
        int h = arr.length - 1;
        while (c <= h) {
            if (arr[c] == 0) {
                if (c != l) {
                    swapUsingIndex(arr, c, l);
                }
                c++;
                l++;
            } else if (arr[c] == 1) {
                c++;
            } else if (arr[c] == 2) {
                swapUsingIndex(arr, c, h);
                h--;
            }
        }
        for (int i : arr) {
            System.out.println(i);
        }
    }

    private void swapUsingIndex(int arr[], int first, int second) {
        int temp = arr[first];
        arr[first] = arr[second];
        arr[second] = temp;
    }
}