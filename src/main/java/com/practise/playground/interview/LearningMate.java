package com.practise.playground.interview;

import java.util.*;
import java.util.stream.Collectors;

public class LearningMate {

    /*
    Prob 1: Given an array of integers, return the K most elements. Optimize for time.
     */
    public static List<Integer> mostFreqElement(List<Integer> integerList) {

        Map<Integer, Long> freqMap = integerList.stream().collect(Collectors.groupingBy(e -> e, Collectors.counting()));

        long currentFreq = 0;

        List<Integer> result = new ArrayList<>();

        for (Map.Entry<Integer, Long> entry : freqMap.entrySet()) {
            if (entry.getValue() > currentFreq) {
                currentFreq = entry.getValue();
            }
        }

        for (Map.Entry<Integer, Long> entry : freqMap.entrySet()) {
            if (entry.getValue() == currentFreq) {
                result.add(entry.getKey());
            }
        }
        return result;
    }


    private static List<String> uniqueElements(String input) {

        return Arrays.stream(input.replace(" ", "").split(""))
                .collect(Collectors.groupingBy(String::toLowerCase, Collectors.counting()))
                .entrySet()
                .stream()
                .filter(e -> e.getValue() == 1L)
                .map(Map.Entry::getKey)
                .toList();
    }


    public static void main(String[] args) {
        List<Integer> result = mostFreqElement(List.of(1, 1, 1, 2, 2, 3, 3, 3, 4, 4, 5));
        result.stream().forEach(System.out::println);
    }

}
