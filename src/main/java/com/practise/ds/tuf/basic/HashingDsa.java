package com.practise.ds.tuf.basic;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

class HashingDsa {

    static int mostFrequentElement(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            if (map.containsKey(num)) {
                map.put(num, map.get(num) + 1);
            } else {
                map.put(num, 1);
            }
        }

        int freqElement = 0;
        int freq = 0;

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() > freq) {
                freq = entry.getValue();
                freqElement = entry.getKey();
            }
        }

        return freqElement;
    }


    static int secondMostFrequentElement(int[] nums) {
        LinkedHashMap<Integer, Integer> map = new LinkedHashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        int maxFreqElement = -1;
        int maxFreq = -1;
        int secondFreqElement = -1;
        int secondFreq = -1;

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (maxFreq < entry.getValue()) {
                secondFreq = maxFreq;
                secondFreqElement = maxFreqElement;
                maxFreq = entry.getValue();
                maxFreqElement = entry.getKey();
            } else if (maxFreq == entry.getValue()) {
                maxFreqElement = Math.min(maxFreqElement, entry.getKey());
            } else {
                if (secondFreq < entry.getValue()) {
                    secondFreq = entry.getValue();
                    secondFreqElement = entry.getKey();
                } else if (secondFreq == entry.getValue() && secondFreqElement > entry.getKey()) {
                    secondFreqElement = entry.getKey();
                }
            }
        }
        return secondFreqElement;
    }


    static int sumHighestAndLowestFrequency(int[] nums) {

        if (nums.length == 1) return 1;

        Map<Integer, Integer> map = new HashMap<>();

        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        int maxFreq = 0;
        int minFreq = nums.length;

        for (int freq : map.values()) {
            maxFreq = Math.max(maxFreq, freq);
            minFreq = Math.min(minFreq, freq);
        }
        return maxFreq + minFreq;
    }


    public static void main(String[] args) {
        System.out.println(sumHighestAndLowestFrequency(new int[]{4, 4, 5, 5, 6}));
    }
}
