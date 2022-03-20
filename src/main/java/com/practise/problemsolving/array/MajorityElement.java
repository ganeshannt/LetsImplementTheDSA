package com.practise.problemsolving.array;

import java.util.HashMap;
import java.util.Map;

public class MajorityElement {

    public static void main(String[] args) {
        MajorityElement element = new MajorityElement();
        int arr[] = {2, 8, 7, 2, 1};
        int m = 0;
        // m = element.hashTableApproach(arr);
        m = element.boyerMooreAlgorithm(arr);
        System.out.println(m);
    }

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

    private int boyerMooreAlgorithm(int[] arr) {
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
