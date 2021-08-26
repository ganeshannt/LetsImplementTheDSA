package com.practise.problemsolving.array;

import java.util.HashMap;
import java.util.Map;

public class SingleNumber {

    private void firstApproach(int[] arr) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i : arr) {
            if (map.containsKey(i)) {
                map.put(i, 1 + map.get(i));
            } else {
                map.put(i, 1);
            }
        }
        for (Map.Entry<Integer, Integer> set : map.entrySet()) {
            if (set.getValue() % 2 != 0) {
                System.out.println(set.getKey());
            }
        }
    }

    private void bestApproach(int[] arr) {
        int ans = 0;
        for (int i : arr) {
            ans ^=i;
        }
        System.out.println(ans);
    }

    public static void main(String[] args) {
        SingleNumber element = new SingleNumber();
        int arr[] = { 1, 2, 3, 4, 5, 6, 7 };
        element.firstApproach(arr);
    }
}
