package com.practise.problemsolving.array;

import java.util.HashMap;
import java.util.Map;

/*

Name - Majority Element
Link - https://leetcode.com/problems/majority-element/

 */

public class MajorityElement {

    public static void main(String[] args) {
        MajorityElement element = new MajorityElement();
        int arr[] = {2, 8, 7, 2, 1};
        int m = 0;
        // m = element.hashTableApproach(arr);
        m = element.bestApproach(arr);
        System.out.println(m);
    }

    /*
    Time Complexity - o(mn)
    Space Complexity - o(1)
    Note - hash map
    */

    private int hashTableApproach(int[] arr) {
        if (arr.length == 1) {
            return arr[1];
        }
        Map<Integer, Integer> map = new HashMap<>();
        for (int i : arr) {
            if (map.containsKey(i)) {
                map.put(i, map.get(i) + 1);
                if (map.get(i) > arr.length / 2) {
                    return i;
                }
            } else {
                map.put(i, 1);
            }
        }
        return -1;
    }

    /*
    Time Complexity - o(mn)
    Space Complexity - o(1)
    Note - Boyer Moore Algorithm
    */

    private int bestApproach(int[] arr) {
        int majority = 0;
        int cnt = 0;
        for (int i : arr) {
            if (cnt == 0) {
                majority = i;
                cnt++;
            } else {
                if (majority == i) {
                    cnt++;
                } else {
                    cnt--;
                }
            }
        }
        cnt = 0;
        for (int i : arr) {
            if (majority == i) {
                cnt++;
            }
        }
        if (cnt > arr.length / 2) {
            return majority;
        }
        return -1;
    }
}
